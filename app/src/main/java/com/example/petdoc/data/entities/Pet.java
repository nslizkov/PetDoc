package com.example.petdoc.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pets")
public class Pet {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "history")
    public String history;
    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public Pet(String name, int age, String type, String history) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setType(String type) {
        this.type = type;
    }
}

