package com.dyadav.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyadav.flicks.R;

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView backdropImage;
    private TextView movieTitle;
    private ImageButton playButton;

    public PopularMovieViewHolder(View v) {
        super(v);
        backdropImage = (ImageView) v.findViewById(R.id.movieImage);
        movieTitle = (TextView) v.findViewById(R.id.movieTitle);
        playButton = (ImageButton) v.findViewById(R.id.play);
    }

    public ImageView getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(ImageView backdropImage) {
        this.backdropImage = backdropImage;
    }

    public TextView getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(TextView movieTitle) {
        this.movieTitle = movieTitle;
    }

    public ImageButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(ImageButton playButton) {
        this.playButton = playButton;
    }
}