package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.content.Context;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class DetailEventModel implements DetailEventContract.Model {

    private Context context;

    public DetailEventModel(Context context){
        this.context = context;
    }

    @Override
    public void getEventDetails(OnFinishedListener onFinishedListener, int id) {
        List<Event> events = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
        Event finalEvent = null;
        for(Event event: events){
            if (event.getId() == id) finalEvent = event;
        }
        onFinishedListener.onFinished(finalEvent);
    }

    @Override
    public void updateEvent(Event event) {

    }
}
