package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null)
            response.sendRedirect("/login.jsp");
        else
            response.sendRedirect("/user/hello.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean isCorrectLogin = Users.getInstance().getUsers().contains(login);
        boolean isCorrectPassword = password != null && !password.trim().isEmpty();

        if (isCorrectLogin && isCorrectPassword) {
            request.getSession().setAttribute("login", "user");
            response.sendRedirect(" /user/hello.jsp");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
