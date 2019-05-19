package com.pammos.roommigrationtest.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(T object);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(T... objects);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(T object);

    @Delete
    void delete(T object);

    @Delete
    void delete(T... objects);
}
