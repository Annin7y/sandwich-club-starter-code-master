package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String sandwichJSON)
    {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(sandwichJSON)) {
            return null;
        }
        ArrayList<Sandwich> sandwiches= new ArrayList<>();
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(sandwichJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of features (or movies).
            JSONArray sandwichArray = baseJsonResponse.getJSONArray("results");

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing sandwich JSON results", e);
        }
            return null;
    }
}
