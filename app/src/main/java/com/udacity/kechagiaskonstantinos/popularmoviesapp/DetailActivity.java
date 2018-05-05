package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.MovieReview;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.MovieVideo;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.data.MoviesContract;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KechagiasKonstantinos on 05/03/2018.
 */

public class DetailActivity extends AppCompatActivity{

    private static final String TAG = DetailActivity.class.getSimpleName();
    private Movie mMovie;

    public final String DATE_FORMAT_DETAILS = "EEEE dd MMMM yyyy";

    public final String YOUTUBE = "YouTube";

    @BindView(R.id.tv_title_value) TextView tvTitleValue;
    @BindView(R.id.tv_description_value) TextView tvDescValue;
    @BindView(R.id.tv_release_date_value) TextView tvReleaseDateValue;
    @BindView(R.id.iv_poster) ImageView ivPoster;
    @BindView(R.id.rb_rating) RatingBar rbRating;
    @BindView(R.id.bt_play_trailer) ImageButton btPlayTrailer;
    @BindView(R.id.ctv_isFavorite) CheckedTextView ctvIsFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("Movie")) {
                mMovie = (Movie)intentThatStartedThisActivity.getParcelableExtra("Movie");

                ButterKnife.bind(this);

                tvTitleValue.setText(mMovie.getTitle());
                tvDescValue.setText(mMovie.getPlotSynopsis());

                if(mMovie.getReleaseDate() != null) {
                    SimpleDateFormat parser = new SimpleDateFormat(DATE_FORMAT_DETAILS);
                    tvReleaseDateValue.setText(parser.format(mMovie.getReleaseDate()).toString());
                }
                rbRating.setRating(((Double)(mMovie.getVoteAverage()/2.0)).floatValue());
                if((mMovie.getBackdropPath() == null) || (mMovie.getBackdropPath().isEmpty()))
                    Picasso.with(getApplicationContext()).load(mMovie.getPosterPath()).into(ivPoster);
                else
                    Picasso.with(getApplicationContext()).load(mMovie.getBackdropPath()).into(ivPoster);

                btPlayTrailer.setOnClickListener((View view)->this.onClickWatchVideo(view));

                Cursor c = getContentResolver().query(MoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                        null,
                        MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID + "=?",
                        new String[] {mMovie.getMovieId().toString()},
                        MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID);
                if(c == null){
                    Log.e(TAG,"Cursor is null");
                    return;
                }
                if(c.getCount() > 0){
                    ctvIsFavorite.setCheckMarkDrawable(R.drawable.ic_heart_full);
                    ctvIsFavorite.setChecked(true);
                }else{
                    ctvIsFavorite.setCheckMarkDrawable(R.drawable.ic_heart_outline);
                    ctvIsFavorite.setChecked(false);
                }
                ctvIsFavorite.setOnClickListener((View view)->this.onClickFavorite(view));
                c.close();

                LinearLayout ll = (LinearLayout) findViewById(R.id.insideLayout);
                TextView tv1 = null;
                for(MovieReview movieReview : mMovie.getReviewList()){
                    tv1 = new TextView(this);
                    tv1.setText(movieReview.getAuthor() + " :" + movieReview.getContent() + "\n");
                    ll.addView(tv1);
                }
            }
        }
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public void onClickWatchVideo(View view) {
        for(MovieVideo movieVideo : mMovie.getVideosList()){
            if(movieVideo.getSite().equals(YOUTUBE) && movieVideo.getType().equals("Trailer")){
                watchYoutubeVideo(this,movieVideo.getMovieKey());
                return;
            }
        }
        Toast.makeText(this, "No trailer for this movie",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickFavorite(View view){
        Cursor c = getContentResolver().query(MoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                null,
                MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID + "=?",
                new String[] {mMovie.getMovieId().toString()},
                MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID);
        if(c == null) {
            Log.e(TAG, "Cursor is null");
            return;
        }
        if (c.getCount() > 0) {
            ctvIsFavorite.setCheckMarkDrawable(R.drawable.ic_heart_outline);
            ctvIsFavorite.setChecked(false);
            Uri uri = MoviesContract.FavoriteMoviesEntry.CONTENT_URI;
            uri = uri.buildUpon().appendPath(mMovie.getMovieId().toString()).build();
            //Delete a single row of data using a ContentResolver
            getContentResolver().delete(uri, null, null);

        }else{
            ctvIsFavorite.setCheckMarkDrawable(R.drawable.ic_heart_full);
            ctvIsFavorite.setChecked(true);

            ContentValues contentValues = new ContentValues();
            // Put the task description and selected mPriority into the ContentValues
            contentValues.put(MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_ID, mMovie.getMovieId());
            contentValues.put(MoviesContract.FavoriteMoviesEntry.COLUMN_FAVORITE_MOVIE_TITLE, mMovie.getTitle());
            // Insert the content values via a ContentResolver
            Uri uri = getContentResolver().insert(MoviesContract.FavoriteMoviesEntry.CONTENT_URI, contentValues);
        }
        c.close();
    }
}
