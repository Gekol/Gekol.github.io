package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.CatalogDao;
import com.epam.java.ft.dao.OrderDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class ReturnBook {
    /**
     * Logic for returning book
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        ReturnBook.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") > 1) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            OrderDao.setStatus(connection, orderId, 3);
            int bookCount = CatalogDao.getBookAmount(connection, bookId);
            CatalogDao.changeBookAmount(connection, bookId, bookCount + 1);
            response.sendRedirect(request.getContextPath() + "/orders");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
