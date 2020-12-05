package com.epam.java.ft.servlets;

import com.epam.java.ft.controllers.*;
import com.epam.java.ft.dao.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class MainServlet extends HttpServlet {
    public static Logger logger = Logger.getLogger("MainServlet");
    private Connection connection = ConnectionPool.getConnection();

    private static String getLanguage(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("language");
        if (request.getCookies() != null && language == null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("language")) {
                    language = cookie.getValue();
                    break;
                }
            }
        }
        if (language == null) {
            language = "ru";
        }
        Cookie languageCookie = new Cookie("language", language);
        response.addCookie(languageCookie);
        return languageCookie.getValue();
    }

    public static boolean getLoggedIn(HttpServletRequest request) {
        return request.getSession(false) != null && request.getSession().getAttribute("loggedIn") != null && (Boolean) request.getSession().getAttribute("loggedIn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String language = getLanguage(request, response);
        boolean loggedIn = getLoggedIn(request);
        switch (path) {
            case "/":
                Main.setConnection(connection);
                Main.get(request, response, language);
                break;
            case "/order":
                Order.setConnection(connection);
                Order.addNewOrder(request, response, loggedIn);
                break;
            case "/fine":
                Fine.setConnection(connection);
                Fine.pay(request, response, loggedIn);
                break;
            case "/profile":
                Profile.setConnection(connection);
                Profile.get(request, response, loggedIn, language);
                break;
            case "/logout":
                Logout.logout(request, response);
                break;
            case "/orders":
                Orders.setConnection(connection);
                Orders.get(request, response, loggedIn, language);
                break;
            case "/users":
                Users.setConnection(connection);
                Users.get(request, response, loggedIn, language);
                break;
            case "/giveBook":
                GiveBook.setConnection(connection);
                GiveBook.get(request, response, loggedIn);
                break;
            case "/return":
                ReturnBook.setConnection(connection);
                ReturnBook.get(request, response, loggedIn);
                break;
            case "/makeLibrarian":
            case "/deleteLibrarian":
                Librarian.setConnection(connection);
                Librarian.get(request, response, loggedIn);
                break;
            case "/block":
            case "/unblock":
                Blocker.setConnection(connection);
                Blocker.get(request, response, loggedIn);
                break;
            case "/books":
                Books.setConnection(connection);
                Books.get(request, response, loggedIn, language);
                break;
            case "/edit":
                BookEditor.setConnection(connection);
                BookEditor.get(request, response, loggedIn, language);
                break;
            case "/error":
                ErrorPage.doGet(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/error");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        String language = getLanguage(request, response);
        boolean loggedIn = getLoggedIn(request);
        switch (path) {
            case "/":
                Main.post(request, response, language);
                break;
            case "/profile":
                Profile.post(connection, request, response, loggedIn);
                break;
            case "/edit":
                BookEditor.setConnection(connection);
                BookEditor.post(request, response, loggedIn);
        }
    }
}
