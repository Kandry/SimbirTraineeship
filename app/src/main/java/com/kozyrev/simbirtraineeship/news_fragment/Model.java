package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface Model {

    void getEvents(OnFinishedListenerEvents onFinishedListener);

    void getEventsAsyncTask(OnFinishedListenerEvents onFinishedListener);

    void getEventsExecutors(OnFinishedListenerEvents onFinishedListener);

    void getEventsIntentService(OnFinishedListenerEvents onFinishedListener);

    List<Category> getCategories();

    void clearCategories();
}
