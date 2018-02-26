package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities.MoviesDBNetworkUtils;

import java.net.URL;

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

            String imageId = params[0];

            URL imageUrl = MoviesDBNetworkUtils.buildImageUrl(imageId);

            return imageUrl.toString();
        }

        @Override
        protected void onPostExecute(String imageUrl) {
            ImageView imageView = findViewById(R.id.imageView);

            Picasso.with(getBaseContext()).load(imageUrl).into(imageView);
        }
    }
}
