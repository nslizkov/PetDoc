package com.example.petdoc.data.data_room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.petdoc.data.entities.Appointment;

import java.util.List;

@Dao
public interface AppointmentsDao {


    @Insert
    void insertAppointment(Appointment... appointment);


    @Delete
    void delete(Appointment appointment);


    @Query("SELECT * FROM Appointments WHERE paid=0")
    List<Appointment> getAppointments();

    @Query("SELECT * FROM Appointments WHERE paid=1")
    List<Appointment> getPaidAppointments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void pay(Appointment appointment);
}
