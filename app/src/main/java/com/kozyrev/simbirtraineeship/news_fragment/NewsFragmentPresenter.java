package com.kozyrev.simbirtraineeship.news_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewsFragmentPresenter implements Presenter, OnFinishedListenerEvents {

    private View newsFragmentView;
    private Model newsFragmentModel;

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
