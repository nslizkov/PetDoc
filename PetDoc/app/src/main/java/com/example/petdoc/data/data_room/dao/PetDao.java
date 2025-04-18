package com.example.petdoc.data.data_room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.petdoc.data.entities.Pet;

import java.util.List;

@Dao
public interface PetDao {
    @Insert
    void insertPet(Pet... pet);

    @Delete
    void delete(Pet pet);

    @Query("SELECT * FROM Pets")
    List<Pet> getPets();
}
