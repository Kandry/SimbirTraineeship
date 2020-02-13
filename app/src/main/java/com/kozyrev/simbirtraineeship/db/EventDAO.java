package com.kozyrev.simbirtraineeship.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kozyrev.simbirtraineeship.model.Event;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface EventDAO {

    @Insert
    void add(Event event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<Event> events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events")
    Maybe<List<Event>> getEvents();

    @Query("SELECT * FROM events WHERE id = :id")
    Maybe<Event> getEventByID(int id);
}
