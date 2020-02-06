package com.kozyrev.simbirtraineeship.network;


import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetHelper {

    public static Observable<List<Category>> getCategories(){
        return ApiClient
                .getClient()
                .create(ApiInterface.class)
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Event>> getEvents(){
        return ApiClient
                .getClient()
                .create(ApiInterface.class)
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
