package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String sandwichJSON)
    {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(sandwichJSON)) {
            return null;
        }

        return null;
    }
}
