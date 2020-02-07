package com.kozyrev.simbirtraineeship.filters_fragment;

import com.kozyrev.simbirtraineeship.base.ViewBase;
import com.kozyrev.simbirtraineeship.model.Category;

import java.util.HashMap;
import java.util.List;

public interface View extends ViewBase {

    void setDataToRecyclerView(HashMap<Category, Boolean> categoriesHM);

    void showEmptyView();

    void hideEmptyView();
}
