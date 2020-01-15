package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class FiltersFragmentModel implements FiltersFragmentContract.Model {
    private Context context;
    private JSONHelper jsonHelper = new JSONHelper();

    FiltersFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getFilters(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(jsonHelper.getCategories(context, context.getString(R.string.categories_filename)));
    }

    @Override
    public void setFilters(List<Category> categories) {
        jsonHelper.setCategories(context, categories);
    }
}
