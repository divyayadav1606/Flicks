package com.dyadav.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dyadav.flicks.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if (intent !=  null) {
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(intent.getStringExtra("title"));

            TextView date = (TextView) findViewById(R.id.relesedate);
            date.setText(intent.getStringExtra("date"));

            TextView overview = (TextView) findViewById(R.id.movieOverview);
            overview.setText(intent.getStringExtra("overview"));

            ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
            Picasso.with(this).load(intent.getStringExtra("backdrop"))
                    .placeholder(R.drawable.placeholer_movie_land)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(backdrop);

            RatingBar votes = (RatingBar) findViewById(R.id.ratingBar);
            votes.setRating((float) intent.getDoubleExtra("rating", 0));
        }
    }
}