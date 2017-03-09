package com.dyadav.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dyadav.flicks.R;

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView backdropImage;

    public PopularMovieViewHolder(View v) {
        super(v);
        backdropImage = (ImageView) v.findViewById(R.id.movieImage);
    }

    public ImageView getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(ImageView backdropImage) {
        this.backdropImage = backdropImage;
    }
}