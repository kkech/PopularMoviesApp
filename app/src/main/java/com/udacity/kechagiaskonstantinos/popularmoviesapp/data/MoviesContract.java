package com.udacity.kechagiaskonstantinos.popularmoviesapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Date;

public class MoviesContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.udacity.kechagiaskonstantinos.popularmoviesapp";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract

//    // This is the path for the "movies" directory
//    public static final String PATH_MOVIES = "movies";
//
//    public static final class MovieEntry implements BaseColumns{
//
//        // TaskEntry content URI = base content URI + path
//        public static final Uri CONTENT_URI =
//                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
//
//        // Task table and column names
//        public static final String TABLE_NAME = "movies";
//
//        // Since MovieEntry implements the interface "BaseColumns", it has an automatically produced
//        public static final String COLUMN_MOVIE_ID = "movie_id";
//        public static final String COLUMN_TITLE = "title";
//        public static final String COLUMN_POSTER_PATH = "poster_path";
//        public static final String COLUMN_BACK_DROP_PATH = "back_drop_path";
//        public static final String COLUMN_RELEASE_DATE = "release_date";
//        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
//        public static final String COLUMN_PLOT_SYNOPSIS = "plot_synopsis";
//        public static final String COLUMN_IS_FAVORITE = "is_favorite";
//    }
//
//    // This is the path for the "movie_review" directory
//    public static final String PATH_MOVIE_REVIEW = "movie_review";
//
//    public static final class MovieReviewEntry implements BaseColumns{
//
//        // TaskEntry content URI = base content URI + path
//        public static final Uri CONTENT_URI =
//                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_REVIEW).build();
//
//        // Task table and column names
//        public static final String TABLE_NAME = "moviesReviews";
//
//        // Since MovieEntry implements the interface "BaseColumns", it has an automatically produced
//        public static final String COLUMN_MOVIE_REVIEW_ID = "movie_review_id";
//        public static final String COLUMN_MOVIE_REVIEW_AUTHOR = "author";
//        public static final String COLUMN_MOVIE_REVIEW_CONTENT = "content";
//        public static final String COLUMN_MOVIE_REVIEW_URL = "url";
//        public static final String COLUMN_MOVIE_REVIEW_MOVIE_ID = "movie_id";
//    }
//
//    // This is the path for the "movie_video" directory
//    public static final String PATH_MOVIE_VIDEO = "movie_video";
//
//    public static final class MovieVideoEntry implements BaseColumns{
//
//        // TaskEntry content URI = base content URI + path
//        public static final Uri CONTENT_URI =
//                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_VIDEO).build();
//
//        // Task table and column names
//        public static final String TABLE_NAME = "moviesVideos";
//
//        // Since MovieEntry implements the interface "BaseColumns", it has an automatically produced
//        public static final String COLUMN_VIDEO_ID = "movie_video_id";
//        public static final String COLUMN_VIDEO_SITE = "site";
//        public static final String COLUMN_VIDEO_TYPE = "type";
//        public static final String COLUMN_VIDEO_MOVIE_ID = "movie_id";
//    }


    //////
    public static final String PATH_FAVORITE_MOVIE = "favoriteMovies";

    public static final class FavoriteMoviesEntry implements BaseColumns{

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_MOVIE).build();

        // Task table and column names
        public static final String TABLE_NAME = "favoriteMovies";

        // Since MovieEntry implements the interface "BaseColumns", it has an automatically produced
        public static final String COLUMN_FAVORITE_MOVIE_ID = "movie_id";
        public static final String COLUMN_FAVORITE_MOVIE_TITLE = "site";
    }


}
