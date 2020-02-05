package com.kozyrev.simbirtraineeship.profile_fragment;

import com.kozyrev.simbirtraineeship.base.finished_listeners.OnFinishedListenerUser;

public interface Model {

    void getUserData(OnFinishedListenerUser onFinishedListener, int id);
}
