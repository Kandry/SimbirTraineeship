package com.kozyrev.simbirtraineeship.search_fragment.search_events_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

public class SearchEventsFragmentPresenter implements Presenter, OnFinishedListenerEvents {

    private View searchEventsFragmentView;
    private Model searchEventsFragmentModel;

    public SearchEventsFragmentPresenter(SearchEventsFragmentView searchEventsFragmentView){
        this.searchEventsFragmentView = searchEventsFragmentView;
        this.searchEventsFragmentModel = new SearchEventsFragmentModel();
    }

    @Override
    public void requestDataFromFile() {
        if (searchEventsFragmentView != null){
            searchEventsFragmentView.showEmptyView();
        }
        searchEventsFragmentModel.getEvents(this);
    }

    @Override
    public void onFinished(List<Event> events) {
        if (searchEventsFragmentView != null){
            searchEventsFragmentView.setDataToRecyclerView(events);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (searchEventsFragmentView != null){
            searchEventsFragmentView.hideProgress();
            searchEventsFragmentView.hideEmptyView();
            searchEventsFragmentView.onResponseFailure(throwable);
        }
    }

    @Override
    public void onDestroy() {
        this.searchEventsFragmentView = null;
    }

    List<Event> filterEvens(List<Event> events, String searchText){
        List<Event> resultEvents = new ArrayList<>();
        for (Event event: events) {
            if (event.getName().toLowerCase().contains(searchText.toLowerCase())) resultEvents.add(event);
        }
        return resultEvents;
    }
}
