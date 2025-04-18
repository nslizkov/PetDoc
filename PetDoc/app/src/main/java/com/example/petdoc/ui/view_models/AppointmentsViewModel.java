package com.example.petdoc.ui.view_models;

import android.app.Activity;

import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.data.repositories.AppointmentsRepository;

import java.util.List;

public class AppointmentsViewModel {
    private AppointmentsRepository appointmentsRepository;

    public AppointmentsViewModel(Activity application) {
        appointmentsRepository = new AppointmentsRepository(application);
    }

    public List<Appointment> getAppointments() {return appointmentsRepository.getAppointments();}

    public void deleteAllAppointments() {appointmentsRepository.deleteAllAppointments();}

    public void payAll(){appointmentsRepository.payAll();}
    public void insertAppointment(Appointment appointment) { appointmentsRepository.insertAppointment(appointment);
    }

    public List<Appointment> getPaidAppointments() {
        return appointmentsRepository.getPaidAppointments();
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentsRepository.deleteAppointment(appointment);
    }
}