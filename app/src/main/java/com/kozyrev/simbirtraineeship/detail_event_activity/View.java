package com.kozyrev.simbirtraineeship.detail_event_activity;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Event;

public interface View extends ViewBase {

    void setDataToViews(Event event);

    void showEmptyView();

    void hideEmptyView();
}
