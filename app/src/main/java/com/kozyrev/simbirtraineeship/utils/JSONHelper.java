package com.kozyrev.simbirtraineeship.utils;

import android.content.Context;

import com.google.gson.Gson;
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

    public static List<Category> getCategories(Context context, String fileName) {
        StringBuilder json = new StringBuilder();

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

        Type type = new TypeToken<List<Category>>(){}.getType();
        return new Gson().fromJson(json.toString(), type);
    }

    public static List<Event> getEvents(Context context, String fileName) {
        StringBuilder json = new StringBuilder();

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

        Type type = new TypeToken<List<Event>>(){}.getType();
        return new Gson().fromJson(json.toString(), type);
    }
}
