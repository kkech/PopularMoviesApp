package com.udacity.kechagiaskonstantinos.popularmoviesapp.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kechagiaskonstantinos on 05/04/2018.
 */

public class MovieVideo implements Parcelable {

    private String movieKey;
    private String site;
    private String type;


    public MovieVideo() {
    }

    public MovieVideo(String movieKey, String site, String type) {
        this.movieKey = movieKey;
        this.site = site;
        this.type = type;
    }

    public MovieVideo(Parcel in) {
        this.movieKey = in.readString();
        this.site = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieVideo createFromParcel(Parcel in) {
            return new MovieVideo(in);
        }

        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };

    public String getMovieKey() {
        return movieKey;
    }

    public void setMovieKey(String movieKey) {
        this.movieKey = movieKey;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.movieKey);
        parcel.writeString(this.site);
        parcel.writeString(this.type);
    }
}
