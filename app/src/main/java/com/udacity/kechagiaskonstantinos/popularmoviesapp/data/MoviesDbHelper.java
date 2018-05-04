package com.udacity.kechagiaskonstantinos.popularmoviesapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDbHelper extends SQLiteOpenHelper {

    // The name of the database
    private static final String DATABASE_NAME = "moviesDb.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;

    // Constructor
    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Called when the tasks database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tasks table (careful to follow SQL formatting rules)
//        final String CREATE_TABLE_MOVIE = "CREATE TABLE "  + MoviesContract.MovieEntry.TABLE_NAME + " (" +
//                MoviesContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY, " +
//                MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
//                MoviesContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
//                MoviesContract.MovieEntry.COLUMN_BACK_DROP_PATH + " TEXT, " +
//                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +
//                MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL, " +
//                MoviesContract.MovieEntry.COLUMN_PLOT_SYNOPSIS + " TEXT, " +
//                MoviesContract.MovieEntry.COLUMN_IS_FAVORITE + " NUMERIC);";
//
//        final String CREATE_TABLE_MOVIES_REVIEWS = "CREATE TABLE "  + MoviesContract.MovieReviewEntry.TABLE_NAME + " (" +
//                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_ID + " INTEGER PRIMARY KEY, " +
//                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_AUTHOR + " TEXT, " +
//                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_CONTENT + " TEXT, " +
//                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_URL + " TEXT, " +
//                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_MOVIE_ID + " INTEGER, " +
//                " FOREIGN KEY ("+MoviesContract.MovieReviewEntry.COLUMN_MOVIE_REVIEW_MOVIE_ID+") REFERENCES "+MoviesContract.MovieEntry.TABLE_NAME+"("+MoviesContract.MovieEntry.COLUMN_MOVIE_ID+"));";
//
//        final String CREATE_TABLE_MOVIES_VIDEOS = "CREATE TABLE "  + MoviesContract.MovieVideoEntry.TABLE_NAME + " (" +
//                MoviesContract.MovieVideoEntry.COLUMN_VIDEO_ID + " INTEGER PRIMARY KEY, " +
//                MoviesContract.MovieVideoEntry.COLUMN_VIDEO_SITE + " TEXT, " +
//                MoviesContract.MovieVideoEntry.COLUMN_VIDEO_TYPE + " TEXT, " +
//                MoviesContract.MovieVideoEntry.COLUMN_VIDEO_MOVIE_ID + " INTEGER, " +
//                " FOREIGN KEY ("+MoviesContract.MovieVideoEntry.COLUMN_VIDEO_MOVIE_ID+") REFERENCES "+MoviesContract.MovieEntry.TABLE_NAME+"("+MoviesContract.MovieEntry.COLUMN_MOVIE_ID+"));";
//
//        db.execSQL(CREATE_TABLE_MOVIE);
//        db.execSQL(CREATE_TABLE_MOVIES_REVIEWS);
//        db.execSQL(CREATE_TABLE_MOVIES_VIDEOS);

        final String CREATE_TABLE_MOVIE =
                "CREATE TABLE "  + MoviesContract.FavoriteMoviesEntry.TABLE_NAME + " (" +
                MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID + " INTEGER PRIMARY KEY, " +
                MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_TITLE + " TEXT);";

        db.execSQL(CREATE_TABLE_MOVIE);

    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieReviewEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieVideoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavoriteMoviesEntry.TABLE_NAME);
        onCreate(db);
    }
}

