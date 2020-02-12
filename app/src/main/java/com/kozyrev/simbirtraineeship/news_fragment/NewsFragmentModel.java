package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.room.Room;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.db.DB;
import com.kozyrev.simbirtraineeship.model.Category;
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

public class NewsFragmentModel implements Model {

    private EventsBroadcastReceiver newsBroadcastReceiver = new EventsBroadcastReceiver();
    private DB db = DB.getDB();

    @Override
    public void getEvents(OnFinishedListenerNews onFinishedListener, int categoriesSize) {
        //onFinishedListener.onFinished(JSONHelper.getEvents());
        //getEventsAsyncTask(this);
        //getEventsExecutors(this);
        //getEventsIntentService(onFinishedListener);
      /*  db
                .getEventDAO()
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Event>>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("NewsFragmentModelRx", "Subscribe");
                    }

                    @Override
                    public void onSuccess(List<Event> events) {
                        Log.i("NewsFragmentModelRx", "Next");
                        if (events.size() > 0) onFinishedListener.onFinished(events);
                        else getNetEvents(onFinishedListener);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("NewsFragmentModelRx", "Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("NewsFragmentModelRx", "Complete");
                        getNetEvents(onFinishedListener);
                    }
                });*/
      getNetEvents(onFinishedListener, categoriesSize);

      //else
    }

    private void getEventsAsyncTask(OnFinishedListenerNews onFinishedListener) {
        EventsTask newsTask = new EventsTask(onFinishedListener);
        newsTask.execute();
    }

    private void getEventsExecutors(OnFinishedListenerNews onFinishedListener, int categoriesSize) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Event> news = JSONHelper.getEvents();
          /*  try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            EventBus.getDefault().post(new ExecutorEventsResult(onFinishedListener, news, categoriesSize));
        });
    }

    private void getEventsIntentService(OnFinishedListenerNews onFinishedListener) {
        Context context = HelpingApplication.getAppContext();
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.events_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EVENTS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        newsBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(newsBroadcastReceiver, intentFilter);
    }

    private void getNetEvents(OnFinishedListenerNews onFinishedListener, int categoriesSize){
        NetHelper
                .getEvents()
                .subscribe(new Observer<List<Event>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(List<Event> events) {
                        //NetHelper.getCategories().subscribe(categories -> {
                          //  db.getCategoryDAO().addAll(categories);
                          //  db.getEventDAO().addAll(events);
                        if (categoriesSize > 0) {
                            Log.d("NewsFragmentModel1", "events");
                            onFinishedListener.onFinished(events);
                        }
                        else {
                            Log.d("NewsFragmentModel1", "events+");
                            NetHelper.getCategories().subscribe(
                                categories -> {onFinishedListener.onFinished(events, categories);},
                                e -> {onFinishedListener.onFinished(events, JSONHelper.getCategories());});
                        }
                       // });
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d("NewsFragmentModel1", e.getMessage());
                        //NetHelper.getCategories().subscribe(categories -> {
                         //   db.getCategoryDAO().addAll(categories);
                        getEventsExecutors(onFinishedListener, categoriesSize);
                       // });
                    }

                    @Override
                    public void onComplete() {}
                });
    }
/*
    public List<Category> getCategories() {
        return JSONHelper.getCategories();
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorEventsResult executorEventsResult){
        EventBus.getDefault().unregister(this);
        executorEventsResult.finish();
    }
}