package com.dyadav.flicks.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyadav.flicks.R;
import com.dyadav.flicks.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MoviesAdapter extends
        RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<Movies> moviesList;
    private Context context;

    public MoviesAdapter(Context context, ArrayList<Movies> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    /** ViewHolder **/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle;
        public TextView movieOverview;
        public ImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieOverview = (TextView) itemView.findViewById(R.id.movieOverview);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
        }
    }
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.movie_list, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        Movies movie = moviesList.get(position);
        holder.movieTitle.setText(movie.getmTitle());
        holder.movieOverview.setText(movie.getmOverview());

        //check for device orientation
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                Picasso.with(context).load(movie.getmPosterPath())
                        .placeholder(R.drawable.placeholer_movie)
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.movieImage);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                Picasso.with(context).load(movie.getmBackdropPath())
                        .placeholder(R.drawable.placeholer_movie_land)
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(holder.movieImage);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void clear() {
        moviesList.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Movies> list) {
        moviesList.addAll(list);
        notifyDataSetChanged();
    }
}
