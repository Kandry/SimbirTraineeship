package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface Model {

    void getFilters(OnFinishedListenerCategories onFinishedListener);

    void getFiltersAsyncTask(OnFinishedListenerCategories onFinishedListener);

    void getFiltersExecutors(OnFinishedListenerCategories onFinishedListener);

    void getFiltersIntentService(OnFinishedListenerCategories onFinishedListener);

    void setFilters(List<Category> categories);
}
