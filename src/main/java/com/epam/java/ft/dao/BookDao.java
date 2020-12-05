package com.epam.java.ft.dao;

import com.epam.java.ft.models.Author;
import com.epam.java.ft.models.Book;
import com.epam.java.ft.models.Edition;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class BookDao {
    public static Logger logger = Logger.getLogger("BookDao");

    public static List<Book> getBook(Connection connection, String querySelector, String language) {
        ArrayList<Book> books = new ArrayList<>();
        String getBooksQuery = "SELECT b.id as b_id, b.title_" + language + " as b_title, book_src, price, fine, " +
                "a.id as a_id, a.full_name_" + language + " as full_name, e.id as e_id, e.title_" + language +
                " as e_title, e.date\n" +
                "FROM (books b JOIN authors a ON b.author_id = a.id) JOIN editions e ON b.edition_id = e.id WHERE " +
                "b.title_" + language + " LIKE \"%" + querySelector + "%\";";
        try (Statement preparedStatement = connection.createStatement()) {
            getBooksList(books, preparedStatement.executeQuery(getBooksQuery));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static List<Book> getBooks(Connection connection, String language) {
        ArrayList<Book> books = new ArrayList<>();
        String getBooksQuery = "SELECT b.id as b_id, b.title_" + language + " as b_title, book_src, price," +
                "fine, a.id as a_id, a.full_name_" + language + " as full_name, e.id as e_id, e.title_" +
                language + " as e_title, e.date\n FROM (books b JOIN authors a ON b.author_id = a.id)\n " +
                "         JOIN editions e ON b.edition_id = e.id ORDER BY b_title";
        try (Statement getBooksStatement = connection.createStatement()) {
            getBooksList(books, getBooksStatement.executeQuery(getBooksQuery));
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return books;
    }

    public static HashMap<String, Integer> getBookCounts(Connection connection, String language) {
        HashMap<String, Integer> bookCounts = new HashMap<>();
        String getBookCountsQuery = "SELECT title_" + language + " as title, COUNT(title_" + language + ") as count " +
                "FROM orders JOIN books ON orders.book_id=books.id GROUP BY books.id ORDER BY title_" + language + ";";
        try (Statement getBooksStatement = connection.createStatement()) {
            ResultSet resultSet = getBooksStatement.executeQuery(getBookCountsQuery);
            while (resultSet.next()) {
                bookCounts.put(resultSet.getString("title"), resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return bookCounts;
    }

    private static void getBooksList(ArrayList<Book> books, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Book book = new Book(resultSet.getInt("b_id"), resultSet.getString("b_title"),
                    resultSet.getString("book_src"), resultSet.getInt("price"), resultSet.getInt("fine"),
                    new Edition(resultSet.getInt("e_id"), resultSet.getString("e_title"),
                            resultSet.getDate("date")),
                    new Author(resultSet.getString("a_id"), resultSet.getString("full_name")));
            books.add(book);
        }
    }

    public static int getFine(Connection connection, int book_id) {
        String getFineQuery = "SELECT fine FROM books WHERE id=?";
        try (PreparedStatement getFineStatement = connection.prepareStatement(getFineQuery)) {
            getFineStatement.setInt(1, book_id);
            ResultSet resultSet = getFineStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("fine");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int insertBook(Connection connection, String titleEn, String titleRu, int price, int fine,
                                 String authorId, int editionId) {
        String insertBookQuery = "INSERT INTO books(title_en, title_ru, price, book_src, fine, author_id, " +
                "edition_id) VALUES (?, ?, ?, 'default.png', ?, ?, ?);";
        try (PreparedStatement insertBookStatement = connection.prepareStatement(insertBookQuery)) {
            insertBookStatement.setString(1, titleEn);
            insertBookStatement.setString(2, titleRu);
            insertBookStatement.setInt(3, price);
            insertBookStatement.setInt(4, fine);
            insertBookStatement.setString(5, authorId);
            insertBookStatement.setInt(6, editionId);
            return insertBookStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int deleteBook(Connection connection, int id) {
        String deleteBookQuery = "DELETE FROM books WHERE id=?";
        try (PreparedStatement deleteBookStatement = connection.prepareStatement(deleteBookQuery)) {
            deleteBookStatement.setInt(1, id);
            return deleteBookStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int updateBook(Connection connection, String titleEn, String titleRu, int fine, int price,
                                 String authorId, int editionId, int id) {
        String deleteBookQuery = "UPDATE books SET title_en=?, title_ru=?, fine=?, price=?, author_id=?, " +
                "edition_id=? WHERE id=?";
        try (PreparedStatement updateBookStatement = connection.prepareStatement(deleteBookQuery)) {
            updateBookStatement.setString(1, titleEn);
            updateBookStatement.setString(2, titleRu);
            updateBookStatement.setInt(3, fine);
            updateBookStatement.setInt(4, price);
            updateBookStatement.setString(5, authorId);
            updateBookStatement.setInt(6, editionId);
            updateBookStatement.setInt(7, id);
            return updateBookStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }
}
