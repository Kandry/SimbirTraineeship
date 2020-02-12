package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.room.Room;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.db.DB;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.network.NetHelper;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.async_tasks.EventsTask;
import com.kozyrev.simbirtraineeship.utils.broadcast_receivers.EventsBroadcastReceiver;
import com.kozyrev.simbirtraineeship.utils.executors.ExecutorEventsResult;
import com.kozyrev.simbirtraineeship.utils.intent_service.EventsIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailEventModel implements Model {

    private EventsBroadcastReceiver eventsBroadcastReceiver = new EventsBroadcastReceiver();
    private DB db = DB.getDB();

    @Override
    public void getEventDetails(OnFinishedListenerEvents onFinishedListener) {
        //onFinishedListener.onFinished(JSONHelper.getEvents());
        //getEventDetailsAsyncTask(onFinishedListener);
        //getEventDetailsExecutor(onFinishedListener);
        //getEventDetailsIntentService(onFinishedListener);
       /* db
                .getEventDAO()
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Event>>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Event> events) {
                        if (events != null) onFinishedListener.onFinished(events);
                        else getNetEventDetails(onFinishedListener);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getNetEventDetails(onFinishedListener);
                    }
                });*/
        getNetEventDetails(onFinishedListener);
    }

    private void getEventDetailsAsyncTask(OnFinishedListenerEvents onFinishedListener) {
        EventsTask eventsTask = new EventsTask(onFinishedListener);
        eventsTask.execute();
    }

    private void getEventDetailsExecutor(OnFinishedListenerEvents onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Event> events = JSONHelper.getEvents();
           // NetHelper.getCategories().subscribe(categories -> {
                //db.getCategoryDAO().addAll(categories);
                //db.getEventDAO().addAll(events);
                EventBus.getDefault().post(new ExecutorEventsResult(onFinishedListener, events));
           // });

           /* try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorEventsResult executorEventsResult){
        EventBus.getDefault().unregister(this);
        executorEventsResult.finish();
    }

    private void getEventDetailsIntentService(OnFinishedListenerEvents onFinishedListener) {
        Context context = HelpingApplication.getAppContext();
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.events_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EVENTS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        eventsBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(eventsBroadcastReceiver, intentFilter);
    }

    private void getNetEventDetails(OnFinishedListenerEvents onFinishedListener){
        NetHelper
                .getEvents()
                .subscribe(new Observer<List<Event>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Event> events) {
                       /* NetHelper.getCategories().subscribe(categories -> {
                            db.getCategoryDAO().addAll(categories);
                            db.getEventDAO().addAll(events);*/
                            onFinishedListener.onFinished(events);
                       // });
                    }

                    @Override
                    public void onError(Throwable e) {
                        getEventDetailsExecutor(onFinishedListener);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
