package com.kozyrev.simbirtraineeship.news_fragment;

import android.util.SparseBooleanArray;

import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewsFragmentPresenter implements Presenter, OnFinishedListenerNews {

    private View newsFragmentView;
    private Model newsFragmentModel;
    private SparseBooleanArray categories = new SparseBooleanArray();

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
        newsFragmentModel.getEvents(this, categories.size());
    }

    public void setCategories(List<Category> getCategories){
        for (Category category: getCategories) {
            categories.put(category.getId(), true);
        }
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
        dataFinishProgress(events);
    }

    @Override
    public void onFinished(List<Event> events, List<Category> categories) {
        setCategories(categories);
        dataFinishProgress(events);
    }

    private void dataFinishProgress(List<Event> events){
        if (newsFragmentView != null) {
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
