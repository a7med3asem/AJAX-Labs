package com.mak.servlets.sse;


import com.mak.helpers.CollectionMapper;
import com.mak.helpers.MessageMapper;
import com.mak.helpers.UserDataMapper;
import com.mak.helpers.UserMapper;
import com.mak.model.Message;
import com.mak.model.User;
import com.mak.model.UserData;
import jakarta.json.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServiceServlet extends HttpServlet {
    private final static List<Message> MESSAGE_LIST = new CopyOnWriteArrayList<>();
    private final static List<User> USERS = new CopyOnWriteArrayList<>();
    private final static List<User> REMOVED_USERS = new CopyOnWriteArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String lastId = request.getHeader("Last-Event-ID");

        if (lastId != null) {
            UserData data = UserDataMapper.mapToUserData(lastId);
            int messageID = data.getLastMessageID();
            int userID = data.getLastUsersID();

            if (messageID < MESSAGE_LIST.size()) {
                writer.println("event: message");
                writer.print("data: " + CollectionMapper.messagesToJson(MESSAGE_LIST.subList(messageID, MESSAGE_LIST.size())) + "\n\n");
            }

            if (userID != USERS.size()) {
                if (userID < USERS.size()) {
                    writer.println("event: onlineUser");
                    writer.print("data: " + CollectionMapper.usersToJson(USERS.subList(userID, USERS.size())) + "\n\n");
                } else if (userID > USERS.size()) {
                    writer.println("event: removeUser");
                    writer.print("data: " + CollectionMapper.usersToJson(REMOVED_USERS) + "\n\n");
                }
            }

            data.setLastUsersID(USERS.size());
            data.setLastMessageID(MESSAGE_LIST.size());
            writer.print("id: " + UserDataMapper.mapToJson(data) + "\n\n");
        } else {
            User user = getCurrentUser(request);
            if (user != null) {
                USERS.add(user);
                REMOVED_USERS.remove(user);
                UserData userData = new UserData(MESSAGE_LIST.size(), USERS.size());
                String data = UserDataMapper.mapToJson(userData);

                writer.println("event: onlineUser");
                writer.print("data: " + CollectionMapper.usersToJson(USERS) + "\n\n");

                writer.println("event: message");
                writer.print("data: " + CollectionMapper.messagesToJson(MESSAGE_LIST) + "\n\n");

                writer.print("id: " + data + "\n\n");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isClosed = request.getParameter("isClosed");
        User user = getCurrentUser(request);
        if (isClosed == null) {
            String messageContent = request.getParameter("content");
            String messageTime = request.getParameter("time");
            if (user != null) {
                Message message = new Message(messageContent, messageTime, user);
                MESSAGE_LIST.add(message);
            }
        } else {
            REMOVED_USERS.add(user);
            USERS.remove(user);
            System.out.println(user + "is removed");
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
    }

    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("currentUser");
        }
        return user;
    }
}

