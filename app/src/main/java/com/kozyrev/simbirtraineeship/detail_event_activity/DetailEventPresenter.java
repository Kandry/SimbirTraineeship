package com.kozyrev.simbirtraineeship.detail_event_activity;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;

import java.io.IOException;
import java.util.List;

public class DetailEventPresenter implements Presenter, OnFinishedListenerEvents {

    private View detailEventView;
    private Model detailEventModel;

    private int id;
    private Event finalEvent = null;

    DetailEventPresenter(View detailEventView){
        this.detailEventView = detailEventView;
        this.detailEventModel = new DetailEventModel();
    }

    @Override
    public void requestEventData(int id) {
        if (detailEventView != null){
            detailEventView.showEmptyView();
            detailEventView.showProgress();
        }
        this.id = id;

        detailEventModel.getEventDetails(this);
        //detailEventModel.getEventDetailsAsyncTask(this);
        //detailEventModel.getEventDetailsExecutor(this);
        //detailEventModel.getEventDetailsIntentService(this);
    }

    @Override
    public void onFinished(List<Event> events) {
        checkEvents(id, events);
        if (detailEventView != null && finalEvent != null){
            detailEventView.hideProgress();
            detailEventView.hideEmptyView();
            detailEventView.setDataToViews(finalEvent);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (detailEventView != null){
            detailEventView.hideProgress();
            detailEventView.hideEmptyView();
            detailEventView.onResponseFailure(throwable);
        }
    }

    @Override
    public void onDestroy() {
        detailEventView = null;
    }


    private void checkEvents(int id, List<Event> events){
        if (events != null) {
            for (Event event : events) {
                if (event.getId() == id) {
                    finalEvent = event;
                    return;
                }
            }
        } else this.onFailure(new IOException("События не загружены"));
    }
}
