package com.kozyrev.simbirtraineeship.news_fragment;

import android.content.Context;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class NewsFragmentModel implements NewsFragmentContract.Model {

    private Context context;

    NewsFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getEvents(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getEvents(context, context.getString(R.string.events_filename)));
    }

    @Override
    public void getEventsAsyncTask(OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getEventsExecutors(OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getEventsIntentService(OnFinishedListener onFinishedListener) {

    }

    @Override
    public List<Category> getCategories() {
        return JSONHelper.getCategories(context, context.getString(R.string.categories_filename));
    }

    @Override
    public void clearCategories() {
        JSONHelper.clearCategories(context);
    }

}
