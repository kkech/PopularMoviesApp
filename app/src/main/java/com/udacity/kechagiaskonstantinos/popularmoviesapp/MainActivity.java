package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities.MoviesDBNetworkUtils;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private MoviesAdapter mMoviesAdapter;
    private RecyclerView mRecyclerView;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    public static final String POPULAR = "Popular";
    public static final String RATE = "Rate";

    @StringDef({POPULAR, RATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MovieSort {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movies);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this,2);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mMoviesAdapter = new MoviesAdapter(this,this);
        mRecyclerView.setAdapter(mMoviesAdapter);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        loadMovieData(POPULAR);
    }

    private void loadMovieData(@MovieSort String movieSort) {
        showMovieDataView();
        if(movieSort.equals(POPULAR))
            new FetchMoviesData().execute(POPULAR);
        else
            new FetchMoviesData().execute(RATE);
    }

    /**
     * This method will make the View for the movie data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showMovieDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the weather
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(Movie movie) {
        //Log.i(TAG,movie.getMovieId().toString());
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("Movie", movie);
        startActivity(intentToStartDetailActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.moviesort, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_popular) {
            mMoviesAdapter.setMoviesData(null);
            loadMovieData(POPULAR);
            return true;
        }else if(id == R.id.sort_rate){
            mMoviesAdapter.setMoviesData(null);
            loadMovieData(RATE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FetchMoviesData extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if(params.length == 0)
                showErrorMessage();

            Movie[] mMovies;
            if(params[0].equals(POPULAR))
                mMovies = MoviesDBNetworkUtils.getMovies(POPULAR);
            else
                mMovies = MoviesDBNetworkUtils.getMovies(RATE);

            return mMovies;
        }

        @Override
        protected void onPostExecute(Movie[] imageUrls) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (imageUrls != null) {
                showMovieDataView();
                mMoviesAdapter.setMoviesData(imageUrls);
            }else
                showErrorMessage();
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
