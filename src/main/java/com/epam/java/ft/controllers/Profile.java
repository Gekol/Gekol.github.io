package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.*;
import com.epam.java.ft.models.Subscription;
import com.epam.java.ft.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

public class Profile {
    /**
     * Logic for profile page
     */

    private static Connection connection;

    public static void setConnection(Connection connection) {
        Profile.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, String language) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/profile.jsp");
        HttpSession session = request.getSession(false);
        if (loggedIn) {
            String createSubscription = request.getParameter("createSubscription");
            if (createSubscription != null && createSubscription.equals("true")) {
                Date todayDate = new Date(Calendar.getInstance().getTimeInMillis());
                Date futureDate = addDays(todayDate);
                int added = SubscriptionDao.createSubscription(connection, new Subscription(1, todayDate, futureDate));
                int userId = (Integer) request.getSession(false).getAttribute("id");
                if (added == 1) {
                    int subscriptionId = SubscriptionDao.getRowsCount(connection);
                    UserDao.updateUserSubscription(connection, userId, subscriptionId);
                }
            }
            User user = UserDao.getUser(connection, (String) session.getAttribute("email"), language);
            request.setAttribute("subscription", user.getSubscription());
            request.setAttribute("orders", OrderDao.getOrderByUser(connection, user.getId(), language));
            request.setAttribute("authors", AuthorDao.getAuthors(connection, language));
            request.setAttribute("editions", EditionDao.getEditions(connection, language));
            request.setAttribute("fine", OrderDao.getFinesSumByUser(connection, user.getId()));
            request.setAttribute("language", language);
            view.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    private static Date addDays(java.util.Date day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DATE, 30);
        return new Date(c.getTimeInMillis());
    }

    public static void post(Connection connection, HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        if (loggedIn && (Integer) request.getSession(false).getAttribute("type") == 3) {
            String titleEn = request.getParameter("title_en");
            String titleRu = request.getParameter("title_ru");
            int price = Integer.parseInt(request.getParameter("price"));
            int fine = Integer.parseInt(request.getParameter("fine"));
            String author = request.getParameter("author");
            int edition = Integer.parseInt(request.getParameter("edition"));
            BookDao.insertBook(connection, titleEn, titleRu, price, fine, author, edition);
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
