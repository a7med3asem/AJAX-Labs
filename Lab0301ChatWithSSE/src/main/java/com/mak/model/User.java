package com.mak.model;

public class User {
    private String name, gender, time;

    public User(String name, String gender, String time) {
        this.name = name;
        this.gender = gender;
        this.time = time;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
