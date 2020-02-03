package com.kozyrev.simbirtraineeship.base.finished_listeners;

import com.kozyrev.simbirtraineeship.base.OnFinishedListener;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface OnFinishedListenerEvents extends OnFinishedListener {

        void onFinished(List<Event> events);
}

