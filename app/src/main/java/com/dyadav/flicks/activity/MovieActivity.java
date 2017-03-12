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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

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

        //Adapter
        movie_list = new ArrayList<>();
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
                intent.putExtra("title", movie_list.get(position).getmTitle());
                intent.putExtra("overview", movie_list.get(position).getmOverview());
                intent.putExtra("date", movie_list.get(position).getmReleaseDate());
                intent.putExtra("backdrop", movie_list.get(position).getmBackdropPath());
                intent.putExtra("rating", movie_list.get(position).getmVoteAverage());
                intent.putExtra("poster", movie_list.get(position).getmPosterPath());
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
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movieArray = response.getJSONArray("results");

                    adapter.clear();
                    adapter.addAll(Movies.fromJSONArray(movieArray));

                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast toast = Toast.makeText(MovieActivity.this, "Error refreshing", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
