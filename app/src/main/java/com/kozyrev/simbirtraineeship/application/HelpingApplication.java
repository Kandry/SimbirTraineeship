package com.kozyrev.simbirtraineeship.application;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class HelpingApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        HelpingApplication.context = getApplicationContext();
        AndroidThreeTen.init(HelpingApplication.context);
    }

    public static Context getAppContext() {
        return HelpingApplication.context;
    }
}
