package com.kozyrev.simbirtraineeship.news_fragment;


public interface Model {

    void getEvents(OnFinishedListenerNews onFinishedListener, int categoriesSize);

    //void getEventsAsyncTask(OnFinishedListenerEvents onFinishedListener);

    //void getEventsExecutors(OnFinishedListenerEvents onFinishedListener);

    //void getEventsIntentService(OnFinishedListenerEvents onFinishedListener);
}
