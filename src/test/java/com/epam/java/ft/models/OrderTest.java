package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class OrderTest {
    Order order = null;
    User user = new User(1, "Georgii", "Sokolovskyi", "george@example.com",
            "1111", new UserType(1, "User"), new UserStatus(1, "Active"),
            new Subscription(1, new Date(1000), new Date(2000)));
    Book book = new Book(1, "Harry Potter and the philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
            new Edition(1, "Edition 1", new java.sql.Date(120, 0, 1)), new Author("rowling", "Joanne Rowling"));
    OrderStatus orderStatus = new OrderStatus(1, "New");
    OrderType orderType = new OrderType(1, "Subscription");

    @Before
    public void setUp() {
        order = new Order(1, user, book, 1, new Date(1000), orderStatus, orderType);
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, order.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        order.setId(expected);
        Assert.assertEquals(expected, order.getId());
    }

    @Test
    public void getUserTest() {
        Assert.assertEquals(user, order.getUser());
    }

    @Test
    public void setUserTest() {
        User expected = new User(1, "Georgii", "Sokolovskyi", "george@example.com",
                "1111", new UserType(1, "User"), new UserStatus(1, "Active"),
                new Subscription(1, new Date(1000), new Date(2000)));
        order.setUser(expected);
        Assert.assertEquals(expected, order.getUser());
    }

    @Test
    public void getFineTest() {
        int expected = 1;
        Assert.assertEquals(expected, order.getFine());
    }

    @Test
    public void setFineTest() {
        int expected = 2;
        order.setFine(expected);
        Assert.assertEquals(expected, order.getFine());
    }

    @Test
    public void getDeadlineTest() {
        Date expected = new Date(1000);
        Assert.assertEquals(expected, order.getDeadline());
    }

    @Test
    public void setDeadlineTest() {
        Date expected = new Date(2000);
        order.setDeadline(expected);
        Assert.assertEquals(expected, order.getDeadline());
    }

    @Test
    public void getOrderStatusTest() {
        Assert.assertEquals(orderStatus, order.getOrderStatus());
    }

    @Test
    public void setOrderStatusTest() {
        OrderStatus expected = new OrderStatus(1, "Done");
        order.setOrderStatus(expected);
        Assert.assertEquals(expected, order.getOrderStatus());
    }

    @Test
    public void getOrderTypeTest() {
        Assert.assertEquals(orderType, order.getOrderType());
    }

    @Test
    public void setOrderTypeTest() {
        OrderType expected = new OrderType(1, "Reading hall");
        order.setOrderType(expected);
        Assert.assertEquals(expected, order.getOrderType());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(order, order);
    }

    @Test
    public void equalsTest() {
        Order expected = new Order(1, user, book, 1, new Date(1000), orderStatus, orderType);
        Assert.assertEquals(expected, order);
    }
}
