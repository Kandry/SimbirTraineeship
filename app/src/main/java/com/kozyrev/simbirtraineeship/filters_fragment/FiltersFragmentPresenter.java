package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public class FiltersFragmentPresenter implements Presenter, OnFinishedListenerCategories {

    private View filtersFragmentView;
    private Model filtersFragmentModel;

    FiltersFragmentPresenter(View filtersFragmentView){
        this.filtersFragmentView = filtersFragmentView;
        this.filtersFragmentModel = new FiltersFragmentModel();
    }

    @Override
    public void requestDataFromFile() {
        if (filtersFragmentView != null){
            filtersFragmentView.showEmptyView();
            filtersFragmentView.showProgress();
        }
        //filtersFragmentModel.getFilters(this);
        //filtersFragmentModel.getFiltersAsyncTask(this);
        //filtersFragmentModel.getFiltersExecutors(this);
        filtersFragmentModel.getFiltersIntentService(this);
    }

    @Override
    public void onFinished(List<Category> categories) {
        if (filtersFragmentView != null){
            filtersFragmentView.hideProgress();
            filtersFragmentView.hideEmptyView();
            filtersFragmentView.setDataToRecyclerView(categories);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (filtersFragmentView != null){
            filtersFragmentView.hideProgress();
            filtersFragmentView.hideEmptyView();
            filtersFragmentView.onResponseFailure(throwable);
        }
    }

    @Override
    public void setDataToFile(List<Category> categories){
        filtersFragmentModel.setFilters(categories);
    }

    @Override
    public void onDestroy() {
        this.filtersFragmentView = null;
    }
}
