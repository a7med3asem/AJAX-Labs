package com.mak.helpers;

import com.google.gson.Gson;
import com.mak.model.Message;
import com.mak.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionMapper {
    private static final Gson GSON = new Gson();

    public static String messagesToJson(Collection<Message> message) {
        return GSON.toJson(message);
    }

    public static String usersToJson(Collection<User> user) {
        return GSON.toJson(user);
    }
}
