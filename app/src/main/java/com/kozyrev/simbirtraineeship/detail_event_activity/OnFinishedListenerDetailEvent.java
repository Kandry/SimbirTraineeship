package com.kozyrev.simbirtraineeship.detail_event_activity;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;

public interface OnFinishedListenerDetailEvent extends OnFinishedListenerEvents {

    void onFinished(Event event);
}
