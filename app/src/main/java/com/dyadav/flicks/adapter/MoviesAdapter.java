package com.dyadav.flicks.adapter;

import android.content.Context;
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
        Picasso.with(context).load(movie.getmPosterPath()).into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
