package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dyadav.flicks.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class YoutubeActivity extends YouTubeBaseActivity {

    @BindView(R.id.player)
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        ButterKnife.bind(this);

        int movieId = intent.getExtras().getInt("id");

        playTrailer(movieId);
    }

    public void playTrailer(int id) {
        AsyncHttpClient client = new AsyncHttpClient();

        String url = String.format(
                "https://api.themoviedb.org/3/movie/%d/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                id);

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray trailerArray = response.getJSONArray("youtube");

                    final JSONObject firstTrailer = trailerArray.getJSONObject(0);

                    youTubePlayerView.initialize("AIzaSyAr27PMcWioaWeGu-eVZDfOl69i5Fql_7c",
                            new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                    YouTubePlayer youTubePlayer, boolean b) {
                                    try {
                                        youTubePlayer.loadVideo(firstTrailer.getString("source"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                    YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast toast = Toast.makeText(YoutubeActivity.this, "Error Loading Trailer", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
