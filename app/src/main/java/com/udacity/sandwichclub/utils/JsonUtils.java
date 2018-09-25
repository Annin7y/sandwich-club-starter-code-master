package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    private static final String KEY_MAIN_NAME = "mainName";

    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";

    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";

    private static final String KEY_DESCRIPTION = "description";

    private static final String KEY_IMAGE = "image";

    private static final String KEY_INGREDIENTS = "ingredients";

    public JsonUtils() {
    }

    public static Sandwich parseSandwichJson(String json) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }
     //   Sandwich sandwich = null;
        try {

            // Create a JSONObject from the JSON file
            JSONObject jsonObject = new JSONObject(json);

            //fetch JSONObject named name
            JSONObject objectName = jsonObject.getJSONObject("name");

            // Extract the value for the key called "main_name"
            String mainName = "";
            if (objectName.has("mainName")) {
                mainName = objectName.optString(KEY_MAIN_NAME);
            }

            JSONArray alsoKnownAsArray = jsonObject.optJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownData = new ArrayList<String>(alsoKnownAsArray.length());
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownData.add(alsoKnownAsArray.getString(i));
            }

            String placeOfOrigin = "";
            if (jsonObject.has("placeOfOrigin")) {
                placeOfOrigin = jsonObject.optString(KEY_PLACE_OF_ORIGIN);
            }

            String description = "";
            if (jsonObject.has("description")) {
                description = objectName.optString(KEY_DESCRIPTION);
            }

            String image = "";
            if (objectName.has("image")) {
                image = objectName.optString(KEY_IMAGE);
            }

            //https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist/17037364
            JSONArray ingredientsArray = jsonObject.optJSONArray(KEY_INGREDIENTS);
            List<String> ingredientsData = new ArrayList<String>();
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    //JSONObject jsonIngredientObject = ingredientsArray.getJSONObject(i);
                    ingredientsData.add(ingredientsArray.getString(i));
                }
            }

           Sandwich sandwich = new Sandwich(mainName, alsoKnownData, placeOfOrigin, description, image, ingredientsData);

            // Return the list of sandwiches
           return sandwich;

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing sandwich JSON results", e);
            // return null;
        }
        return null;
    }}
