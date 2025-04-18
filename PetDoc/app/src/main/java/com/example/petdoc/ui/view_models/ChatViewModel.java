package com.example.petdoc.ui.view_models;

import android.app.Activity;

import com.example.petdoc.data.entities.Message;
import com.example.petdoc.data.repositories.ChatRepository;

import java.util.List;

public class ChatViewModel {
    private static ChatRepository chatRepository;

    public ChatViewModel(Activity activity) {
        chatRepository = new ChatRepository();
    }

    public static List<Message> getMessages() {
        return chatRepository.getMessages();
    }

    public static List<Message> getMessages(int userID) { return chatRepository.getMessages(userID);}

    public void insertMessage(Message message) { chatRepository.insertMessage(message);
    }
}
