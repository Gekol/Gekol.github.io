package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.AuthorDao;
import com.epam.java.ft.dao.BookDao;
import com.epam.java.ft.dao.EditionDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class BookEditor {
    /**
     * Logic for editing of books
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        BookEditor.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, String language) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") == 3) {
            String id = request.getParameter("id");
            request.setAttribute("book", Integer.parseInt(id));
            request.setAttribute("authors", AuthorDao.getAuthors(connection, language));
            request.setAttribute("editions", EditionDao.getEditions(connection, language));
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/editBook.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    public static void post(HttpServletRequest request, HttpServletResponse response, boolean loggedIn) throws IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") == 3) {
            int id = Integer.parseInt(request.getParameter("id"));
            String titleEn = request.getParameter("title_en");
            String titleRu = request.getParameter("title_ru");
            int price = Integer.parseInt(request.getParameter("price"));
            int fine = Integer.parseInt(request.getParameter("fine"));
            String author = request.getParameter("author");
            int edition = Integer.parseInt(request.getParameter("edition"));
            BookDao.updateBook(connection, titleEn, titleRu, price, fine, author, edition, id);
            response.sendRedirect(request.getContextPath() + "/books");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
