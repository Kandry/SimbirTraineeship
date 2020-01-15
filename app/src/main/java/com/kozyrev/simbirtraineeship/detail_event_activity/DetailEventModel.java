package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.io.IOException;
import java.util.List;

public class DetailEventModel implements DetailEventContract.Model {

    private Context context;

    public DetailEventModel(Context context) {
        this.context = context;
    }

    @Override
    public void getEventDetails(OnFinishedListener onFinishedListener, int id) {
        List<Event> events = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
        checkEvents(onFinishedListener, id, events);
    }

    @Override
    public void getEventDetailsAsyncTask(OnFinishedListener onFinishedListener, int id) {
        EventTask eventTask = new EventTask(context, onFinishedListener, id);
        eventTask.execute();
    }

    @Override
    public void getEventDetailsExecutor(OnFinishedListener onFinishedListener, int id) {

    }

    @Override
    public void getEventDetailsIntentService(OnFinishedListener onFinishedListener, int id) {

    }

    @Override
    public void updateEvent(Event event) {

    }

    private static void checkEvents(OnFinishedListener onFinishedListener, int id, List<Event> events){
        if (events != null) {
            Event finalEvent = null;
            for (Event event : events) {
                if (event.getId() == id) finalEvent = event;
            }
            onFinishedListener.onFinished(finalEvent);
        } else onFinishedListener.onFailure(new IOException("События не загружены"));
    }

    @SuppressLint("StaticFieldLeak")
    class EventTask extends AsyncTask<Void, Void, List<Event>> {

        private Context context;
        private DetailEventContract.Model.OnFinishedListener onFinishedListener;
        private int id;

        EventTask(Context context, DetailEventContract.Model.OnFinishedListener onFinishedListener, int id){
            this.context = context;
            this.onFinishedListener = onFinishedListener;
            this.id = id;
        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            List<Event> events = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
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
            DetailEventModel.checkEvents(onFinishedListener, id, events);
        }
    }
}
