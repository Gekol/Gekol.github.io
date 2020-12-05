package com.epam.java.ft.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPage {
    public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer code = 404;
        if (request.getAttribute("javax.servlet.error.status_code") != null) {
            code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        }
        request.setAttribute("code", code);
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error.jsp");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
