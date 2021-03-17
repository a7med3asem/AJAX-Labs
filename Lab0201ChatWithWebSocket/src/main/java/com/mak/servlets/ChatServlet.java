package com.mak.servlets;

import com.mak.helpers.MessageMapper;
import com.mak.model.Message;
import com.mak.validation.NumbersValidator;
import com.mak.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ChatServlet extends HttpServlet {
    private final Validator<String> numberValidator = new NumbersValidator();
    private final List<Message> messages = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (messages.size() != 0) {
            String num = request.getParameter("length");
            if (num != null && !num.isEmpty() && numberValidator.isValid(num)) {
                int length = Integer.parseInt(num);
                int size = messages.size();
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                if (length > 0 && length < size) {
//                    out.print(MessageMapper.mapToJson(messages.subList(length, size)));
                } else if (length == 0 && size > 0) {
//                    out.print(MessageMapper.mapToJson(messages));

                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String message = request.getParameter("message");
        if (userName != null && !userName.isEmpty() && message != null && !message.isEmpty()) {
//            Message userMessage = new Message(userName, message);
            synchronized (messages) {
//                messages.add(userMessage);
            }
        }
    }
}
