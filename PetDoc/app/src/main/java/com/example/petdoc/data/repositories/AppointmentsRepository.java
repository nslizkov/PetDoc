package com.example.petdoc.data.repositories;

import android.app.Activity;

import com.example.petdoc.data.data_room.dao.AppointmentsDao;
import com.example.petdoc.data.data_room.dao.PetDao;
import com.example.petdoc.data.data_room.database.AppointmentsDatabase;
import com.example.petdoc.data.data_room.database.PetDatabase;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.data.entities.Doctor;
import com.example.petdoc.data.entities.Pet;

import java.util.List;

public class AppointmentsRepository extends PetsRepository {
    private AppointmentsDao appointmentsDao;
    private PetDao petDao;
    private DoctorsRepository doctorsRepository = new DoctorsRepository();
    public AppointmentsRepository(Activity application) {
        AppointmentsDatabase db = AppointmentsDatabase.getAppointmentsDatabase(application);
        appointmentsDao = db.AppointmentDao();
        PetDatabase pets_db = PetDatabase.getPetsDatabase(application);
        petDao = pets_db.PetDao();
    }

    public List<Appointment> getAppointments() {
        return appointmentsDao.getAppointments();
    }

    public void deleteAllAppointments() {
        for (Appointment i : appointmentsDao.getAppointments()){
            deleteAppointment(i);
        }
    }

    public void deleteAppointment(Appointment i) {
        appointmentsDao.delete(i);
        List<Pet> pets = petDao.getPets();
        String type = "Dog";
        for (Pet p:pets){
            if (p.getName().equals(i.getPet())){
                type = p.getType();
            }
        }
        Doctor doc = new Doctor(i.getDoctor(), i.getDoctorType(), type, i.getDate(), i.getPrice());
        doctorsRepository.insertDoctor(doc);
    }

    public void insertAppointment(Appointment appointment) {
        appointmentsDao.insertAppointment(appointment);
    }

    public void payAll() {
        for (Appointment i : appointmentsDao.getAppointments()){
            i.setPaid(1);
            appointmentsDao.pay(i);
        }
    }

    public List<Appointment> getPaidAppointments() {
        return appointmentsDao.getPaidAppointments();
    }
}
