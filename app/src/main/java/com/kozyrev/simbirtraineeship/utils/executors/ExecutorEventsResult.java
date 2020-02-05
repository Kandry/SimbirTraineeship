package com.kozyrev.simbirtraineeship.utils.executors;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public class ExecutorEventsResult {
    private OnFinishedListenerEvents onFinishedListener;
    private List<Event> events;

    public ExecutorEventsResult(OnFinishedListenerEvents onFinishedListener, List<Event> events){
        this.onFinishedListener = onFinishedListener;
        this.events = events;
    }

    public void finish(){
        onFinishedListener.onFinished(events);
    }
}
