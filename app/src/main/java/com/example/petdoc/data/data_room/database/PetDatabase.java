package com.example.petdoc.data.data_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.petdoc.data.data_room.dao.PetDao;
import com.example.petdoc.data.entities.Pet;

@Database(version = 1, entities = {Pet.class }, exportSchema = false)
public abstract class PetDatabase extends RoomDatabase {
    private static PetDatabase INSTANCE;
    public abstract PetDao PetDao();
    public static PetDatabase getPetsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PetDatabase.class, "Pets")
                            .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}