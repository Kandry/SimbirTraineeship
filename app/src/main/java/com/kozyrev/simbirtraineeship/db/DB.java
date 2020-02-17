package com.kozyrev.simbirtraineeship.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kozyrev.simbirtraineeship.application.HelpingApplication;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

@Database(entities = {Category.class, Event.class}, version = 1)
public abstract class DB extends RoomDatabase {

    private static DB instance;

    public abstract CategoryDAO getCategoryDAO();

    public abstract EventDAO getEventDAO();

    public static DB getDB(){
        if (instance == null){
            instance = Room.databaseBuilder(HelpingApplication.getAppContext(), DB.class, "appDB")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }
}
