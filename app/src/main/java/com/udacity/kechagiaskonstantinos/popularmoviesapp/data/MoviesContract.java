package com.udacity.kechagiaskonstantinos.popularmoviesapp.data;

import android.net.Uri;
import android.provider.BaseColumns;


public class MoviesContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.udacity.kechagiaskonstantinos.popularmoviesapp";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
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
