package com.example.petdoc.data.entities;

import java.util.ArrayList;

public class Chat {
    private int userid;
    private ArrayList<Message> messages = new ArrayList<>();

    private boolean readed = false;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public boolean getReaded() {return readed;}

    public void setReaded(boolean readed) {this.readed = readed;}

    public void appendMessage(Message message){
        messages.add(message);
    }
}
