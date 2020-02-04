package com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

public class SearchEventsFragmentModel implements Model {

    @Override
    public void getEvents(OnFinishedListenerEvents onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getEvents());
    }
}
