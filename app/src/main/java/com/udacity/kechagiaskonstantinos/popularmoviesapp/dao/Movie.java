package com.udacity.kechagiaskonstantinos.popularmoviesapp.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Boolean isFavorite;
    private ArrayList<MovieVideo> videosList;
    private ArrayList<MovieReview> reviewList;

    public Movie() {
    }

    public Movie(Long movieId) {
        this.movieId = movieId;
    }

    public Movie(Long movieId, String posterPath, String backdropPath, String title, Date releaseDate, Double voteAverage, String plotSynopsis, Boolean isFavorite, ArrayList<MovieVideo> videosList, ArrayList<MovieReview> reviewList) {
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
        this.isFavorite = isFavorite;
        this.videosList = videosList;
        this.reviewList = reviewList;
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
        this.isFavorite = in.readByte() != 0;
        this.videosList = in.createTypedArrayList(MovieVideo.CREATOR);
        this.reviewList = in.createTypedArrayList(MovieReview.CREATOR);
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

//    public Boolean getFavorite() {
//        return isFavorite;
//    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public ArrayList<MovieVideo> getVideosList() {
        return videosList;
    }

    public void setVideosList(ArrayList<MovieVideo> videosList) {
        this.videosList = videosList;
    }

    public ArrayList<MovieReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<MovieReview> reviewList) {
        this.reviewList = reviewList;
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
        return this.hashCode();
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
        parcel.writeByte((byte) (isFavorite ? 1 : 0));     //if myBoolean == true, byte == 1
        parcel.writeTypedList(this.videosList);
        parcel.writeTypedList(this.reviewList);
    }
}
