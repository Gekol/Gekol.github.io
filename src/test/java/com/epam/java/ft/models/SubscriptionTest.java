package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class SubscriptionTest {
    Subscription subscription;

    @Before
    public void setUp() {
        subscription = new Subscription(1, new Date(1000), new Date(2000));
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, subscription.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        subscription.setId(expected);
        Assert.assertEquals(expected, subscription.getId());
    }

    @Test
    public void getGivenTest() {
        Date expected = new Date(1000);
        Assert.assertEquals(expected, subscription.getGiven());
    }

    @Test
    public void setGivenTest() {
        Date expected = new Date(1500);
        subscription.setGiven(expected);
        Assert.assertEquals(expected, subscription.getGiven());
    }

    @Test
    public void getExpiresTest() {
        Date expected = new Date(2000);
        Assert.assertEquals(expected, subscription.getExpires());
    }

    @Test
    public void setExpiresTest() {
        Date expected = new Date(2500);
        subscription.setExpires(expected);
        Assert.assertEquals(expected, subscription.getExpires());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(subscription, subscription);
    }

    @Test
    public void equalsTest() {
        Subscription expected = new Subscription(1, new Date(1000), new Date(2000));
        Assert.assertEquals(expected, subscription);
    }
}
