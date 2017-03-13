package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dyadav.flicks.ItemClickSupport;
import com.dyadav.flicks.R;
import com.dyadav.flicks.adapter.MoviesAdapter;
import com.dyadav.flicks.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.movieList)
    RecyclerView rView;


    ArrayList<Movies> movie_list;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            movie_list = savedInstanceState.getParcelableArrayList("Movies");
        } else {
            movie_list = new ArrayList<>();
        }

        //Adapter
        adapter = new MoviesAdapter(this, movie_list);
        rView.setAdapter(adapter);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(rView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Launch Detail Activity
                Intent intent = new Intent(MovieActivity.this, DetailActivity.class);
                //Title,Release date, backdrop, vote average and Overview
                Movies movie = movie_list.get(position);
                intent.putExtra("Movie", movie);
                startActivity(intent);
            }
        });
        getmovieList();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getmovieList();
            }
        });
    }

    public void getmovieList() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast toast = Toast.makeText(MovieActivity.this, R.string.error_movie_list, Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    final String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    final JSONArray movieArray = json.getJSONArray("results");

                    MovieActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();
                            adapter.addAll(Movies.fromJSONArray(movieArray));
                            swipeContainer.setRefreshing(false);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(state);
        state.putParcelableArrayList("Movies", movie_list);
    }
}
