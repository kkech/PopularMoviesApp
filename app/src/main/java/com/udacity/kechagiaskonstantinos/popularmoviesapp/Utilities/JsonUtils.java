package com.udacity.kechagiaskonstantinos.popularmoviesapp.Utilities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KechagiasKonstantinos on 26/02/2018.
 */

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static String[] getImageUrls(String configurationResponse){

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
