package com.kozyrev.simbirtraineeship.news_fragment;

import android.util.SparseBooleanArray;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewsFragmentPresenter implements Presenter, OnFinishedListenerEvents {

    private View newsFragmentView;
    private Model newsFragmentModel;
    private SparseBooleanArray categories = null;

    NewsFragmentPresenter(View newsFragmentView){
        this.newsFragmentView = newsFragmentView;
        this.newsFragmentModel = new NewsFragmentModel();
    }

    @Override
    public void requestDataFromFile() {
        if (newsFragmentView != null){
            newsFragmentView.showEmptyView();
            newsFragmentView.showProgress();
        }
        //newsFragmentModel.getEvents(this);
        //newsFragmentModel.getEventsAsyncTask(this);
        //newsFragmentModel.getEventsExecutors(this);
        newsFragmentModel.getEventsIntentService(this);
    }

    private SparseBooleanArray fillCategories(){
        List<Category> getCategories = newsFragmentModel.getCategories();
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (Category category: getCategories) {
            sparseBooleanArray.put(category.getId(), true);
        }
        return sparseBooleanArray;
    }

    List<Event> filterNews(List<Event> events, SparseBooleanArray categories) {
        List<Event> news = new ArrayList<>();

        for (Event event: events) {
            boolean inNews = categories.get(event.getCategory());

            if (!inNews && news.contains(event)){
                news.remove(event);
            }

            if (inNews && !news.contains(event)){
                news.add(event);
            }
        }

        return news;
    }

    @Override
    public void onFinished(List<Event> events) {
        if (categories == null) categories = fillCategories();
        if (newsFragmentView != null){
            newsFragmentView.hideProgress();
            newsFragmentView.hideEmptyView();
            newsFragmentView.setDataToRecyclerView(filterNews(events, categories));
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (newsFragmentView != null){
            newsFragmentView.hideProgress();
            newsFragmentView.hideEmptyView();
            newsFragmentView.onResponseFailure(throwable);
        }
    }

    @Override
    public void onDestroy() {
        this.newsFragmentView = null;
    }

    public SparseBooleanArray getCategories() {
        return categories;
    }

    public void setCategories(SparseBooleanArray categories) {
        this.categories = categories;
    }
}
