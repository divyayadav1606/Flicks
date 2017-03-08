package com.dyadav.flicks.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movies {
    private int mId;
    private String mPosterPath;
    private String mOverview;
    private String mTitle;
    private String mBackdropPath;
    private double mPopularity;
    private double mVoteAverage;
    private boolean mIsVideo;

    /** Other fields from movie list not used
        1. adult
        2. release_date
        3. genre_ids
        4. original_title
        5. original_language
        6. vote_count
     **/

    public Movies(JSONObject movieObject) throws JSONException {
        mId = movieObject.getInt("id");
        mPosterPath = movieObject.getString("poster_path");
        mOverview = movieObject.getString("overview");
        mTitle = movieObject.getString("title");
        mBackdropPath = movieObject.getString("backdrop_path");
        mPopularity = movieObject.getDouble("popularity");
        mVoteAverage = movieObject.getDouble("vote_average");
        mIsVideo = movieObject.getBoolean("video");
    }

    public static ArrayList<Movies> fromJSONArray(JSONArray movieArray) {
        ArrayList<Movies> movies = new ArrayList<>();

        for (int i = 0; i < movieArray.length(); i++) {
            try {
                movies.add(new Movies(movieArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public int getmId() {
        return mId;
    }

    public String getmPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w500%s", mPosterPath);
    }

    public String getmOverview() {
        return mOverview;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780%s", mBackdropPath);
    }

    public double getmPopularity() {
        return mPopularity;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public boolean ismIsVideo() {
        return mIsVideo;
    }
}
