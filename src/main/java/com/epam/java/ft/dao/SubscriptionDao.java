package com.epam.java.ft.dao;

import com.epam.java.ft.models.Subscription;
import org.apache.log4j.Logger;

import java.sql.*;

;

public class SubscriptionDao {
    public static Logger logger = Logger.getLogger("UserDao");

    public static int createSubscription(Connection connection, Subscription subscription) {
        String insertSubscriptionQuery = "INSERT INTO subscriptions(given, expires) VALUES (?, ?);";
        try (PreparedStatement insertBookStatement = connection.prepareStatement(insertSubscriptionQuery)) {
            insertBookStatement.setDate(1, subscription.getGiven());
            insertBookStatement.setDate(2, subscription.getExpires());
            return insertBookStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int getRowsCount(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM subscriptions");
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
