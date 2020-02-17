package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface OnFinishedListenerNews extends OnFinishedListenerEvents {

    void onFinished(List<Event> events, List<Category> categories);
}
