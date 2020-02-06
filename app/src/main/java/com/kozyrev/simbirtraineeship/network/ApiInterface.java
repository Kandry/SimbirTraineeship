package com.kozyrev.simbirtraineeship.network;

import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("events.json")
    Observable<List<Event>> getEvents();

    @GET("categories.json")
    Observable<List<Category>> getCategories();
}
