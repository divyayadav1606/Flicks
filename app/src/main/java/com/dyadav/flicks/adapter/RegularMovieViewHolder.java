package com.dyadav.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyadav.flicks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegularMovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movieTitle)
    TextView title;

    @BindView(R.id.movieOverview)
    TextView overview;

    @BindView(R.id.movieImage)
    ImageView moviePoster;


    public RegularMovieViewHolder(View v) {
        super(v);

        ButterKnife.bind(this, v);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getOverview() {
        return overview;
    }

    public void setOverview(TextView overview) {
        this.overview = overview;
    }

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(ImageView moviePoster) {
        this.moviePoster = moviePoster;
    }
}
