package com.epam.java.ft.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CatalogDaoTest {
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
    public void getBookAmountTest() {
        int amount = CatalogDao.getBookAmount(connection, 1);
        Assert.assertEquals(5, amount);
    }

    @Test
    public void changeBookAmountTest() {
        CatalogDao.changeBookAmount(connection, 1, 10);
        int amount = CatalogDao.getBookAmount(connection, 1);
        Assert.assertEquals(10, amount);
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
