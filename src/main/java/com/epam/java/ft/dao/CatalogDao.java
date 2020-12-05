package com.epam.java.ft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CatalogDao {
    private static Logger logger = Logger.getLogger("OrderTypeDao");

    public static int getBookAmount(Connection connection, int id) {
        String checkQuery = "SELECT book_amount FROM catalog WHERE book_id=?;";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    public static int changeBookAmount(Connection connection, int id, int amount) {
        String changeBookAmountQuery = "UPDATE catalog SET book_amount=? WHERE book_id=?;";
        try (PreparedStatement changeBookAmountStatement = connection.prepareStatement(changeBookAmountQuery)) {
            changeBookAmountStatement.setInt(1, amount);
            changeBookAmountStatement.setInt(2, id);
            return changeBookAmountStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }
}
