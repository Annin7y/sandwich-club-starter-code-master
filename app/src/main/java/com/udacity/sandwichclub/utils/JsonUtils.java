package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    private static final String KEY_MAIN_NAME = "main_name";

    private static final String KEY_ALSO_KNOWN_AS = "also_known_as";

    private static final String KEY_PLACE_OF_ORIGIN = "place_of_origin";

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

        try {

            // Create a JSONObject from the JSON file
            JSONObject jsonObject = new JSONObject(json);

            //fetch JSONObject named name
            JSONObject objectName = jsonObject.getJSONObject("name");

            // Extract the value for the key called "main_name"
            String mainName = objectName.getString(KEY_MAIN_NAME);

            JSONArray alsoKnownAsArray = objectName.optJSONArray(KEY_ALSO_KNOWN_AS);
            for (int i = 0; i < (alsoKnownAsArray.length()); i++) {

                JSONObject alsoKnownObject = new JSONObject(alsoKnownAsArray.get(i).toString());
            }

            String placeOfOrigin = objectName.getString(KEY_PLACE_OF_ORIGIN);

            String description = objectName.getString(KEY_DESCRIPTION);

            String image = objectName.getString(KEY_IMAGE);

            List<Sandwich> ingredients = new ArrayList<>();
            JSONArray ingredientsArray = objectName.optJSONArray(KEY_INGREDIENTS);
            for (int i = 0; i < (ingredientsArray.length()); i++) {

                JSONObject ingredientsObject = new JSONObject(ingredientsArray.get(i).toString());
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            sandwiches.add(sandwich);


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing sandwich JSON results", e);
        }
        // Return the list of sandwiches
        return sandwiches;
    }
}
