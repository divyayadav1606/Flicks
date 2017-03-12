package com.dyadav.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyadav.flicks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movieImage)
    ImageView backdropImage;

    @BindView(R.id.movieTitle)
    TextView movieTitle;

    @BindView(R.id.play)
    ImageButton playButton;

    public PopularMovieViewHolder(View v) {
        super(v);

        ButterKnife.bind(this, v);
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