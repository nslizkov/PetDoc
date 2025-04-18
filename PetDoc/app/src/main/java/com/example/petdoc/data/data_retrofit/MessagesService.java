package com.example.petdoc.data.data_retrofit;

import com.example.petdoc.data.entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MessagesService {
    @GET("messages.php")
    Call<List<Message>> getMessages(@Query("mode") String mode,
                                    @Query("userId") int userId);

    @GET("messages.php")
    Call<String> changeMessage(@Query("mode") String mode,
                               @Query("id") int id,
                               @Query("userId") int userId,
                               @Query("toId") int toId,
                               @Query("text") String text,
                               @Query("date") String date
                               );
}
