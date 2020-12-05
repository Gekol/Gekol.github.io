package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class UserTest {
    User user;
    UserType userType = new UserType(1, "User");
    UserStatus userStatus = new UserStatus(1, "Active");
    Subscription subscription = new Subscription(1, new Date(1000), new Date(2000));

    @Before
    public void setUp() {
        user = new User(1, "Georgii", "Sokolovskyi", "george@example.com",
                "1111", userType, userStatus, subscription);
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, user.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        user.setId(expected);
        Assert.assertEquals(expected, user.getId());
    }

    @Test
    public void getFirstNameTest() {
        String expected = "Georgii";
        Assert.assertEquals(expected, user.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        String expected = "Heorhii";
        user.setFirstName(expected);
        Assert.assertEquals(expected, user.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        String expected = "Sokolovskyi";
        Assert.assertEquals(expected, user.getLastName());
    }

    @Test
    public void setLastNameTest() {
        String expected = "Sokolovsky";
        user.setLastName(expected);
        Assert.assertEquals(expected, user.getLastName());
    }

    @Test
    public void getEmailTest() {
        String expected = "george@example.com";
        Assert.assertEquals(expected, user.getEmail());
    }

    @Test
    public void setEmailTest() {
        String expected = "georgii@example.com";
        user.setEmail(expected);
        Assert.assertEquals(expected, user.getEmail());
    }

    @Test
    public void getPasswordTest() {
        String expected = "1111";
        Assert.assertEquals(expected, user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        String expected = "2222";
        user.setPassword(expected);
        Assert.assertEquals(expected, user.getPassword());
    }

    @Test
    public void getUserStatusTest() {
        Assert.assertEquals(userStatus, user.getUserStatus());
    }

    @Test
    public void setUserStatusTest() {
        UserStatus expected = new UserStatus(1, "Blocked");
        user.setUserStatus(expected);
        Assert.assertEquals(expected, user.getUserStatus());
    }

    @Test
    public void getUserTypeTest() {
        Assert.assertEquals(userType, user.getUserType());
    }

    @Test
    public void setUserTypeTest() {
        UserType expected = new UserType(1, "Librarian");
        user.setUserType(expected);
        Assert.assertEquals(expected, user.getUserType());
    }
}
