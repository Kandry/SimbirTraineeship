package com.kozyrev.simbirtraineeship.news_fragment;

import android.util.SparseBooleanArray;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.network.NetHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        newsFragmentModel.getEvents(this);
    }

    public void setCategories(List<Category> getCategories){
        categories = new SparseBooleanArray();
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
        if (categories == null) getListCategories(events);
        else dataFinishProgress(events);
    }

    private void getListCategories(List<Event> events){
        NetHelper
                .getCategories()
                .subscribe(new Observer<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(List<Category> categories) {
                        setCategories(categories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setCategories(newsFragmentModel.getCategories());
                    }

                    @Override
                    public void onComplete() {
                        dataFinishProgress(events);
                    }
                });
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
