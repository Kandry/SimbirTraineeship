package com.kozyrev.simbirtraineeship.detail_event_activity;

import android.content.Context;
import android.os.Handler;

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
        if (detailEventView != null){
            detailEventView.showEmptyView();
            detailEventView.showProgress();
        }
        //detailEventModel.getEventDetails(this, id);
        //detailEventModel.getEventDetailsAsyncTask(this, id);
        //detailEventModel.getEventDetailsExecutor(this, id);
        detailEventModel.getEventDetailsIntentService(this, id);
    }

    @Override
    public void onFinished(Event event) {
        if (detailEventView != null){
            detailEventView.hideProgress();
            detailEventView.hideEmptyView();
        }
        detailEventView.setDataToViews(event);
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (detailEventView != null){
            detailEventView.hideProgress();
            detailEventView.hideEmptyView();
        }
        detailEventView.onResponseFailure(throwable);
    }

    @Override
    public void onDestroy() {
        detailEventView = null;
    }
}
