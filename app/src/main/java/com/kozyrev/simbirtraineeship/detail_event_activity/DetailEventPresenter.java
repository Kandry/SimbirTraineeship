package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.content.Context;

import com.kozyrev.simbirtraineeship.model.Event;

public class DetailEventPresenter implements DetailEventContract.Presenter, DetailEventContract.Model.OnFinishedListener {

    private DetailEventContract.View detailEventView;
    private DetailEventContract.Model detailEventModel;

    public DetailEventPresenter(DetailEventContract.View detailEventView, Context context){
        this.detailEventView = detailEventView;
        this.detailEventModel = new DetailEventModel(context);
    }

    @Override
    public void requestEventData(int id) {
        detailEventModel.getEventDetails(this, id);
    }

    @Override
    public void onFinished(Event event) {
        detailEventView.setDataToViews(event);
    }

    @Override
    public void onFailure(Throwable throwable) {
        detailEventView.onResponseFailure(throwable);
    }

    @Override
    public void onDestroy() {
        detailEventView = null;
    }
}
