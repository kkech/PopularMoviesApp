package com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities;

import android.util.Log;

import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by KechagiasKonstantinos on 26/02/2018.
 */

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Movie[] getMoviesFromJSON(String moviesResponse){
        ArrayList<Movie> returnArray = new ArrayList<Movie>();
        try {
            JSONObject moviesTotal = new JSONObject(moviesResponse);
            JSONArray resultsArray = moviesTotal.getJSONArray("results");
            for(int i = 0;i < resultsArray.length();i++){
                JSONObject resultObject=resultsArray.getJSONObject(i);
                String stringDate = resultObject.optString("release_date");
                Date date;
                if(stringDate.isEmpty()){
                    date = null;
                }else {
                    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
                    date = parser.parse(stringDate);
                }
                Movie movie = new Movie(resultObject.optLong("id"), MoviesDBNetworkUtils.buildImageUrl(resultObject.optString("poster_path")).toString(), resultObject.optString("title"), date, resultObject.optDouble("vote_average"), resultObject.optString("overview"));
                returnArray.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnArray.toArray(new Movie[0]);
    }

    public static String[] getImagePaths(String configurationResponse){
        try {
            JSONObject configurationTotal = new JSONObject(configurationResponse);
            JSONObject configurationImages = configurationTotal.getJSONObject("images");
            String secureBaseUrl = configurationImages.optString("secure_base_url");
            //In the future this can be taken from the configurationResponse
            String size = "w185";

            Log.v(TAG, "Json Parsed");

            return new String[]{secureBaseUrl,size};
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"Cannot parse Json");
        }
        return null;
    }
}
