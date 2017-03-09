package com.dyadav.flicks;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dyadav.flicks.adapter.MoviesAdapter;
import com.dyadav.flicks.model.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movies> movie_list;
    private SwipeRefreshLayout swipeContainer;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //Adapter
        RecyclerView rView = (RecyclerView) findViewById(R.id.movieList);
        movie_list = new ArrayList<>();
        adapter = new MoviesAdapter(this, movie_list);
        rView.setAdapter(adapter);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setLayoutManager(new LinearLayoutManager(this));
        getmovieList();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getmovieList();
            }
        });
    }

    public void getmovieList() {
        AsyncHttpClient client = new AsyncHttpClient();

        //Get movie list
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


    //Save movielist to avoid network fetch on orientation change
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putStringArrayList("MOVIE_LIST", movie_item_regular);
    }
}
