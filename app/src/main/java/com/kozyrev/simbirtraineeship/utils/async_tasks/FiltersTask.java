package com.kozyrev.simbirtraineeship.utils.async_tasks;

import android.os.AsyncTask;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;

import java.util.List;

public class FiltersTask extends AsyncTask<Void, Void, List<Category>> {

    private OnFinishedListenerCategories onFinishedListener;

    public FiltersTask(OnFinishedListenerCategories onFinishedListener){
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    protected List<Category> doInBackground(Void... voids){
        List<Category> categories = JSONHelper.getCategories();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return categories;
    }

    @Override
    protected void onPostExecute(List<Category> categories){
        super.onPostExecute(categories);
        onFinishedListener.onFinished(categories);
    }
}
