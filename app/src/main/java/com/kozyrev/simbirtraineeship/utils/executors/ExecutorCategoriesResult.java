package com.kozyrev.simbirtraineeship.utils.executors;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public class ExecutorCategoriesResult {
    private OnFinishedListenerCategories onFinishedListener;
    private List<Category> categories;

    public ExecutorCategoriesResult(OnFinishedListenerCategories onFinishedListener, List<Category> categories){
        this.onFinishedListener = onFinishedListener;
        this.categories = categories;
    }

    public void finishCategories(){
        onFinishedListener.onFinished(categories);
    }
}