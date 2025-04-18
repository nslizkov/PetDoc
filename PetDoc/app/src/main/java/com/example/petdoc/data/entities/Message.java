package com.example.petdoc.data.entities;

public class Message {
    private int id;
    private String text;
    private String date;
    private int userId;
    private int toId;

    public Message(int userId, int toId, String text, String date){
        this.date = date;
        this.userId = userId;
        this.text = text;
        this.toId = toId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

