package com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by kechagiaskonstantinos on 26/02/2018.
 */

public class MoviesDBNetworkUtils {


    private static final String TAG = MoviesDBNetworkUtils.class.getSimpleName();

    public static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    public static String IMAGE_BASE_URL;

    public static final String API_PARAM = "api_key";


    public static URL buildImageUrl(String imageId){

        try {
            String configurationResponse = getResponseFromHttpUrl(buildConfigurationUrl());
            System.out.println(configurationResponse);

            String[] imagesUrls = JsonUtils.getImageUrls(configurationResponse);
            if((imagesUrls == null) || imagesUrls.length != 2)
                return null;

            Uri builtUri = Uri.parse(new StringBuffer(imagesUrls[0]).append(imagesUrls[1]).append("/").append("kqjL17yufvn9OVLyXYpvtyrFfak.jpg").toString()).buildUpon()
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Log.v(TAG, "Built URI for Images " + url);

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

        Log.v(TAG, "Built URI for configuration" + url);

        return url;


    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
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
