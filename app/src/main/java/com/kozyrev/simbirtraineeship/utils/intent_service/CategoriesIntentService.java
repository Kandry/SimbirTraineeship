package com.kozyrev.simbirtraineeship.utils.intent_service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoriesIntentService extends IntentService {

    public CategoriesIntentService(String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String fileName = intent.getStringExtra(Constants.EXTRA_KEY_IN);

        String json = JSONHelper.readJson(getApplicationContext(), fileName, new StringBuilder());
        Type type = new TypeToken<List<Category>>(){}.getType();
        ArrayList<Category> categories = new Gson().fromJson(json, type);

        Intent responseIntent = new Intent();
        responseIntent.setAction(Constants.ACTION_EVENTSINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putParcelableArrayListExtra(Constants.EXTRA_KEY_OUT, categories);
    }
}
