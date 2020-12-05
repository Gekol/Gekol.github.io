package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.BookDao;
import com.epam.java.ft.models.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class Books {
    /**
     * View all books
     */
    private static Connection connection;

    public static void setConnection(Connection connection) {
        Books.connection = connection;
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, boolean loggedIn, String language) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (loggedIn && (Integer) session.getAttribute("type") == 3) {
            String delete = request.getParameter("delete");
            List<Book> books = BookDao.getBooks(connection, language);
            HashMap<String, Integer> bookCounts = BookDao.getBookCounts(connection, language);
            request.setAttribute("books", books);
            request.setAttribute("bookCounts", bookCounts);
            request.setAttribute("language", language);
            if (delete != null) {
                BookDao.deleteBook(connection, Integer.parseInt(delete));
            }
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/books.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
