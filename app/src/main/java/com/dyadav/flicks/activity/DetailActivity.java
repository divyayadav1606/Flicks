package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dyadav.flicks.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent !=  null) {
            layout.setTitle(intent.getStringExtra("title"));
            layout.setCollapsedTitleTextColor(getResources().getColor(R.color.icons));
            layout.setExpandedTitleColor(getResources().getColor(R.color.icons));

            title.setText(intent.getStringExtra("title"));

            date.setText("Release Date: " + intent.getStringExtra("date"));

            overview.setText(intent.getStringExtra("overview"));

            Picasso.with(this).load(intent.getStringExtra("backdrop"))
                    .placeholder(R.drawable.placeholer_movie_land)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(backdrop);

            Picasso.with(this).load(intent.getStringExtra("poster"))
                    .placeholder(R.drawable.placeholer_movie_land)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(posterImage);

            votes.setRating((float) intent.getDoubleExtra("rating", 0)/2);
        }

        final String movieName = intent.getStringExtra("title");
        final String movieOverview = intent.getStringExtra("overview");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout " + movieName);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Overview - " + movieOverview);
                startActivity(Intent.createChooser(sharingIntent,  "Type"));

            }
        });
    }
}