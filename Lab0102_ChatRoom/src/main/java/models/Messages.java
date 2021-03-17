package models;

import java.util.*;

public class Messages {
    // List<String> messages;
    // private String message;
    // private String name;

    Map<String, String> messages;

    public Messages() {
        messages = new HashMap<String, String>();
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public SetMessage(String message) {
    //     this.message = message;
    // }

    // public String getMessage() {
    //     return message;
    // }

    // public String getName() {
    //     return name;
    // }

    public Map<String, String> getMessages() {
        return messages;
    }
}