package com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities;

import android.net.Uri;
import android.util.Log;

import com.udacity.kechagiaskonstantinos.popularmoviesapp.MainActivity;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.udacity.kechagiaskonstantinos.popularmoviesapp.MainActivity.POPULAR;

/**
 * Created by kechagiaskonstantinos on 26/02/2018.
 */

public class MoviesDBNetworkUtils {


    private static final String TAG = MoviesDBNetworkUtils.class.getSimpleName();

    private static final String MOVIE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    private static final String CONFIGURATION_SUB_URL = "configuration";

    private static final String API_PARAM = "api_key";
    private static final String API_KEY = "eb68acebf9644349b99549ab101d948c";
    private static final String LANG_PARAM = "language";
    private static final String ENG_LANG_VALUE = "en-US";
    private static final String SORT_PARAM = "sort_by";
    private static final String POPULARITY_SORT_VALUE = "popularity.desc";
    private static final String RATE_SORT_VALUE = "vote_average.desc";
    private static final String PAGE_PARAM = "page";
    private static final String PAGE_VALUE = "1";

    private static String[] imagesUrls = null;

    public static ArrayList<Movie> getMovies(@MainActivity.MovieSort String movieSort){
        try {
            String movieResponse = getResponseFromHttpUrl(buildMovieUrl(movieSort));
            return JsonUtils.getMoviesFromJSON(movieResponse);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Cannot get movies");
        }
        return null;
    }

    private static URL buildMovieUrl(@MainActivity.MovieSort String movieSort){
        Uri builtUri;
        if(movieSort.equals(POPULAR))
            builtUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendQueryParameter(API_PARAM,API_KEY)
                .appendQueryParameter(LANG_PARAM,ENG_LANG_VALUE)
                .appendQueryParameter(SORT_PARAM,POPULARITY_SORT_VALUE)
                .appendQueryParameter(PAGE_PARAM,PAGE_VALUE)
                .build();
        else
            builtUri = Uri.parse(MOVIE_URL).buildUpon()
                    .appendQueryParameter(API_PARAM,API_KEY)
                    .appendQueryParameter(LANG_PARAM,ENG_LANG_VALUE)
                    .appendQueryParameter(SORT_PARAM,RATE_SORT_VALUE)
                    .appendQueryParameter(PAGE_PARAM,PAGE_VALUE)
                    .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG,"Unable to build Movie URL");
        }
        return url;
    }

    public static URL buildImageUrl(String imagePath){

        try {
            //To load imageUrls only one time because they not change
            if (imagesUrls == null){
                String configurationResponse = getResponseFromHttpUrl(buildConfigurationUrl());
                imagesUrls = JsonUtils.getImagePaths(configurationResponse);
                if ((imagesUrls == null) || imagesUrls.length != 2)
                    return null;
            }
            if(imagePath == null || imagePath.equals("null"))
                System.out.println("Aaaaaa");

            Uri builtUri = Uri.parse(new StringBuffer(imagesUrls[0]).append(imagesUrls[1]).append(imagePath).toString()).buildUpon()
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //Log.v(TAG, "Built URI for Images " + url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"Unable to build image URL");
        }
        return null;
    }

    private static URL buildConfigurationUrl(){
        Uri builtUri = Uri.parse(MOVIE_DB_URL + CONFIGURATION_SUB_URL).buildUpon()
                .appendQueryParameter(API_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG,"Unable to build configuration URL");
        }
        //Log.v(TAG, "Built URI for configuration" + url);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
