package com.udacity.kechagiaskonstantinos.popularmoviesapp.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by KechagiasKonstantinos on 05/03/2018.
 */

public class Movie implements Parcelable{

    private Long movieId;
    private String posterPath;
    private String backdropPath;
    private String title;
    private Date releaseDate;
    private Double voteAverage;
    private String plotSynopsis;

    public Movie() {
    }

    public Movie(Long movieId, String posterPath, String backdropPath, String title, Date releaseDate, Double voteAverage, String plotSynopsis) {
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    public Movie(Parcel in) {
        this.movieId = in.readLong();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.title = in.readString();
        //Because in parcel we store date in millis.
        this.releaseDate = new Date(in.readLong());
        this.voteAverage = in.readDouble();
        this.plotSynopsis = in.readString();
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.movieId);
        parcel.writeString(this.posterPath);
        parcel.writeString(this.backdropPath);
        parcel.writeString(this.title);
        parcel.writeLong(this.releaseDate.getTime());
        parcel.writeDouble(this.voteAverage);
        parcel.writeString(this.plotSynopsis);
    }
}
