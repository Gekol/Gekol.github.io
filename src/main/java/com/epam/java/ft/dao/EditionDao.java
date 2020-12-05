package com.epam.java.ft.dao;

import com.epam.java.ft.models.Edition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EditionDao {
    private static Logger logger = Logger.getLogger("EditionDao");

    public static List<Edition> getEditions(Connection connection, String language) {
        ArrayList<Edition> editions = new ArrayList<>();
        String getEditionsQuery = "SELECT id, title_" + language + " as title, date FROM editions";
        try (Statement getBooksStatement = connection.createStatement()) {
            ResultSet resultSet = getBooksStatement.executeQuery(getEditionsQuery);
            while (resultSet.next()) {
                editions.add(new Edition(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("date")));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return editions;
    }
}
