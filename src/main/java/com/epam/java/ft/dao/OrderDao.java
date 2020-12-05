package com.epam.java.ft.dao;

import com.epam.java.ft.models.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDao {
    public static Logger logger = Logger.getLogger("OrderDao");

    public static List<Order> getAllOrders(Connection connection, String lang) {
        ArrayList<Order> orders = new ArrayList<>();
        String getOrdersQuery = "SELECT *" +
                "FROM orders o\n" +
                "         JOIN users u on o.user_id = u.id\n" +
                "         JOIN books b ON b.id = o.book_id\n" +
                "         JOIN editions e on b.edition_id = e.id\n" +
                "         JOIN authors a on b.author_id = a.id\n" +
                "         JOIN orderStatuses os on o.order_status_id = os.id\n" +
                "         JOIN orderTypes ot ON o.order_type_id = ot.id\n" +
                "WHERE o.order_status_id!=3 ORDER BY u.email;";
        try (Statement getOrdersStatement = connection.createStatement()) {
            getOrders(lang, orders, getOrdersStatement.executeQuery(getOrdersQuery));
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return orders;
    }

    public static List<Order> getOrderByUser(Connection connection, int userId, String language) {
        ArrayList<Order> orders = new ArrayList<>();
        String getOrdersQuery = "SELECT * " +
                "FROM orders o\n" +
                "         JOIN users u on o.user_id = u.id\n" +
                "         JOIN books b ON b.id = o.book_id\n" +
                "         JOIN editions e on b.edition_id = e.id\n" +
                "         JOIN authors a on b.author_id = a.id\n" +
                "         JOIN orderStatuses os on o.order_status_id = os.id\n" +
                "         JOIN orderTypes ot ON o.order_type_id = ot.id\n" +
                " WHERE o.user_id=? AND o.order_type_id=1 AND o.order_status_id!=3 ORDER BY o.id;";
        try (PreparedStatement getOrdersStatement = connection.prepareStatement(getOrdersQuery)) {
            getOrdersStatement.setInt(1, userId);
            getOrders(language, orders, getOrdersStatement.executeQuery());
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return orders;
    }

    public static int getFinesSumByUser(Connection connection, int userId) {
        String getFinesSumQuery = "SELECT SUM(fine) as sum FROM orders o WHERE o.user_id=?";
        try (PreparedStatement getFinesSumStatement = connection.prepareStatement(getFinesSumQuery)) {
            getFinesSumStatement.setInt(1, userId);
            ResultSet resultSet = getFinesSumStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("sum");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }

    private static void getOrders(String language, ArrayList<Order> orders, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Order order = new Order(resultSet.getInt("o.id"), new User(resultSet.getInt("u.id"), resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("email"), resultSet.getString("u.user_password"), null, null, null),
                    new Book(resultSet.getInt("b.id"), resultSet.getString("b.title_" + language), resultSet.getString("b.book_src"), resultSet.getInt("b.price"), resultSet.getInt("b.fine"),
                            new Edition(resultSet.getInt("e.id"), resultSet.getString("e.title_" + language), resultSet.getDate("e.date")),
                            new Author(resultSet.getString("a.id"), resultSet.getString("a.full_name_" + language))),
                    resultSet.getInt("o.fine"), resultSet.getDate("o.deadline"), new OrderStatus(resultSet.getInt("os.id"), resultSet.getString("os.status_" + language)), new OrderType(resultSet.getInt("ot.id"), resultSet.getString("ot.type_" + language)));
            orders.add(order);
        }
    }

    public static void insertNewOrder(Connection connection, int userId, int bookId, int orderTypeId) {
        String insertNewOrderQuery = "INSERT INTO orders(user_id, book_id, fine, deadline, order_status_id, order_type_id) VALUES (?, ?, 0, NULL, 1, ?)";
        try (PreparedStatement insertNewOrderStatement = connection.prepareStatement(insertNewOrderQuery)) {
            insertNewOrderStatement.setInt(1, userId);
            insertNewOrderStatement.setInt(2, bookId);
            insertNewOrderStatement.setInt(3, orderTypeId);
            insertNewOrderStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public static void payFine(Connection connection, int orderId) {
        String payQuery = "UPDATE orders SET fine=0, order_status_id=3 WHERE id=?";
        try (PreparedStatement payStatement = connection.prepareStatement(payQuery)) {
            payStatement.setInt(1, orderId);
            payStatement.executeUpdate();
            OrderDao.setStatus(connection, orderId, 3);
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    private static void calculateFine(Connection connection, int orderId, int bookId) {
        int fine = BookDao.getFine(connection, bookId);
        String calculateFineQuery = "UPDATE orders SET fine=? WHERE id=?";
        try (PreparedStatement calculateFineStatement = connection.prepareStatement(calculateFineQuery)) {
            calculateFineStatement.setInt(1, fine);
            calculateFineStatement.setInt(2, orderId);
            calculateFineStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public static void checkForFine(Connection connection, int userId) {
        String getOverdueOrdersQuery = "SELECT id, book_id FROM orders WHERE deadline<? AND user_id=? AND order_status_id=2";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        try (PreparedStatement getOverdueStatement = connection.prepareStatement(getOverdueOrdersQuery)) {
            getOverdueStatement.setString(1, dateString);
            getOverdueStatement.setInt(2, userId);
            ResultSet resultSet = getOverdueStatement.executeQuery();
            while (resultSet.next()) {
                calculateFine(connection, resultSet.getInt("id"), resultSet.getInt("book_id"));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public static void setDeadline(Connection connection, int orderId, int days) {
        String setDeadlineQuery = "UPDATE orders SET deadline=? WHERE id=?";
        try (PreparedStatement setDeadlineStatement = connection.prepareStatement(setDeadlineQuery)) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(Calendar.getInstance().getTimeInMillis()));
            c.add(Calendar.DATE, days);
            setDeadlineStatement.setDate(1, new Date(c.getTimeInMillis()));
            setDeadlineStatement.setInt(2, orderId);
            setDeadlineStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public static void setStatus(Connection connection, int orderId, int statusId) {
        String setStatusQuery = "UPDATE orders SET order_status_id=? WHERE id=?";
        try (PreparedStatement setStatusStatement = connection.prepareStatement(setStatusQuery)) {
            setStatusStatement.setInt(1, statusId);
            setStatusStatement.setInt(2, orderId);
            setStatusStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
