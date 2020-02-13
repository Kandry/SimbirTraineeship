package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
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
        //getNetEvents(onFinishedListener, categoriesSize);

        db.getEventDAO()
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Event>>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Event> events) {
                        if ((events.size() > 0) && (categoriesSize > 0)) onFinishedListener.onFinished(events);
                        else getNetEvents(onFinishedListener, categoriesSize);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        getNetEvents(onFinishedListener, categoriesSize);
                    }
                });
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
                        if (categoriesSize > 0) {
                            onFinishedListener.onFinished(events);
                        }
                        else {
                            NetHelper.getCategories().subscribe(
                                categories -> {
                                    db.getCategoryDAO().addAll(categories);
                                    db.getEventDAO().addAll(events);
                                    onFinishedListener.onFinished(events, categories);
                                },
                                e -> {
                                    List<Category> categories = JSONHelper.getCategories();
                                    db.getCategoryDAO().addAll(categories);
                                    db.getEventDAO().addAll(events);
                                    onFinishedListener.onFinished(events, categories);
                                });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getEventsExecutors(onFinishedListener, categoriesSize);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorEventsResult executorEventsResult){
        EventBus.getDefault().unregister(this);
        executorEventsResult.finish();
    }
}