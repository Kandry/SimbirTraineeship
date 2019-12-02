package com.kozyrev.simbirtraineeship.base;

public interface ModelBase {

    interface OnFinishedListener {

        void onFailure(Throwable throwable);
    }
}
