package com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KechagiasKonstantinos on 26/02/2018.
 */

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static String[] getImageRelativeURLs(String moviesResponse){
        ArrayList<String> returnArray = new ArrayList<String>();
        try {
            JSONObject moviesTotal = new JSONObject(moviesResponse);
            JSONArray resultsArray = moviesTotal.getJSONArray("results");
            for(int i = 0;i < resultsArray.length();i++){
                JSONObject resultObject=resultsArray.getJSONObject(i);
                returnArray.add(resultObject.optString("poster_path"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return returnArray.toArray(new String[0]);
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
