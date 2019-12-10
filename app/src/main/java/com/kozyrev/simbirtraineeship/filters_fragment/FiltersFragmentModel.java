package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.utils.JSONHelper;

public class FiltersFragmentModel implements FiltersFragmentContract.Model {

    private static final String FILE_NAME = "categories.json";
    private Context context;

    FiltersFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getFilters(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getCategories(context, FILE_NAME));
    }
}
