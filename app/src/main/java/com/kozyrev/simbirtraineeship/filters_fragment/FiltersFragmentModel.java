package com.kozyrev.simbirtraineeship.filters_fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.async_tasks.FiltersTask;
import com.kozyrev.simbirtraineeship.utils.broadcast_receivers.CategoriesBroadcastReceiver;
import com.kozyrev.simbirtraineeship.utils.executors.ExecutorCategoriesResult;
import com.kozyrev.simbirtraineeship.utils.intent_service.CategoriesIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiltersFragmentModel implements Model {

    private CategoriesBroadcastReceiver categoriesBroadcastReceiver = new CategoriesBroadcastReceiver();

    @Override
    public void getFilters(OnFinishedListenerCategories onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getCategories());
    }

    @Override
    public void getFiltersAsyncTask(OnFinishedListenerCategories onFinishedListener) {
        FiltersTask filtersTask = new FiltersTask(onFinishedListener);
        filtersTask.execute();
    }

    @Override
    public void getFiltersExecutors(OnFinishedListenerCategories onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Category> categories = JSONHelper.getCategories();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            EventBus.getDefault().post(new ExecutorCategoriesResult(onFinishedListener, categories));
        });
    }

    @Override
    public void getFiltersIntentService(OnFinishedListenerCategories onFinishedListener) {
        Context context = HelpingApplication.getAppContext();
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
        JSONHelper.setCategories(categories);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorCategoriesResult executorResult){
        EventBus.getDefault().unregister(this);
        executorResult.finishCategories();
    }
/*
    class CategoriesBroadcastReceiver extends BroadcastReceiver {

        private OnFinishedListenerCategories onFinishedListener;

        public void setData(OnFinishedListenerCategories onFinishedListener){
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Category> categories = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
            finishedReceiver(onFinishedListener, categories);
        }
    }

    private void finishedReceiver(OnFinishedListenerCategories onFinishedListener, List<Category> categories){
        HelpingApplication.getAppContext().unregisterReceiver(categoriesBroadcastReceiver);
        onFinishedListener.onFinished(categories);
    }*/
}
