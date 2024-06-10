package com.example.petdoc.ui.view_models;

import android.app.Activity;

import com.example.petdoc.data.entities.Doctor;
import com.example.petdoc.data.repositories.DoctorsRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorsViewModel {
    private static DoctorsRepository doctorsRepository;

    public DoctorsViewModel(Activity activity) {
        doctorsRepository = new DoctorsRepository();
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = doctorsRepository.getFreeDoctors();
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor doctor1, Doctor doctor2) {
                return doctor1.getDate().compareTo(doctor2.getDate());
            }
        });
        return doctors;
    }

    public List<String> getDoctorsTypes() { return doctorsRepository.getFreeDoctorsTypes();
    }

    public void deleteDoctor(Doctor i) {doctorsRepository.deleteDoctor(i);}

    public void insertDoctor(Doctor i){doctorsRepository.insertDoctor(i);}
}
