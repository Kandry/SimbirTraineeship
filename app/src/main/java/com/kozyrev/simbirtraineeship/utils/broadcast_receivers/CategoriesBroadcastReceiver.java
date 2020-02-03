package com.kozyrev.simbirtraineeship.utils.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerCategories;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CategoriesBroadcastReceiver extends BroadcastReceiver {

    private OnFinishedListenerCategories onFinishedListener;

    public void setData(OnFinishedListenerCategories onFinishedListener){
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<Category> categories = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
        finishedReceiver(onFinishedListener, categories);
    }

    private void finishedReceiver(OnFinishedListenerCategories onFinishedListener, List<Category> categories){
        HelpingApplication.getAppContext().unregisterReceiver(this);
        onFinishedListener.onFinished(categories);
    }
}


