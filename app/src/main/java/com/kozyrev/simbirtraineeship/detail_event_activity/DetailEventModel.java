package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.intent_service.EventsIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailEventModel implements DetailEventContract.Model {

    private Context context;
    private EventsBroadcastReceiver eventsBroadcastReceiver = new EventsBroadcastReceiver();

    DetailEventModel(Context context) {
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
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Event> events = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EventBus.getDefault().post(new ExecutorResult(onFinishedListener, id, events));
        });
    }

    @Override
    public void getEventDetailsIntentService(OnFinishedListener onFinishedListener, int id) {
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.events_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EVENTS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        eventsBroadcastReceiver.setData(onFinishedListener, id);
        context.registerReceiver(eventsBroadcastReceiver, intentFilter);
    }

    @Override
    public void updateEvent(Event event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorResult executorResult){
        EventBus.getDefault().unregister(this);
        executorResult.checkExecutorEvents();
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
        private OnFinishedListener onFinishedListener;
        private int id;

        EventTask(Context context, OnFinishedListener onFinishedListener, int id){
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

    class EventsBroadcastReceiver extends BroadcastReceiver {

        private OnFinishedListener onFinishedListener;
        private int id;

        public void setData(OnFinishedListener onFinishedListener, int id) {
            this.onFinishedListener = onFinishedListener;
            this.id = id;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Event> events = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
            finishedReceiver(onFinishedListener, id, events);
        }
    }

    private void finishedReceiver(OnFinishedListener onFinishedListener, int id, List<Event> events){
        context.unregisterReceiver(eventsBroadcastReceiver);
        DetailEventModel.checkEvents(onFinishedListener, id, events);
    }

    class ExecutorResult {
        private OnFinishedListener onFinishedListener;
        private int id;
        private List<Event> events;

        ExecutorResult(OnFinishedListener onFinishedListener, int id, List<Event> events){
            this.onFinishedListener = onFinishedListener;
            this.id = id;
            this.events = events;
        }

        void checkExecutorEvents(){
            checkEvents(onFinishedListener, id, events);
        }
    }
}
