package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Librarian {
    /**
     * Logic for creating/deleting librarian
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Librarian.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        if (!loggedIn || !(Boolean) request.getSession().getAttribute("loggedIn")) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        String path = request.getServletPath();
        int userId = Integer.parseInt(request.getParameter("user"));
        switch (path) {
            case "/makeLibrarian": {
                UserDao.updateUserType(connection, userId, "Librarian", "en");
                response.sendRedirect(request.getContextPath() + "/users");
                break;
            }
            case "/deleteLibrarian": {
                UserDao.updateUserType(connection, userId, "User", "en");
                response.sendRedirect(request.getContextPath() + "/users");
                break;
            }
        }
    }
}
