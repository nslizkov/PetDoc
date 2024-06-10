package com.example.petdoc.data.entities;

public class Doctor {

    private int id;

    public String name;
    public String docType;

    public String animalType;

    public String date;

    public int price;

    public Doctor(String name, String docType, String animalType, String date, int price){
        this.name = name;
        this.price = price;
        this.animalType = animalType;
        this.date = date;
        this.docType = docType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getDocType() {
        return docType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public void setName(String name) {
        this.name = name;
    }
}

