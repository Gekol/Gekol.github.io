package com.epam.java.ft.dao;

import com.epam.java.ft.models.Author;
import com.epam.java.ft.models.Book;
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
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public class BookDaoTest {
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
    public void getBooksTest() {
        List<Book> books = BookDao.getBooks(connection, "en");
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getOrderedByTitleTest() {
        List<Book> books = BookDao.getBooks(connection, "en");
        books.sort(Comparator.comparing(book -> book.getTitle()));
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getOrderedByAuthorTest() {
        List<Book> books = BookDao.getBooks(connection, "en");
        books.sort(Comparator.comparing(book -> book.getAuthor().getFullName()));
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getOrderedByEditionTest() {
        List<Book> books = BookDao.getBooks(connection, "en");
        books.sort(Comparator.comparing(book -> book.getEdition().getTitle()));
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getOrderedByEditionDateTest() {
        List<Book> books = BookDao.getBooks(connection, "en");
        books.sort(Comparator.comparing(book -> book.getEdition().getDate()));
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getBookByTitle() {
        List<Book> books = BookDao.getBook(connection, "Harry", "en");
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(1, "Harry Potter and the Philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
                add(new Book(2, "Harry Potter and the Chamber of Secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        new Edition(1, "Edition 1", new Date(120, 0, 1)),
                        new Author("rowling", "Joanne Rowling")));
            }
        };
        Assert.assertEquals(expected.toString(), books.toString());
    }

    @Test
    public void getFineTest() {
        int fine = BookDao.getFine(connection, 1);
        Assert.assertEquals(5, fine);
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
