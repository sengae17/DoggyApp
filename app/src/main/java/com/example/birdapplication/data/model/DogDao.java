package com.example.birdapplication.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//specifing database operations
@Dao
public interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Dog> dogs);

    @Query("DELETE FROM dog_database")
    void deleteAllDogs();

    @Query("SELECT * FROM dog_database")
    LiveData<List<Dog>> getAllDogs();

    @Query("SELECT * FROM dog_database")
    List<Dog> getAllDogsNonLive();

    @Query("UPDATE dog_database SET pictureUrl = :pictureUrl WHERE id = :breedId")
    void updateImage(int breedId, String pictureUrl);
}
