package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities.MoviesDBNetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMovieData();
    }

    private void loadMovieData() {
        new FetchMoviesData().execute("test");
    }

    public class FetchMoviesData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            if(params.length == 0)
                return null;

            String movieId = params[0];

            MoviesDBNetworkUtils.buildImageUrl(movieId);

            return null;
        }
    }
}
