package com.kozyrev.simbirtraineeship.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONHelper {

    private static final String APP_PREFERENCES = "prefs";
    private static final String APP_PREFERENCES_JSON = "json";

    public static List<Category> getCategories(@NonNull Context context, String fileName) {
      /*  switch (backThreadType) {
            case ASYNCTASK:
                CategoriesTask categoriesTask = new CategoriesTask(context, fileName);
                categoriesTask.execute();
                try {
                    return categoriesTask.get();
                } catch (ExecutionException | InterruptedException e){
                    e.printStackTrace();
                }
            case INTENTSERVICE:
                Intent intentCategories = new Intent(context, CategoriesIntentService.class);
                intentCategories.putExtra(Constants.EXTRA_KEY_IN, fileName);
                context.startService(intentCategories);

                CategoriesBroadcastReceiver categoriesBroadcastReceiver = new CategoriesBroadcastReceiver();

                IntentFilter intentFilter = new IntentFilter(Constants.ACTION_INTENTSERVICE);
                intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
                context.registerReceiver(categoriesBroadcastReceiver, intentFilter);

                List<Category> categories = categoriesBroadcastReceiver.getCategories();

                int count = 0;
                while (categories == null && count < 1000) {
                    categories = categoriesBroadcastReceiver.getCategories();
                    Log.d("COUNT_CAT", "count " + count);
                    count++;
                }
                return categories;
            case NONE:
            default:*/
                SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                StringBuilder jsonBuilder = new StringBuilder(prefs.getString(APP_PREFERENCES_JSON, ""));
                String json;

                if (jsonBuilder.length() < 1)
                    json = readJson(context, fileName, new StringBuilder());
                else
                    json = jsonBuilder.toString();

                Type type = new TypeToken<List<Category>>() {}.getType();
                return new Gson().fromJson(json, type);
      //  }
    }

    public static void setCategories(@NonNull Context context, List<Category> categories) {
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

    public static void clearCategories(@NonNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static List<Event> getEvents(Context context, String fileName) {
        String json = readJson(context, fileName, new StringBuilder());
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

   /* class CategoriesTask extends AsyncTask<Void, Void, List<Category>>{

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
    }*/
/*
    class CategoriesBroadcastReceiver extends BroadcastReceiver{

        private List<Category> categories = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            categories = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
            //Log.d("COUNT_EV1", "isEmpty: " + categories.toString());
        }

        public List<Category> getCategories() {
            return categories;
        }
    }*/
}