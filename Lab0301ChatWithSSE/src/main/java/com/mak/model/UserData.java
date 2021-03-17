package com.mak.model;

public class UserData {
    private int lastMessageID;
    private int lastUsersID;

    public UserData() {
    }

    public UserData(int lastMessageID, int lastUsersID) {
        this.lastMessageID = lastMessageID;
        this.lastUsersID = lastUsersID;
    }

    public int getLastMessageID() {
        return lastMessageID;
    }

    public void setLastMessageID(int lastMessageID) {
        this.lastMessageID = lastMessageID;
    }

    public int getLastUsersID() {
        return lastUsersID;
    }

    public void setLastUsersID(int lastUsersID) {
        this.lastUsersID = lastUsersID;
    }
}
