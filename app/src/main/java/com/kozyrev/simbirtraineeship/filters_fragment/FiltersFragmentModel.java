package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.db.DB;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.network.NetHelper;
import com.kozyrev.simbirtraineeship.utils.Constants;
import com.kozyrev.simbirtraineeship.utils.JSONHelper;
import com.kozyrev.simbirtraineeship.utils.async_tasks.FiltersTask;
import com.kozyrev.simbirtraineeship.utils.broadcast_receivers.CategoriesBroadcastReceiver;
import com.kozyrev.simbirtraineeship.utils.executors.ExecutorCategoriesResult;
import com.kozyrev.simbirtraineeship.utils.intent_service.CategoriesIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FiltersFragmentModel implements Model {

    private CategoriesBroadcastReceiver categoriesBroadcastReceiver = new CategoriesBroadcastReceiver();
    private DB db = DB.getDB();

    @Override
    public void getFilters(OnFinishedListenerCategories onFinishedListener) {
        //onFinishedListener.onFinished(JSONHelper.getCategories());
        //getFiltersAsyncTask(onFinishedListener);
        //getFiltersExecutors();
        //getFiltersIntentService(onFinishedListener);
        //getNetFilters(onFinishedListener);

        db.getCategoryDAO()
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Category>>(){
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<Category> categories) {
                        if (categories.size() > 0) onFinishedListener.onFinished(categories);
                        else getNetFilters(onFinishedListener);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {
                        getNetFilters(onFinishedListener);
                    }
                });
    }

    private void getFiltersAsyncTask(OnFinishedListenerCategories onFinishedListener) {
        FiltersTask filtersTask = new FiltersTask(onFinishedListener);
        filtersTask.execute();
    }

    private void getFiltersExecutors(OnFinishedListenerCategories onFinishedListener) {
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

    private void getFiltersIntentService(OnFinishedListenerCategories onFinishedListener) {
        Context context = HelpingApplication.getAppContext();
        Intent intent = new Intent(context, CategoriesIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.categories_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_CATEGORIES);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        categoriesBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(categoriesBroadcastReceiver, intentFilter);
    }

    private void getNetFilters(OnFinishedListenerCategories onFinishedListener){
        NetHelper
                .getCategories()
                .subscribe(
                        categories ->
                        {
                            db.getCategoryDAO().addAll(categories);
                            onFinishedListener.onFinished(categories);
                        },
                        e -> getFiltersIntentService(onFinishedListener));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorCategoriesResult executorResult) {
        EventBus.getDefault().unregister(this);
        executorResult.finishCategories();
    }
}
