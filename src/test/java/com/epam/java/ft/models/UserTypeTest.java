package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTypeTest {
    UserType userType;

    @Before
    public void setUp() {
        userType = new UserType(1, "Librarian");
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, userType.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        userType.setId(expected);
        Assert.assertEquals(expected, userType.getId());
    }

    @Test
    public void getTypeTest() {
        String expected = "Librarian";
        Assert.assertEquals(expected, userType.getType());
    }

    @Test
    public void setTypeTest() {
        String expected = "User";
        userType.setType(expected);
        Assert.assertEquals(expected, userType.getType());
    }
}
