package com.example.petdoc.ui.view_models;

import android.app.Activity;

import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.data.repositories.PetsRepository;

import java.util.List;

public class PetsViewModel {
    private PetsRepository petsRepository;

    public PetsViewModel(Activity application) {
        //super(application);
        petsRepository = new PetsRepository(application);

    }

    public void insertPet(Pet pet) {petsRepository.insertPet(pet);}

    public List<Pet> getPets() {return petsRepository.getPets();}

    public void deletePet(Activity activity, Pet pet) {petsRepository.deletePet(activity, pet);}

}
