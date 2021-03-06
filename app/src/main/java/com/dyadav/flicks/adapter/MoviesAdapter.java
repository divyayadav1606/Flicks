package com.dyadav.flicks.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyadav.flicks.R;
import com.dyadav.flicks.activity.YoutubeActivity;
import com.dyadav.flicks.model.Movies;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import okhttp3.OkHttpClient;

public class MoviesAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Movies> moviesList;
    private Context context;
    private final int POPULAR_MOVIE = 0, REGULAR_MOVIE = 1;

    public MoviesAdapter(Context context, ArrayList<Movies> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    /**Heterogenous Layout**/
    @Override
    public int getItemViewType(int position) {
        if (moviesList.get(position).getmVoteAverage() > 5) {
            return POPULAR_MOVIE;
        } else {
            return REGULAR_MOVIE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        Context context = parent.getContext();

        switch (viewType) {
            case POPULAR_MOVIE:
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_popular, parent, false);
                viewHolder = new PopularMovieViewHolder(v1);
                break;

            case REGULAR_MOVIE:
            default:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_regular, parent, false);
                viewHolder = new RegularMovieViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Movies movie = moviesList.get(position);
        OkHttpClient client = new OkHttpClient();
        Picasso picasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(client)).build();
        if (movie != null) {
            switch (viewHolder.getItemViewType()) {
                case POPULAR_MOVIE:
                    PopularMovieViewHolder vh1 = (PopularMovieViewHolder) viewHolder;

                    vh1.getMovieTitle().setText(movie.getmTitle());
                    picasso.with(context).load(movie.getmBackdropPath())
                            .placeholder(R.drawable.placeholer_movie)
                            .transform(new RoundedCornersTransformation(10, 10))
                            .into(vh1.getBackdropImage());
                    vh1.getPlayButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Open youtube with the first trailer
                            Intent i = new Intent(context, YoutubeActivity.class);
                            i.putExtra("id", movie.getmId());
                            context.startActivity(i);
                        }
                    });
                    break;
                case REGULAR_MOVIE:
                default: {
                    RegularMovieViewHolder vh2 = (RegularMovieViewHolder) viewHolder;
                    vh2.getTitle().setText(movie.getmTitle());
                    vh2.getOverview().setText(movie.getmOverview());

                    switch (context.getResources().getConfiguration().orientation) {
                        case Configuration.ORIENTATION_PORTRAIT:
                            picasso.with(context).load(movie.getmPosterPath())
                                    .placeholder(R.drawable.placeholer_movie)
                                    .transform(new RoundedCornersTransformation(10, 10))
                                    .into(vh2.getMoviePoster());
                            break;
                        case Configuration.ORIENTATION_LANDSCAPE:
                            picasso.with(context).load(movie.getmBackdropPath())
                                    .placeholder(R.drawable.placeholer_movie_land)
                                    .transform(new RoundedCornersTransformation(10, 10))
                                    .into(vh2.getMoviePoster());
                            break;
                    }
                }
            }
        }
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
