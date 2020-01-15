package com.kozyrev.simbirtraineeship.utils.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EventsIntentService extends IntentService {

    public EventsIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String fileName = intent.getStringExtra(Constants.EXTRA_KEY_IN);
        Log.d("COUNT_EV1", "isEmpty: " + fileName);

        JSONHelper jsonHelper = new JSONHelper();
        String json = jsonHelper.readJson(getApplicationContext(), fileName, new StringBuilder());
        Type type = new TypeToken<List<Event>>(){}.getType();
        List<Event> events = new Gson().fromJson(json, type);

        Log.d("COUNT_EV1", "isEmpty: " + events.toString());

        if (events == null) events = new ArrayList<>();

        Intent responseIntent = new Intent();
        responseIntent.setAction(Constants.ACTION_INTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putParcelableArrayListExtra(Constants.EXTRA_KEY_OUT, (ArrayList<Event>) events);
        sendBroadcast(responseIntent);

        Log.d("COUNT_EV1", "isEmpty: " + events.toString());
    }
}
