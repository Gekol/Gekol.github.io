package com.epam.java.ft.dao;

import com.epam.java.ft.models.User;
import com.epam.java.ft.models.UserStatus;
import com.epam.java.ft.models.UserType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {
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
    public void getUserByEmailTest() {
        User user = UserDao.getUser(connection, "vader@example.com", "en");
        User expected = new User(1, "Darth", "Vader", "vader@example.com", "1111",
                new UserType(3, "Admin"), new UserStatus(1, "Active"), null);
        Assert.assertEquals(expected, user);
    }

    @Test
    public void getUsersTest() {
        List<User> users = UserDao.getAllUsers(connection, "en");
        ArrayList<User> expected = new ArrayList<User>() {
            {
                add(new User(1, "Darth", "Vader", "vader@example.com", "1111",
                        new UserType(3, "Admin"), new UserStatus(1, "Active"), null));
                add(new User(2, "Luke", "Skywalker", "lskywalker@example.com", "1111",
                        new UserType(2, "Librarian"), new UserStatus(1, "Active"), null));
                add(new User(3, "Mace", "Vindu", "vindu@example.com", "1111",
                        new UserType(1, "User"), new UserStatus(1, "Active"), null));
                add(new User(4, "Obi-Wan", "Kenobi", "kenobi@example.com", "1111",
                        new UserType(1, "User"), new UserStatus(1, "Active"), null));
            }
        };
        Assert.assertEquals(expected, users);
    }

    @Test
    public void insertUserTest() {
        int expected = 1;
        int res = UserDao.insertUser(connection, new User(5, "Darth", "Sidius", "vader1@example.com", "1111",
                new UserType(3, "Admin"), new UserStatus(1, "active"),
                null));
        Assert.assertEquals(expected, res);
        Assert.assertEquals(new ArrayList<User>() {
            {
                add(new User(1, "Darth", "Vader", "vader@example.com", "1111",
                        new UserType(3, "Admin"), new UserStatus(1, "Active"), null));
                add(new User(2, "Luke", "Skywalker", "lskywalker@example.com", "1111",
                        new UserType(2, "Librarian"), new UserStatus(1, "Active"), null));
                add(new User(3, "Mace", "Vindu", "vindu@example.com", "1111",
                        new UserType(1, "User"), new UserStatus(1, "Active"), null));
                add(new User(4, "Obi-Wan", "Kenobi", "kenobi@example.com", "1111",
                        new UserType(1, "User"), new UserStatus(1, "Active"), null));
                add(new User(5, "Darth", "Sidius", "vader1@example.com", "1111",
                        new UserType(3, "Admin"), new UserStatus(1, "Active"), null));
            }
        }, UserDao.getAllUsers(connection, "en"));
    }

    @Test
    public void updateUserStatusTest() {
        int expected = 1;
        int res = UserDao.updateUserStatus(connection, 1, "blocked", "en");
        Assert.assertEquals(expected, res);
    }

    @Test
    public void updateUserTypeTest() {
        int expected = 1;
        int res = UserDao.updateUserType(connection, 1, "librarian", "en");
        Assert.assertEquals(expected, res);
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
