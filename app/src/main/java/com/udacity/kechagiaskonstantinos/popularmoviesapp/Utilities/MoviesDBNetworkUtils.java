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
import java.util.Scanner;

import static com.udacity.kechagiaskonstantinos.popularmoviesapp.MainActivity.POPULAR;

/**
 * Created by kechagiaskonstantinos on 26/02/2018.
 */

public class MoviesDBNetworkUtils {


    private static final String TAG = MoviesDBNetworkUtils.class.getSimpleName();

    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    private static final String API_PARAM = "api_key";
    private static final String LANG_PARAM = "language";
    private static final String SORT_PARAM = "sort_by";
    private static final String PAGE_PARAM = "page";

    public static Movie[] getMovies(@MainActivity.MovieSort String movieSort){
        try {
            String movieResponse = getResponseFromHttpUrl(buildMovieUrl(movieSort));
            return JsonUtils.getMoviesFromJSON(movieResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URL buildMovieUrl(@MainActivity.MovieSort String movieSort){
        Uri builtUri;
        if(movieSort.equals(POPULAR))
            builtUri = Uri.parse("https://api.themoviedb.org/3/discover/movie").buildUpon()
                .appendQueryParameter(API_PARAM,"eb68acebf9644349b99549ab101d948c")
                .appendQueryParameter(LANG_PARAM,"en-US")
                .appendQueryParameter(SORT_PARAM,"popularity.desc")
                .appendQueryParameter(PAGE_PARAM,"1")
                .build();
        else
            builtUri = Uri.parse("https://api.themoviedb.org/3/discover/movie").buildUpon()
                    .appendQueryParameter(API_PARAM,"eb68acebf9644349b99549ab101d948c")
                    .appendQueryParameter(LANG_PARAM,"en-US")
                    .appendQueryParameter(SORT_PARAM,"vote_average.desc")
                    .appendQueryParameter(PAGE_PARAM,"1")
                    .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildImageUrl(String imagePath){

        try {
            String configurationResponse = getResponseFromHttpUrl(buildConfigurationUrl());
            String[] imagesUrls = JsonUtils.getImagePaths(configurationResponse);
            if((imagesUrls == null) || imagesUrls.length != 2)
                return null;

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
        }
        return null;
    }

    private static URL buildConfigurationUrl(){
        Uri builtUri = Uri.parse(MOVIE_DB_URL + "configuration").buildUpon()
                .appendQueryParameter(API_PARAM, "eb68acebf9644349b99549ab101d948c")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
        } finally {
            urlConnection.disconnect();
        }
    }
}
