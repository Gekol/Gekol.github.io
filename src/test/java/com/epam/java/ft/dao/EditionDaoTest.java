package com.epam.java.ft.dao;

import com.epam.java.ft.models.Edition;
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
import java.util.logging.Logger;

public class EditionDaoTest {
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
    public void getEditionsTest() {
        List<Edition> editions = EditionDao.getEditions(connection, "en");
        List<Edition> expected = new ArrayList<Edition>() {
            {
                add(new Edition(1, "Edition 1", new Date(120, 0, 1)));
                add(new Edition(2, "Edition 2", new Date(120, 1, 1)));
                add(new Edition(3, "Edition 3", new Date(120, 2, 1)));
            }
        };
        Assert.assertEquals(expected, editions);
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
