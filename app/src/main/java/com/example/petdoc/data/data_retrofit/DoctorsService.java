package com.example.petdoc.data.data_retrofit;

import com.example.petdoc.data.entities.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoctorsService {
    @GET("doctors.php")
    Call<List<Doctor>> getFreeDoctors();

    @GET("doctors.php")
    Call<String> changeDoctor(@Query("mode") String mode,
                              @Query("id") int id,
                              @Query("name") String name,
                              @Query("docType") String docType,
                              @Query("animalType") String animalType,
                              @Query("date") String date,
                              @Query("price") int price);
}
