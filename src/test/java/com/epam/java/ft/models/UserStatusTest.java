package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserStatusTest {
    UserStatus userStatus;
    
    @Before
    public void setUp() {
        userStatus = new UserStatus(1, "Active");
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, userStatus.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        userStatus.setId(expected);
        Assert.assertEquals(expected, userStatus.getId());
    }

    @Test
    public void getStatusTest() {
        String expected = "Active";
        Assert.assertEquals(expected, userStatus.getStatus());
    }

    @Test
    public void setStatusTest() {
        String expected = "blocked";
        userStatus.setStatus(expected);
        Assert.assertEquals(expected, userStatus.getStatus());
    }
}
