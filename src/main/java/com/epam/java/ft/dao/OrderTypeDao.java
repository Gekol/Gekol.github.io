package com.epam.java.ft.dao;

import com.epam.java.ft.models.OrderType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OrderTypeDao {
    private static Logger logger = Logger.getLogger("OrderTypeDao");

    public static OrderType getOrderType(Connection connection, String type, String language) {
        String selectQuery = "SELECT * FROM orderTypes WHERE type_" + language + "=?";
        OrderType orderType = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, type);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                orderType = new OrderType(resultSet.getInt("id"), resultSet.getString("type_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return orderType;
    }
}
