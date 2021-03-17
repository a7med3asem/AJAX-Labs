package com.mak.helpers;

import com.google.gson.Gson;
import com.mak.model.User;
import com.mak.model.UserData;

public class UserDataMapper {
    private static final Gson GSON = new Gson();

    public static String mapToJson(UserData userData) {
        return GSON.toJson(userData);
    }
    public static UserData mapToUserData(String json) {
        return GSON.fromJson(json, UserData.class);
    }
}
