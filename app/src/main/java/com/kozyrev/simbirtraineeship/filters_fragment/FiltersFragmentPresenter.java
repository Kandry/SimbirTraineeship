package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public class FiltersFragmentPresenter implements FiltersFragmentContract.Presenter, FiltersFragmentContract.Model.OnFinishedListener {

    private FiltersFragmentContract.View filtersFragmentView;
    private FiltersFragmentContract.Model filtersFragmentModel;

    FiltersFragmentPresenter(FiltersFragmentContract.View filtersFragmentView, Context context){
        this.filtersFragmentView = filtersFragmentView;
        this.filtersFragmentModel = new FiltersFragmentModel(context);
    }

    @Override
    public void onFinished(List<Category> categories) {
        filtersFragmentView.setDataToRecyclerView(categories);
    }

    @Override
    public void onFailure(Throwable throwable) {
        filtersFragmentView.onResponseFailure(throwable);
    }

    @Override
    public void requestDataFromFile() {
        filtersFragmentModel.getFilters(this);
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
