package com.epam.java.ft.dao;

import com.epam.java.ft.models.UserStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusDao {
    private static Logger logger = Logger.getLogger("UserDao");

    public static UserStatus getStatus(Connection connection, String status, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM userStatuses us\n" +
                "WHERE us.status_" + language + "=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setString(1, status);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
                return new UserStatus(resultSet.getInt("id"), resultSet.getString("status_" + language));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
