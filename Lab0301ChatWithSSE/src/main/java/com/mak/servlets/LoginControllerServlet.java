package com.mak.servlets;

import com.mak.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String name = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String time = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss a").format(LocalDateTime.now());

        User currentUser = new User(name, gender, time);
        userSession.setAttribute("currentUser", currentUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("chat");
        dispatcher.forward(request, response);
    }
}
