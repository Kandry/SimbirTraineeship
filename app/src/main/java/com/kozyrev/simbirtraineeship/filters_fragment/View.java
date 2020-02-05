package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

public interface View extends ViewBase {

    void setDataToRecyclerView(List<Category> categories);

    void showEmptyView();

    void hideEmptyView();
}
