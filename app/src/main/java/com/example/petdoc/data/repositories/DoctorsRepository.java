package com.example.petdoc.data.repositories;

import com.example.petdoc.data.data_retrofit.DoctorsService;
import com.example.petdoc.data.entities.Doctor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorsRepository {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://nik.slizkov.com/PetDoc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    DoctorsService service = retrofit.create(DoctorsService.class);

    public List<Doctor> getFreeDoctors() {
        Call<List<Doctor>> call = service.getFreeDoctors();
        List<Doctor> result = new ArrayList<>();
        try {
            Response<List<Doctor>> response = call.execute();
            result.addAll(response.body());
        } catch (IOException e){}
        return result;
    }

    public List<String> getFreeDoctorsTypes()  {
        Call<List<Doctor>> call = service.getFreeDoctors();
        List<String> result = new ArrayList<>();
        try {
            Response<List<Doctor>> response = call.execute();
            for (Doctor d : response.body()) {
                result.add(d.getDocType());
            }
            HashSet<String> hs = new HashSet<>(result);
            result.clear();
            result.addAll(hs);
        } catch (IOException e){

        }
        return result;

    }

    public String deleteDoctor(Doctor i) {
        Call<String> call = service.changeDoctor("delete", i.getId(), i.getName(), i.getDocType(), i.getAnimalType(), i.getDate(), i.getPrice());
        String res = "failed";
        try {
            res = String.valueOf(call.execute());
        } catch (IOException e){
            res = e.getLocalizedMessage();
        }
        return res;
    }

    public String insertDoctor(Doctor i){
        Call<String> call = service.changeDoctor("insert", i.getId(), i.getName(), i.getDocType(), i.getAnimalType(), i.getDate(), i.getPrice());
        String res = "failed";
        try {
            res = String.valueOf(call.execute());
        } catch (IOException e){
            res = e.getLocalizedMessage();
        }
        return res;
    }
}
