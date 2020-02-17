package com.kozyrev.simbirtraineeship.utils.executors;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.db.DB;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.network.NetHelper;
import com.kozyrev.simbirtraineeship.news_fragment.OnFinishedListenerNews;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class ExecutorEventsResult {
    private DB db = DB.getDB();

    private OnFinishedListenerEvents onFinishedListener;
    private List<Event> events;
    private int categoriesSize = 1;

    public ExecutorEventsResult(OnFinishedListenerEvents onFinishedListener, List<Event> events){
        this.onFinishedListener = onFinishedListener;
        this.events = events;
    }

    public ExecutorEventsResult(OnFinishedListenerNews onFinishedListener, List<Event> events, int categoriesSize){
        this.onFinishedListener = onFinishedListener;
        this.events = events;
        this.categoriesSize = categoriesSize;
    }

    public void finish(){
        if (categoriesSize > 0) { onFinishedListener.onFinished(events); }
        else NetHelper.getCategories().subscribe(
                categories ->
                {
                    db.getCategoryDAO().addAll(categories);
                    db.getEventDAO().addAll(events);
                    ((OnFinishedListenerNews)onFinishedListener).onFinished(events, categories);
                },
                e -> {
                    List<Category> categories = JSONHelper.getCategories();
                    db.getCategoryDAO().addAll(categories);
                    db.getEventDAO().addAll(events);
                    ((OnFinishedListenerNews)onFinishedListener).onFinished(events, categories);
                });
    }
}
