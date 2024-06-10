package com.example.petdoc.data.repositories;

import android.app.Activity;
import android.util.Log;

import com.example.petdoc.data.data_room.dao.PetDao;
import com.example.petdoc.data.data_room.database.AppointmentsDatabase;
import com.example.petdoc.data.data_room.database.PetDatabase;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.data.entities.Pet;

import java.util.List;

public class PetsRepository {
    private PetDao petDao;


    public PetsRepository(Activity application) {
        PetDatabase db = PetDatabase.getPetsDatabase(application);
        petDao = db.PetDao();
    }

    public PetsRepository() {}
    public void insertPet(Pet pet) {
        petDao.insertPet(pet);
    }

    public List<Pet> getPets() {
        return petDao.getPets();
    }
    public void deletePet(Activity activity, Pet pet) {
        Log.i("pet", ""+pet.getName());
        petDao.delete(pet);
        AppointmentsDatabase db = AppointmentsDatabase.getAppointmentsDatabase(activity);
        for (Appointment i : db.AppointmentDao().getAppointments()){
            db.AppointmentDao().delete(i);
        }
    }
}
