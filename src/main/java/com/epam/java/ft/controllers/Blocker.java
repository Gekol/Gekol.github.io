package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Blocker {
    /**
     * Logic for blocking/unblocking user
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Blocker.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {

        if (!loggedIn || !(Boolean) request.getSession().getAttribute("loggedIn")) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        String path = request.getServletPath();
        int userId = Integer.parseInt(request.getParameter("user"));
        switch (path) {
            case "/block": {
                UserDao.updateUserStatus(connection, userId, "Blocked", "en");
                response.sendRedirect(request.getContextPath() + "/users");
                break;
            }
            case "/unblock": {
                UserDao.updateUserStatus(connection, userId, "Active", "en");
                response.sendRedirect(request.getContextPath() + "/users");
                break;
            }
        }
    }
}
