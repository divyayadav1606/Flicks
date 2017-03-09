package com.dyadav.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyadav.flicks.R;

public class RegularMovieViewHolder extends RecyclerView.ViewHolder {

    private TextView title, overview;
    private ImageView moviePoster;


    public RegularMovieViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.movieTitle);
        overview = (TextView) v.findViewById(R.id.movieOverview);
        moviePoster = (ImageView) v.findViewById(R.id.movieImage);
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
