package com.epam.java.ft.dao;

import com.epam.java.ft.models.UserType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeDao {
    private static Logger logger = Logger.getLogger("UserDao");

    public static UserType getType(Connection connection, String type, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM userTypes ut\n" +
                "WHERE ut.type_" + language + "=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setString(1, type);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
                return new UserType(resultSet.getInt("id"), resultSet.getString("type_" + language));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
}
