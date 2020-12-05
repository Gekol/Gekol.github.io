package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.OrderDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class Fine {
    /**
     * Logic for paying fine
     */

    public static Logger logger = Logger.getLogger("Fine");
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Fine.connection = connection;
    }

    public static void pay(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) {
        HttpSession session = request.getSession(false);
        if (loggedIn && ((Integer) session.getAttribute("type")) > 1) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderDao.payFine(connection, orderId);
            try {
                response.sendRedirect(request.getContextPath() + "/orders");
            } catch (IOException e) {
                logger.debug(e.getMessage());
            }
        }
    }

}
