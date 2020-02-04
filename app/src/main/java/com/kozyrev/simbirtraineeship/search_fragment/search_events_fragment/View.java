package com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

public interface View extends ViewBase {

    void setDataToRecyclerView (List<Event> events);

    void showEmptyView();

    void hideEmptyView();
}
