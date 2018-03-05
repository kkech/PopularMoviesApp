package com.udacity.kechagiaskonstantinos.popularmoviesapp.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by KechagiasKonstantinos on 05/03/2018.
 */

public class Movie implements Serializable{

    private Long movieId;
    private String posterPath;
    private String title;
    private Date releaseDate;
    private Double voteAverage;
    private String plotSynopsis;

    public Movie() {
    }

    public Movie(Long movieId, String posterPath, String title, Date releaseDate, Double voteAverage, String plotSynopsis) {
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
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
}
