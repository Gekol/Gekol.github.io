package com.epam.java.ft.dao;

import com.epam.java.ft.models.Author;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AuthorDaoTest {
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
    public void getAuthorsTest() {
        List<Author> editions = AuthorDao.getAuthors(connection, "en");
        List<Author> expected = new ArrayList<Author>() {
            {
                add(new Author("rand", "ayn rand"));
                add(new Author("rowling", "joanne rowling"));
                add(new Author("greene", "robert greene"));
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
