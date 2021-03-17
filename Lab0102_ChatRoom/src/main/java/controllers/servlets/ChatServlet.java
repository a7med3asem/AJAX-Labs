package controllers.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import models.Message;

public class ChatServlet extends HttpServlet {
    List<Message> messages = new ArrayList<>();

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("Sent");

        int length = Integer.parseInt(request.getParameter("length"));
        int messagesSize = messages.size();
        if (length > 0 && length < messagesSize) {
        out.print(buildGsonFromObject(messages.subList(length, messagesSize)));
        } else if (length == 0 && messagesSize > 0) {
        out.print(buildGsonFromObject(messages));
        }
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        Message userMessage = new Message(name, message);

        messages.add(userMessage);
        System.out.println("Added");
        System.out.println(userMessage);
    }


    private String buildGsonFromObject(List<Message> messages) {
        Gson gsonMessage = new Gson();
        return gsonMessage.toJson(messages);
    }
}
