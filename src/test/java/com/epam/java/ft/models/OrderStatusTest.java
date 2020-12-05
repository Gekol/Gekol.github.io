package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderStatusTest {
    OrderStatus orderStatus;

    @Before
    public void setUp() {
        orderStatus = new OrderStatus(1, "new");
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, orderStatus.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        orderStatus.setId(expected);
        Assert.assertEquals(expected, orderStatus.getId());
    }

    @Test
    public void getStatusTest() {
        String expected = "new";
        Assert.assertEquals(expected, orderStatus.getStatus());
    }

    @Test
    public void setStatusTest() {
        String expected = "awaiting for user";
        orderStatus.setStatus(expected);
        Assert.assertEquals(expected, orderStatus.getStatus());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(orderStatus, orderStatus);
    }

    @Test
    public void equalsTest() {
        OrderStatus expected = new OrderStatus(1, "new");
        Assert.assertEquals(expected, orderStatus);
    }
}
