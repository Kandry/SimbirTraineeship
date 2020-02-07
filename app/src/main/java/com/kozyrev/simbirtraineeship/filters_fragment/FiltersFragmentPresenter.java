package com.kozyrev.simbirtraineeship.filters_fragment;

import android.util.SparseBooleanArray;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.HashMap;
import java.util.List;

public class FiltersFragmentPresenter implements Presenter, OnFinishedListenerCategories {

    private View filtersFragmentView;
    private Model filtersFragmentModel;
    private SparseBooleanArray sparseBooleanArray = null;

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
        filtersFragmentModel.getFilters(this);
    }

    @Override
    public void onFinished(List<Category> categories) {

        HashMap<Category, Boolean> categoriesHM = new HashMap<>(categories.size());
        if (sparseBooleanArray != null){
            for (Category category: categories) {
                categoriesHM.put(category, sparseBooleanArray.get(category.getId()));
            }
        } else {
            for (Category category: categories) {
                categoriesHM.put(category, true);
            }
        }

        if (filtersFragmentView != null){
            filtersFragmentView.hideProgress();
            filtersFragmentView.hideEmptyView();
            filtersFragmentView.setDataToRecyclerView(categoriesHM);
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
    public void onDestroy() {
        this.filtersFragmentView = null;
    }

    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }

    public void setSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
        this.sparseBooleanArray = sparseBooleanArray;
    }
}
