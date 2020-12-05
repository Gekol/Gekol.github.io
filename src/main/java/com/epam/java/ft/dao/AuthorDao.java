package com.epam.java.ft.dao;

import com.epam.java.ft.models.Author;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao {
    public static Logger logger = Logger.getLogger("AuthorDao");

    public static List<Author> getAuthors(Connection connection, String lang) {
        ArrayList<Author> authors = new ArrayList<>();
        String getAuthorsQuery = "SELECT id, full_name_" + lang + " as full_name FROM authors ORDER BY full_name_" + lang;
        try (Statement getAuthorsStatement = connection.createStatement()) {
            getAuthorsList(authors, getAuthorsStatement.executeQuery(getAuthorsQuery));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private static void getAuthorsList(ArrayList<Author> authors, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Author author = new Author(resultSet.getString("id"), resultSet.getString("full_name"));
            authors.add(author);
        }
    }
}
