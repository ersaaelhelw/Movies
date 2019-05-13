package com.example.manara.movies;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT *FROM Favorite")
    LiveData<List<Favorite_Table>> loadAllMovies();

    @Insert
    void  Addfavorite(Favorite_Table task);

    @Delete
    void delete(Favorite_Table task);

    @Query("SELECT * FROM Favorite WHERE id = :id")
    LiveData<Favorite_Table> loadTaskById(int id);

    @Query("DELETE FROM Favorite WHERE id = :id")
    void deleteFavoriteById(int id);

    @Query("SELECT * FROM Favorite WHERE id = :id")
    Favorite_Table getFavoritById(int id);


}
