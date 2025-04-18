package com.example.petdoc.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "pet")
    private String pet;

    @ColumnInfo(name = "doctor")
    private String doctor;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "doctorType")
    private String doctorType;

    @ColumnInfo(name = "paid")
    private int paid;

    public Appointment(String pet, String doctor, String doctorType, String date, int price, int paid) {
        this.doctorType = doctorType;
        this.pet = pet;
        this.doctor = doctor;
        this.date = date;
        this.price = price;
        this.paid = paid;
    }


    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPet() {
        return pet;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
