package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KechagiasKonstantinos on 05/03/2018.
 */

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private Movie mMovie;

    public final String DATE_FORMAT_DETAILS = "EEEE dd MMMM yyyy";


    @BindView(R.id.tv_title_value) TextView tvTitleValue;
    @BindView(R.id.tv_description_value) TextView tvDescValue;
    @BindView(R.id.tv_release_date_value) TextView tvReleaseDateValue;
    @BindView(R.id.iv_poster) ImageView ivPoster;
    @BindView(R.id.rb_rating) RatingBar rbRating;

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
            }
        }
    }
}
