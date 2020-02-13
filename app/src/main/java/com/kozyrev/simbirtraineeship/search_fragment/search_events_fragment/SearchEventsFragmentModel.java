package com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment;

import android.util.Log;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.network.NetHelper;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.executors.ExecutorEventsResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchEventsFragmentModel implements Model {

    @Override
    public void getEvents(OnFinishedListenerEvents onFinishedListener) {
        //onFinishedListener.onFinished(JSONHelper.getEvents());
        getNetEvents(onFinishedListener);
    }

    private void getEventsExecutors(OnFinishedListenerEvents onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            NetHelper
                    .getEvents()
                    .subscribe(
                            events -> EventBus.getDefault().post(new ExecutorEventsResult(onFinishedListener, events)),
                            e -> EventBus.getDefault().post(new ExecutorEventsResult(onFinishedListener, JSONHelper.getEvents())));
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorEventsResult executorEventsResult){
        EventBus.getDefault().unregister(this);
        executorEventsResult.finish();
    }

    private void getNetEvents(OnFinishedListenerEvents onFinishedListener){
        NetHelper
                .getEvents()
                .subscribe(
                        events -> onFinishedListener.onFinished(events),
                        e -> getEventsExecutors(onFinishedListener));
    }
}
