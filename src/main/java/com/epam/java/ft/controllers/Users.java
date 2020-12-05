package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.UserDao;
import com.epam.java.ft.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Users {
    /**
     * View all users
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Users.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, String language) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") > 1) {
            List<User> users = UserDao.getAllUsers(connection, language);
            request.setAttribute("users", users);
            request.setAttribute("language", language);
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/users.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
