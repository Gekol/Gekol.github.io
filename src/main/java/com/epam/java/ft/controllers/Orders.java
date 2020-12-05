package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.OrderDao;
import com.epam.java.ft.models.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Orders {
    /**
     * View all orders
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Orders.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, String language) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") > 1) {
            List<Order> orders = OrderDao.getAllOrders(connection, language);
            request.setAttribute("orders", orders);
            request.setAttribute("language", language);
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/orders.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
