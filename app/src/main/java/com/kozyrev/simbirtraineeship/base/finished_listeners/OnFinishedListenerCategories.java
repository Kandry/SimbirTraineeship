package com.kozyrev.simbirtraineeship.base.finished_listeners;

import com.kozyrev.simbirtraineeship.base.OnFinishedListener;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface OnFinishedListenerCategories extends OnFinishedListener {

    void onFinished(List<Category> categories);
}
