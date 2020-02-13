package com.kozyrev.simbirtraineeship.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kozyrev.simbirtraineeship.model.Category;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CategoryDAO {

    @Insert
    void add(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<Category> categories);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM categories")
    Maybe<List<Category>> getCategories();
}
