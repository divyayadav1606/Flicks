package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dyadav.flicks.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%d/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                id);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast toast = Toast.makeText(YoutubeActivity.this, "Error getting trailers", Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    final String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    JSONArray trailerArray = json.getJSONArray("youtube");

                    final JSONObject firstTrailer = trailerArray.getJSONObject(0);

                    YoutubeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
