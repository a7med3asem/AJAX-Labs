package com.mak.endpoint;

import com.mak.helpers.CollectionMapper;
import com.mak.helpers.MessageMapper;
import com.mak.helpers.UserMapper;
import com.mak.model.Message;
import com.mak.model.User;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/chat/{input}")
public class ChatEndPoint {
    private final static List<Message> MESSAGE_LIST = new CopyOnWriteArrayList<>();
    private final static Map<Session, User> USERS = new ConcurrentHashMap<>();

    private static void broadcast(String data) {
        USERS.keySet().forEach(e -> {
            try {
                e.getBasicRemote().sendText(data);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(@PathParam("input") String user, Session session) throws IOException {
        User userObj = UserMapper.mapToUser(user);
        userObj.setTime(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss a").format(LocalDateTime.now()));
        if(MESSAGE_LIST.size() > 0) {
            session.getBasicRemote().sendText(CollectionMapper.messagesToJson(MESSAGE_LIST));
        }
        if (USERS.size() > 0) {
            session.getBasicRemote().sendText(CollectionMapper.usersToJson(USERS.values()));
        }
        USERS.put(session, userObj);
        broadcast(UserMapper.mapToJson(userObj));
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        broadcast(message);
        MESSAGE_LIST.add(MessageMapper.mapToMessage(message));
    }

    @OnClose
    public void onClose(Session session) {
        if (USERS.containsKey(session)) {
            User user = USERS.get(session);
//            user.setDeleted(true);
            USERS.remove(session);
            broadcast(UserMapper.mapToJson(user));
        }
    }
}
