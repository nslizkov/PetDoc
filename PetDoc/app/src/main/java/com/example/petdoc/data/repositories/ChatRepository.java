package com.example.petdoc.data.repositories;

import static com.example.petdoc.MainActivity.USERID;

import android.util.Log;

import com.example.petdoc.data.data_retrofit.MessagesService;
import com.example.petdoc.data.entities.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatRepository {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://nik.slizkov.com/PetDoc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MessagesService service = retrofit.create(MessagesService.class);

    public List<Message> getMessages() {
        Call<List<Message>> call = service.getMessages("select", USERID);
        List<Message> result = new ArrayList<>();
        try {
            Response<List<Message>> response = call.execute();
            result.addAll(response.body());
        } catch (IOException e){
            Log.i("mytag", e.getLocalizedMessage());}
        return result;
    }

    public List<Message> getMessages(int userID) {
        Call<List<Message>> call = service.getMessages("select", userID);
        List<Message> result = new ArrayList<>();
        try {
            Response<List<Message>> response = call.execute();
            result.addAll(response.body());
        } catch (IOException e){
            Log.i("mytag", e.getLocalizedMessage());}
        return result;
    }

    public String insertMessage(Message i){
        Call<String> call = service.changeMessage("insert", i.getId(), i.getUserId(), i.getToId(), i.getText(), i.getDate());
        String res = "failed";
        try {
            res = String.valueOf(call.execute());
        } catch (IOException e){
            res = e.getLocalizedMessage();
        }
        Log.i("mytag", "insrtmsg " + res);
        return res;
    }
}
