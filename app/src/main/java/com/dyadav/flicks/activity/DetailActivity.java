package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dyadav.flicks.R;
import com.dyadav.flicks.model.Movies;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import okhttp3.OkHttpClient;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.relesedate)
    TextView date;

    @BindView(R.id.movieOverview)
    TextView overview;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.posterImage)
    ImageView posterImage;

    @BindView(R.id.ratingBar)
    RatingBar votes;

    @BindView(R.id.fab)
    FloatingActionButton btn;

    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout layout;

    @BindView(R.id.play)
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);


        final Movies movie = getIntent().getExtras().getParcelable("Movie");

        OkHttpClient client = new OkHttpClient();
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();

        layout.setTitle(movie.getmTitle());
        layout.setCollapsedTitleTextColor(getResources().getColor(R.color.icons));
        layout.setExpandedTitleColor(getResources().getColor(R.color.icons));

        title.setText(movie.getmTitle());

        date.setText("Release Date: " + movie.getmReleaseDate());

        overview.setText(movie.getmOverview());

        picasso.with(this).load(movie.getmBackdropPath())
                .placeholder(R.drawable.placeholer_movie_land)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(backdrop);

        picasso.with(this).load(movie.getmPosterPath())
                .placeholder(R.drawable.placeholer_movie_land)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(posterImage);

        votes.setRating((float) movie.getmVoteAverage()/2);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, YoutubeActivity.class);
                i.putExtra("id", movie.getmId());
                DetailActivity.this.startActivity(i);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout " + movie.getmTitle());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Overview - " + movie.getmOverview());
                startActivity(Intent.createChooser(sharingIntent,  "Type"));

            }
        });
    }
}