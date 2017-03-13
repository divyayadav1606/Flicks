package com.dyadav.flicks.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movies  implements Parcelable {
    private int mId;
    private String mPosterPath;
    private String mOverview;
    private String mTitle;
    private String mBackdropPath;
    private double mPopularity;
    private double mVoteAverage;
    private boolean mIsVideo;
    private String mReleaseDate;

    /** Other fields from movie list not used
        1. adult
        2. genre_ids
        3. original_title
        4. original_language
        5. vote_count
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
        mReleaseDate = movieObject.getString("release_date");
    }

    protected Movies(Parcel in) {
        mId = in.readInt();
        mPosterPath = in.readString();
        mOverview = in.readString();
        mTitle = in.readString();
        mBackdropPath = in.readString();
        mPopularity = in.readDouble();
        mVoteAverage = in.readDouble();
        mIsVideo = in.readByte() != 0;
        mReleaseDate = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

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
        return String.format("https://image.tmdb.org/t/p/w1280%s", mBackdropPath);
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

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mPosterPath);
        parcel.writeString(mOverview);
        parcel.writeString(mTitle);
        parcel.writeString(mBackdropPath);
        parcel.writeDouble(mPopularity);
        parcel.writeDouble(mVoteAverage);
        parcel.writeByte((byte) (mIsVideo ? 1 : 0));
        parcel.writeString(mReleaseDate);
    }
}
