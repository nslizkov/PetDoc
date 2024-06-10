package com.example.petdoc.data.data_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.petdoc.data.data_room.dao.AppointmentsDao;
import com.example.petdoc.data.entities.Appointment;

@Database(version = 1, entities = { Appointment.class }, exportSchema = false)
public abstract class AppointmentsDatabase extends RoomDatabase {
    private static AppointmentsDatabase INSTANCE;
    public abstract AppointmentsDao AppointmentDao();
    public static AppointmentsDatabase getAppointmentsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppointmentsDatabase.class, "Appointments")
                            .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}