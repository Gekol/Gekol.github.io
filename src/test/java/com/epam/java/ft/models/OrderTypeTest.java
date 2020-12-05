package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTypeTest {
    OrderType orderType;

    @Before
    public void setUp() {
        orderType = new OrderType(1, "Subscription");
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, orderType.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        orderType.setId(expected);
        Assert.assertEquals(expected, orderType.getId());
    }

    @Test
    public void getTypeTest() {
        String expected = "Subscription";
        Assert.assertEquals(expected, orderType.getType());
    }

    @Test
    public void setTypeTest() {
        String expected = "Reading hall";
        orderType.setType(expected);
        Assert.assertEquals(expected, orderType.getType());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(orderType, orderType);
    }

    @Test
    public void equalsTest() {
        OrderType expected = new OrderType(1, "Subscription");
        Assert.assertEquals(expected, orderType);
    }
}
