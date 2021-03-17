package com.mak.helpers;

import com.google.gson.Gson;
import com.mak.model.Message;
import com.mak.model.User;

import java.util.List;

public class UserMapper {
    private static final Gson GSON = new Gson();

    public static String mapToJson(User user) {
        return GSON.toJson(user);
    }
    public static User mapToUser(String json) {
        return GSON.fromJson(json, User.class);
    }
}
