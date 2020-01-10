package com.kozyrev.simbirtraineeship.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JSONHelper {

    public enum BackThreadType {NONE, ASYNCTASK, EXECUTOR, INTENTSERVICE}

    private static final String APP_PREFERENCES = "prefs";
    private static final String APP_PREFERENCES_JSON = "json";

    public static List<Category> getCategories(@NotNull Context context, String fileName, @NotNull BackThreadType backThreadType) {
        switch (backThreadType) {
            case ASYNCTASK:
                CategoriesTask categoriesTask = new CategoriesTask(context, fileName);
                categoriesTask.execute();
                try {
                    return categoriesTask.get();
                } catch (ExecutionException | InterruptedException e){
                    e.printStackTrace();
                }
            case NONE:
            default:
                SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                StringBuilder jsonBuilder = new StringBuilder(prefs.getString(APP_PREFERENCES_JSON, ""));
                String json;

                if (jsonBuilder.length() < 1)
                    json = readJson(context, fileName, new StringBuilder());
                else
                    json = jsonBuilder.toString();

                Type type = new TypeToken<List<Category>>() {}.getType();
                return new Gson().fromJson(json, type);
        }
    }

    public static void setCategories(@NotNull Context context, List<Category> categories) {
        StringBuilder json = new StringBuilder();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        json.append(gson.toJson(categories));
        Log.d("JSONTAG", json.toString());

        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(APP_PREFERENCES_JSON, json.toString());
        editor.apply();
    }

    public static void clearCategories(@NotNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static List<Event> getEvents(Context context, String fileName, @NotNull BackThreadType backThreadType) {
        switch (backThreadType){
            case ASYNCTASK:
                EventsTask eventsTask = new EventsTask(context, fileName);
                eventsTask.execute();
                try {
                    return eventsTask.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            case NONE:
            default:
                String json = readJson(context, fileName, new StringBuilder());
                Type type = new TypeToken<List<Event>>(){}.getType();
                return new Gson().fromJson(json, type);
        }
    }

    @Nullable
    private static String readJson(@NotNull Context context, String fileName, StringBuilder json){
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

    static class EventsTask extends AsyncTask<Void, Void, List<Event>> {

        @SuppressLint("StaticFieldLeak")
        Context context;
        String fileName;

        EventsTask(Context context, String fileName){
            this.context = context;
            this.fileName = fileName;
        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            String json = readJson(context, fileName, new StringBuilder());
            Type type = new TypeToken<List<Event>>(){}.getType();
            return new Gson().fromJson(json, type);
        }
    }

    static class CategoriesTask extends AsyncTask<Void, Void, List<Category>>{

        @SuppressLint("StaticFieldLeak")
        Context context;
        String fileName;

        CategoriesTask(Context context, String fileName){
            this.context = context;
            this.fileName = fileName;
        }

        @Override
        protected List<Category> doInBackground(Void... voids){
            SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            StringBuilder jsonBuilder = new StringBuilder(prefs.getString(APP_PREFERENCES_JSON, ""));
            String json;

            if (jsonBuilder.length() < 1)
                json = readJson(context, fileName, new StringBuilder());
            else
                json = jsonBuilder.toString();

            Type type = new TypeToken<List<Category>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
    }
}