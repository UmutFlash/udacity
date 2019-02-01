package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject forecast = new JSONObject(json);
            JSONObject name = forecast.getJSONObject("name");

            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(parseJsonArrayToList(name.getJSONArray("alsoKnownAs")));
            sandwich.setPlaceOfOrigin(forecast.getString("placeOfOrigin"));
            sandwich.setIngredients( parseJsonArrayToList(forecast.getJSONArray("ingredients")));
            sandwich.setDescription(forecast.getString("description"));
            sandwich.setImage(forecast.getString("image"));

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
        }
        return sandwich;
    }

    private static List<String> parseJsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
