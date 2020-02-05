package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface View extends ViewBase {

    void setDataToRecyclerView (List<Event> events);

    void showEmptyView();

    void hideEmptyView();
}
