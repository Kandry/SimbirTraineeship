package com.kozyrev.simbirtraineeship.base.finished_listeners;

import com.kozyrev.simbirtraineeship.base.OnFinishedListener;
import com.kozyrev.simbirtraineeship.model.User;

public interface OnFinishedListenerUser extends OnFinishedListener {

    void onFinished(User user);
}
