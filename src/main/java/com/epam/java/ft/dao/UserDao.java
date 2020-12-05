package com.epam.java.ft.dao;

import com.epam.java.ft.models.Subscription;
import com.epam.java.ft.models.User;
import com.epam.java.ft.models.UserStatus;
import com.epam.java.ft.models.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {

    private static Logger logger = Logger.getLogger("UserDao");

    private static User getUser(ResultSet res, String language) throws SQLException {
        return new User(res.getInt("u.id"), res.getString("first_name"), res.getString("last_name"),
                res.getString("email"), res.getString("user_password"),
                new UserType(res.getInt("ut.id"), res.getString("type_" + language)),
                new UserStatus(res.getInt("us.id"), res.getString("status_" + language)),
                (res.getInt("s.id") != 0) ? new Subscription(res.getInt("s.id"), res.getDate("given"), res.getDate("expires")) : null);
    }

    public static User getUser(Connection connection, String email, String language) {
        String getUserQuery = "SELECT * " +
                "FROM users u\n" +
                "         LEFT JOIN subscriptions s on u.subscription_id = s.id\n" +
                "         JOIN userStatuses us ON u.user_status_id = us.id\n" +
                "         JOIN userTypes ut ON u.user_type_id = ut.id " +
                "WHERE u.email=?;";
        try (PreparedStatement getUserStatement = connection.prepareStatement(getUserQuery)) {
            getUserStatement.setString(1, email);
            ResultSet res = getUserStatement.executeQuery();
            if (res.next()) {
                return getUser(res, language);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public static List<User> getAllUsers(Connection connection, String language) {
        String getUsersQuery = "SELECT * FROM users u\n" +
                "         LEFT JOIN subscriptions s on u.subscription_id = s.id\n" +
                "         JOIN userStatuses us ON u.user_status_id = us.id\n" +
                "         JOIN userTypes ut ON u.user_type_id = ut.id; ";
        ArrayList<User> users = new ArrayList<>();
        try (Statement getUsersStatement = connection.createStatement()) {
            ResultSet res = getUsersStatement.executeQuery(getUsersQuery);
            while (res.next()) {
                users.add(getUser(res, language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return users;
    }

    public static int insertUser(Connection connection, User user) {
        String insertQuery = "INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id, subscription_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement insertUserStatement = connection.prepareStatement(insertQuery)) {
            insertUserStatement.setString(1, user.getFirstName());
            insertUserStatement.setString(2, user.getLastName());
            insertUserStatement.setString(3, user.getEmail());
            insertUserStatement.setString(4, user.getPassword());
            insertUserStatement.setInt(5, user.getUserType().getId());
            insertUserStatement.setInt(6, user.getUserStatus().getId());
            insertUserStatement.setNull(7, java.sql.Types.INTEGER);
            return insertUserStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static User loginUser(Connection connection, String email, String password, String language) {
        String query = "SELECT * FROM users u\n" +
                "         LEFT JOIN subscriptions s on u.subscription_id = s.id\n" +
                "         JOIN userStatuses us ON u.user_status_id = us.id\n" +
                "         JOIN userTypes ut ON u.user_type_id = ut.id WHERE email=? AND user_password=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet, language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int updateUserStatus(Connection connection, int id, String status, String language) {
        String getStatusIdQuery = "SELECT id FROM userStatuses WHERE status_" + language + "=?";
        String updateStatusQuery = "UPDATE users SET user_status_id=? WHERE id=?";
        return changeUserMetaData(connection, id, status, getStatusIdQuery, updateStatusQuery);
    }

    public static int updateUserType(Connection connection, int id, String type, String language) {
        String getTypeIdQuery = "SELECT id FROM userTypes WHERE type_" + language + "=?";
        String updateTypeQuery = "UPDATE users SET user_type_id=? WHERE id=?";
        return changeUserMetaData(connection, id, type, getTypeIdQuery, updateTypeQuery);
    }

    public static int updateUserSubscription(Connection connection, int userId, int subscriptionId) {
        String updateSubscriptionQuery = "UPDATE users SET subscription_id=? WHERE id=?";
        try (PreparedStatement updateSubscriptionStatement = connection.prepareStatement(updateSubscriptionQuery)) {
            updateSubscriptionStatement.setInt(1, subscriptionId);
            updateSubscriptionStatement.setInt(2, userId);
            return updateSubscriptionStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    private static int changeUserMetaData(Connection connection, int id, String type, String getQuery, String updateQuery) {
        try (PreparedStatement getTypeIdStatement = connection.prepareStatement(getQuery)) {
            getTypeIdStatement.setString(1, type);
            ResultSet res = getTypeIdStatement.executeQuery();
            if (res.next()) {
                try (PreparedStatement updateStatusStatement = connection.prepareStatement(updateQuery)) {
                    updateStatusStatement.setInt(1, res.getInt("id"));
                    updateStatusStatement.setInt(2, id);
                    return updateStatusStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int getRowsCount(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as count FROM users");
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
