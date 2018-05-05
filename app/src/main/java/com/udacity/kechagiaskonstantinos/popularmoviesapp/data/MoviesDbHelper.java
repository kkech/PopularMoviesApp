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
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavoriteMoviesEntry.TABLE_NAME);
        onCreate(db);
    }
}

