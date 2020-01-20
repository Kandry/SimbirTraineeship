package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewsFragmentPresenter implements NewsFragmentContract.Presenter, NewsFragmentContract.Model.OnFinishedListener {

    private NewsFragmentContract.View newsFragmentView;
    private NewsFragmentContract.Model newsFragmentModel;

    NewsFragmentPresenter(NewsFragmentContract.View newsFragmentView, Context context){
        this.newsFragmentView = newsFragmentView;
        this.newsFragmentModel = new NewsFragmentModel(context);
    }

    @Override
    public void requestDataFromFile() {
        if (newsFragmentView != null){
            newsFragmentView.showEmptyView();
            newsFragmentView.showProgress();
        }
        //newsFragmentModel.getEvents(this);
        newsFragmentModel.getEventsAsyncTask(this);
        //newsFragmentModel.getEventsExecutors(this);
        //newsFragmentModel.getEventsIntentService(this);
    }

    private List<Event> filterNews(List<Event> events) {
        List<Event> news = new ArrayList<>();

        List<Category> categories = newsFragmentModel.getCategories();
        for (Event event: events) {
            List<Integer> categoriesID = event.getCategoriesID();
            boolean inNews = false;

            for (Category category: categories) {
                if ((categoriesID.contains(category.getId())) && (category.isActive())) inNews = true;
            }

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
    public void clearCategories() {
        newsFragmentModel.clearCategories();
    }

    @Override
    public void onFinished(List<Event> events) {
        if (newsFragmentView != null){
            newsFragmentView.hideProgress();
            newsFragmentView.hideEmptyView();
            newsFragmentView.setDataToRecyclerView(filterNews(events));
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
}
