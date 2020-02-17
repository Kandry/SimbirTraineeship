package com.kozyrev.simbirtraineeship.utils.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerEvents;
import com.kozyrev.simbirtraineeship.db.DB;
import com.kozyrev.simbirtraineeship.model.Event;
import com.kozyrev.simbirtraineeship.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class EventsBroadcastReceiver extends BroadcastReceiver {
    private OnFinishedListenerEvents onFinishedListener;

    public void setData(OnFinishedListenerEvents onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<Event> events = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY_OUT);
        finishedReceiver(onFinishedListener, events);
    }

    private void finishedReceiver(OnFinishedListenerEvents onFinishedListener, List<Event> events){
        HelpingApplication.getAppContext().unregisterReceiver(this);
       /* DB.getDB()
                .getEventDAO()
                .addAll(events);*/
        onFinishedListener.onFinished(events);
    }
}


