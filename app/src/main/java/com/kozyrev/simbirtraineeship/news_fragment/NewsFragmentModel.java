package com.kozyrev.simbirtraineeship.news_fragment;

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
import com.kozyrev.simbirtraineeship.utils.intent_service.EventsIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsFragmentModel implements NewsFragmentContract.Model {

    private Context context;
    private NewsBroadcastReceiver newsBroadcastReceiver = new NewsBroadcastReceiver();

    NewsFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public void getEvents(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(JSONHelper.getEvents(context, context.getString(R.string.events_filename)));
    }

    @Override
    public void getEventsAsyncTask(OnFinishedListener onFinishedListener) {
        NewsTask newsTask = new NewsTask(context, onFinishedListener);
        newsTask.execute();
    }

    @Override
    public void getEventsExecutors(OnFinishedListener onFinishedListener) {
        EventBus.getDefault().register(this);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            List<Event> news = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EventBus.getDefault().post(new ExecutorResult(onFinishedListener, news));
        });
    }

    @Override
    public void getEventsIntentService(OnFinishedListener onFinishedListener) {
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.putExtra(Constants.EXTRA_KEY_IN, context.getString(R.string.events_filename));
        context.startService(intent);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EVENTS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        newsBroadcastReceiver.setData(onFinishedListener);
        context.registerReceiver(newsBroadcastReceiver, intentFilter);
    }

    @Override
    public List<Category> getCategories() {
        return JSONHelper.getCategories(context, context.getString(R.string.categories_filename));
    }

    @Override
    public void clearCategories() {
        JSONHelper.clearCategories(context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void executorDone(ExecutorResult executorResult){
        EventBus.getDefault().unregister(this);
        executorResult.finishNews();
    }

    @SuppressLint("StaticFieldLeak")
    class NewsTask extends AsyncTask<Void, Void, List<Event>> {

        private Context context;
        private OnFinishedListener onFinishedListener;

        NewsTask(Context context, OnFinishedListener onFinishedListener){
            this.context = context;
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            List<Event> news = JSONHelper.getEvents(context, context.getString(R.string.events_filename));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return news;
        }

        @Override
        protected void onPostExecute(List<Event> news){
            super.onPostExecute(news);
            onFinishedListener.onFinished(news);
        }
    }

    class ExecutorResult {
        private OnFinishedListener onFinishedListener;
        private List<Event> news;

        ExecutorResult(OnFinishedListener onFinishedListener, List<Event> news){
            this.onFinishedListener = onFinishedListener;
            this.news = news;
        }

        void finishNews(){
            onFinishedListener.onFinished(news);
        }
    }

    class NewsBroadcastReceiver extends BroadcastReceiver {

        private OnFinishedListener onFinishedListener;

        public void setData(OnFinishedListener onFinishedListener){
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        public void onReceive(Context context, Intent intent){
            ArrayList<Event> news = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
            finishedReceiver(onFinishedListener, news);
        }
    }

    private void finishedReceiver(OnFinishedListener onFinishedListener, List<Event> news){
        context.unregisterReceiver(newsBroadcastReceiver);
        onFinishedListener.onFinished(news);
    }
}
