package com.kozyrev.simbirtraineeship.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONHelper {

    private static final String TAG = "JSONHelper";

    private static final String APP_PREFERENCES = "prefs";
    private static final String APP_PREFERENCES_JSON = "json";

    public static List<Category> getCategories() {
        Context context = HelpingApplication.getAppContext();
        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        StringBuilder jsonBuilder = new StringBuilder(prefs.getString(APP_PREFERENCES_JSON, ""));
        String json;

        if (jsonBuilder.length() < 1)
            json = readJson(context, context.getString(R.string.categories_filename), new StringBuilder());
        else
            json = jsonBuilder.toString();

        Type type = new TypeToken<List<Category>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    public static void setCategories(List<Category> categories) {
        Context context =  HelpingApplication.getAppContext();
        StringBuilder json = new StringBuilder();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        json.append(gson.toJson(categories));
        Log.d(TAG, json.toString());

        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(APP_PREFERENCES_JSON, json.toString());
        editor.apply();
    }

    public static void clearCategories() {
        Context context =  HelpingApplication.getAppContext();
        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static List<Event> getEvents() {
        Context context =  HelpingApplication.getAppContext();
        String json = readJson(context, context.getString(R.string.events_filename), new StringBuilder());
        Type type = new TypeToken<List<Event>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    @Nullable
    public static String readJson(@NonNull Context context, String fileName, StringBuilder json){
        try{
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json.toString();
    }
}