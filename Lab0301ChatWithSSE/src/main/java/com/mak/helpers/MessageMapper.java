package com.mak.helpers;

import com.google.gson.Gson;
import com.mak.model.Message;

import java.util.List;

public class MessageMapper {
    private static final Gson GSON = new Gson();

    public static String mapToJson(Message message) {
        return GSON.toJson(message);
    }
    public static Message mapToMessage(String json) {
        return GSON.fromJson(json, Message.class);
    }
}
