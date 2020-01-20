package com.kozyrev.simbirtraineeship.filters_fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.intent_service.CategoriesIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiltersFragmentModel implements FiltersFragmentContract.Model {

    private Context context;
    private CategoriesBroadcastReceiver categoriesBroadcastReceiver = new CategoriesBroadcastReceiver();

    FiltersFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getFilters(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getCategories(context, context.getString(R.string.categories_filename)));
    }

    @Override
    public void getFiltersAsyncTask(OnFinishedListener onFinishedListener) {
        FiltersTask filtersTask = new FiltersTask(context, onFinishedListener);
        filtersTask.execute();
    }

    @Override
    public void getFiltersExecutors(OnFinishedListener onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Category> categories = JSONHelper.getCategories(context, context.getString(R.string.categories_filename));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            EventBus.getDefault().post(new ExecutorResult(onFinishedListener, categories));
        });
    }

    @Override
    public void getFiltersIntentService(OnFinishedListener onFinishedListener) {
        Intent intent = new Intent(context, CategoriesIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.categories_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_CATEGORIES);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        categoriesBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(categoriesBroadcastReceiver, intentFilter);
    }

    @Override
    public void setFilters(List<Category> categories) {
        JSONHelper.setCategories(context, categories);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorResult executorResult){
        EventBus.getDefault().unregister(this);
        executorResult.finishCategories();
    }

    @SuppressLint("StaticFieldLeak")
    class FiltersTask extends AsyncTask<Void, Void, List<Category>> {

        private Context context;
        private OnFinishedListener onFinishedListener;

        FiltersTask(Context context, OnFinishedListener onFinishedListener){
            this.context = context;
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        protected List<Category> doInBackground(Void... voids){
            List<Category> categories = JSONHelper.getCategories(context, context.getString(R.string.categories_filename));
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

    class ExecutorResult{
        private OnFinishedListener onFinishedListener;
        private List<Category> categories;

        ExecutorResult(OnFinishedListener onFinishedListener, List<Category> categories){
            this.onFinishedListener = onFinishedListener;
            this.categories = categories;
        }

        void finishCategories(){
            onFinishedListener.onFinished(categories);
        }
    }

    class CategoriesBroadcastReceiver extends BroadcastReceiver {

        private OnFinishedListener onFinishedListener;

        public void setData(OnFinishedListener onFinishedListener){
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Category> categories = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
            finishedReceiver(onFinishedListener, categories);
        }
    }

    private void finishedReceiver(OnFinishedListener onFinishedListener, List<Category> categories){
        context.unregisterReceiver(categoriesBroadcastReceiver);
        onFinishedListener.onFinished(categories);
    }
}
