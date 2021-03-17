package controllers.servlets;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LoginServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String userName = request.getParameter("uName");
        
        if(userName.equals("ahmed")) {
            out.print("Vaild");
        } else {
            out.print("invalid");
        }
        // : //get request paramters
        // : //validate user name
        }
}