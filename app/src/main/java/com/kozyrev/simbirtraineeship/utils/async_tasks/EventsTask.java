package com.kozyrev.simbirtraineeship.utils.async_tasks;

import android.os.AsyncTask;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class EventsTask extends AsyncTask<Void, Void, List<Event>> {

    private OnFinishedListenerEvents onFinishedListener;

    public EventsTask(OnFinishedListenerEvents onFinishedListener){
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    protected List<Event> doInBackground(Void... voids) {
        List<Event> events = JSONHelper.getEvents();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        super.onPostExecute(events);
        onFinishedListener.onFinished(events);
    }
}
