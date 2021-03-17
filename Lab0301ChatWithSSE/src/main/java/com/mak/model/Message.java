package com.mak.model;

public class Message {

    private String content, time;
    private User user;

    public Message(String content, String time, User user) {
        this.content = content;
        this.time = time;
        this.user = user;
    }

    public Message() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
