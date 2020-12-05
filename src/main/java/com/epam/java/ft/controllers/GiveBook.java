package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.OrderDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class GiveBook {
    /**
     * Logic for giving book
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        GiveBook.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") > 1) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            int orderType = Integer.parseInt(request.getParameter("orderType"));
            OrderDao.setDeadline(connection, orderId, (orderType == 1) ? 30 : 0);
            OrderDao.setStatus(connection, orderId, 2);
            response.sendRedirect(request.getContextPath() + "/orders");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
