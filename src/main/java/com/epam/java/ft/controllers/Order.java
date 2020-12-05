package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.CatalogDao;
import com.epam.java.ft.dao.OrderDao;
import com.epam.java.ft.dao.OrderTypeDao;
import com.epam.java.ft.models.OrderType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class Order {
    /**
     * Logic for adding a new order
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Order.connection = connection;
    }

    public static void addNewOrder(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        HttpSession session = request.getSession(false);

        if (loggedIn) {
            OrderType orderType = OrderTypeDao.getOrderType(connection, request.getParameter("orderType"), "en");
            int bookId;
            if (orderType != null) {
                bookId = Integer.parseInt(request.getParameter("book_id"));
                int bookCount = CatalogDao.getBookAmount(connection, bookId);
                if (bookCount > 0) {
                    OrderDao.insertNewOrder(connection, (Integer) session.getAttribute("id"), bookId, orderType.getId());
                    CatalogDao.changeBookAmount(connection, bookId, bookCount - 1);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
}
