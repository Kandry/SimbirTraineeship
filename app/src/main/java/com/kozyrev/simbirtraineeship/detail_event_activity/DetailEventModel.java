package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.async_tasks.EventsTask;
import com.kozyrev.simbirtraineeship.utils.broadcast_receivers.EventsBroadcastReceiver;
import com.kozyrev.simbirtraineeship.utils.executors.ExecutorEventsResult;
import com.kozyrev.simbirtraineeship.utils.intent_service.EventsIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailEventModel implements Model {

    private EventsBroadcastReceiver eventsBroadcastReceiver = new EventsBroadcastReceiver();

    @Override
    public void getEventDetails(OnFinishedListenerEvents onFinishedListener) {
        List<Event> events = JSONHelper.getEvents();
        onFinishedListener.onFinished(events);
    }

    @Override
    public void getEventDetailsAsyncTask(OnFinishedListenerEvents onFinishedListener) {
        EventsTask eventsTask = new EventsTask(onFinishedListener);
        eventsTask.execute();
    }

    @Override
    public void getEventDetailsExecutor(OnFinishedListenerEvents onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Event> events = JSONHelper.getEvents();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EventBus.getDefault().post(new ExecutorEventsResult(onFinishedListener, events));
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorEventsResult executorEventsResult){
        EventBus.getDefault().unregister(this);
        executorEventsResult.finish();
    }

    @Override
    public void getEventDetailsIntentService(OnFinishedListenerEvents onFinishedListener) {
        Context context = HelpingApplication.getAppContext();
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.events_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EVENTS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        eventsBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(eventsBroadcastReceiver, intentFilter);
    }

    @Override
    public void updateEvent(Event event) {

    }
}
