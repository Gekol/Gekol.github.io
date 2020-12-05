package com.epam.java.ft.controllers;

import com.epam.java.ft.dao.*;
import com.epam.java.ft.models.Author;
import com.epam.java.ft.models.Book;
import com.epam.java.ft.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * Logic for main page
     */
    private static HashMap<String, String> statuses = new HashMap<String, String>() {{
        put("active_en", "Active");
        put("active_ru", "Активный");
        put("blocked_en", "Blocked");
        put("blocked_ru", "Заблокированный");
    }};
    private static HashMap<String, String> types = new HashMap<String, String>() {{
        put("user_en", "User");
        put("user_ru", "Пользователь");
        put("librarian_en", "Librarian");
        put("librarian_ru", "Библиотекарь");
        put("admin_en", "Admin");
        put("admin_ru", "Админимтратор");
    }};

    private static Connection connection;

    public static void setConnection(Connection connection) {
        Main.connection = connection;
    }

    private static String getAscending(HttpServletRequest request) {
        String ascending = request.getParameter("ascending");
        if (ascending == null) {
            ascending = "true";
        }
        return ascending;
    }

    private static String getOrderBy(HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        if (orderBy == null) {
            orderBy = "title";
        }
        return orderBy;
    }

    private static List<Book> getBooksByAuthor(HttpServletRequest request, String lang) {
        List<Book> books = BookDao.getBooks(connection, lang);
        int i = 0;
        while (i != books.size()) {
            if (request.getParameter(books.get(i).getAuthor().getId()) == null) {
                books.remove(i);
            } else {
                i++;
            }
        }
        return books;
    }

    private static void orderBooks(String orderBy, String ascending, List<Book> books) {

        if (orderBy.equals("title")) {
            if (ascending.equals("true")) {
                books.sort(Comparator.comparing(Book::getTitle));
            } else {
                books.sort((book, t1) -> t1.getTitle().compareTo(book.getTitle()));
            }
        }
        if (orderBy.equals("author")) {
            if (ascending.equals("true")) {
                books.sort(Comparator.comparing(book -> book.getAuthor().getFullName()));
            } else {
                books.sort((book, t1) -> t1.getAuthor().getFullName().compareTo(book.getAuthor().getFullName()));
            }
        }
        if (orderBy.equals("edition")) {
            if (ascending.equals("true")) {
                books.sort(Comparator.comparing(book -> book.getEdition().getTitle()));
            } else {
                books.sort((book, t1) -> t1.getEdition().getTitle().compareTo(book.getEdition().getTitle()));
            }
        }
        if (orderBy.equals("editionDate")) {
            if (ascending.equals("true")) {
                books.sort(Comparator.comparing(book -> book.getEdition().getDate()));
            } else {
                books.sort((book, t1) -> t1.getEdition().getDate().compareTo(book.getEdition().getDate()));
            }
        }
    }

    public static void get(HttpServletRequest request, HttpServletResponse response, String language) throws ServletException, IOException {
        request.setAttribute("language", language);
        List<Author> authors;
        authors = AuthorDao.getAuthors(connection, language);

        request.setAttribute("authors", authors);

        String getBook = request.getParameter("book");
        List<Book> books = getBooksByAuthor(request, language);

        String ascending = getAscending(request);
        String orderBy = getOrderBy(request);

        request.setAttribute("ascending", ascending);
        request.setAttribute("orderBy", orderBy);

        String currentPage = "1";
        if (request.getParameter("page") != null) {
            currentPage = request.getParameter("page");
        }
        request.setAttribute("page", currentPage);

        if (getBook == null) {
            if (books.size() == 0) {
                books = BookDao.getBooks(connection, language);
            }
            orderBooks(orderBy, ascending, books);
            request.setAttribute("books", books);
        } else {
            request.setAttribute("books", BookDao.getBook(connection, getBook, language));
        }

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/index.jsp");
        view.forward(request, response);
    }

    private static void logIn(User user, HttpServletRequest request, String language) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", true);
        session.setAttribute("id", user.getId());
        session.setAttribute("type", user.getUserType().getId());
        if (user.getUserType().getId() > 1) {
            for (int i = 1; i < UserDao.getRowsCount(connection) + 1; i++) {
                OrderDao.checkForFine(connection, i);
            }
        } else {
            OrderDao.checkForFine(connection, user.getId());
        }
        session.setAttribute("status", user.getUserStatus().getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("subscription", user.getSubscription());
        session.setAttribute("userOrders", OrderDao.getOrderByUser(connection, user.getId(), language));
        session.setAttribute("userName", user.getUserName());
    }

    private static int addNewUser(Map<String, String[]> parameters, String language) {
        User newUser = new User(1, parameters.get("first_name")[0], parameters.get("last_name")[0],
                parameters.get("email")[0], parameters.get("password")[0], UserTypeDao.getType(connection, types.get("user_" + language), language),
                UserStatusDao.getStatus(connection, statuses.get("active_" + language), language), null);
        return UserDao.insertUser(connection, newUser);
    }

    public static void post(HttpServletRequest request, HttpServletResponse response, String language) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (request.getParameterMap().containsKey("first_name") && addNewUser(request.getParameterMap(), language) != 1) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        User user = UserDao.loginUser(connection, email, password, language);
        if (user != null) {
            logIn(user, request, language);
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
