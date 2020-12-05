package com.epam.java.ft.dao;

import com.epam.java.ft.models.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

;

public class OrderDaoTest {
    Connection connection = ConnectionPool.getInstance("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
            "root", "1111").getConnectionWithDriverManager();
    Logger logger = Logger.getLogger("OrderDaoTest");

    @Before
    public void CreateEmptyDatabase() {
        try {
            PrepareTests.beforeTests(connection);
        } catch (FileNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    @Test
    public void getOrdersTest() {
        List<Order> orders = OrderDao.getAllOrders(connection, "en");
        List<Order> expected = new ArrayList<Order>() {
            {
                add(new Order(1,
                        new User(1, "Darth", "Vader", "vader@example.com", "1111", null, null, null),
                        new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                                new Edition(1, "Edition 1", new Date(120, 0, 1)),
                                new Author("rowling", "Joanne Rowling")), 0, null, new OrderStatus(1, "New"), new OrderType(1, "Subscription")));
                add(new Order(2,
                        new User(3, "Mace", "Vindu", "vindu@example.com", "1111", null, null, null),
                        new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                                new Edition(1, "Edition 1", new Date(120, 0, 1)),
                                new Author("rowling", "Joanne Rowling")), 0, null, new OrderStatus(2, "Awaiting return"), new OrderType(1, "Subscription")));
            }
        };
        Assert.assertEquals(expected, orders);
    }

    @After
    public void DropDatabase() {
        try {
            PrepareTests.afterTests(connection);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }
}
