package com.kozyrev.simbirtraineeship.detail_event_activity;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;

public interface Model {

    void getEventDetails(OnFinishedListenerDetailEvent onFinishedListener, int id);

    //void getEventDetailsAsyncTask(OnFinishedListenerEvents onFinishedListener);

    //void getEventDetailsExecutor(OnFinishedListenerEvents onFinishedListener);

    //void getEventDetailsIntentService(OnFinishedListenerEvents onFinishedListener);

    //void updateEvent(Event event);
}
