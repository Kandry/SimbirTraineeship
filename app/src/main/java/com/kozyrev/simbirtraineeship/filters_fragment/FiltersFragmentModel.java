package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class FiltersFragmentModel implements FiltersFragmentContract.Model {
    private Context context;

    FiltersFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getFilters(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getCategories(context, context.getString(R.string.categories_filename)));
    }

    @Override
    public void setFilters(List<Category> categories) {
        JSONHelper.setCategories(context, categories);
    }
}
