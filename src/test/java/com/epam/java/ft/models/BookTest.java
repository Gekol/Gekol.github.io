package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BookTest {

    Book book = null;
    Edition edition = new Edition(1, "Edition 1", new java.sql.Date(120, 0, 1));
    Author author = new Author("rowling", "Joanne Rowling");

    @Before
    public void setUp() {
        book = new Book(1, "Harry Potter and the philosopher's stone",
                "harry_potter_and_the_philosopher's_stone.webp", 10, 5, edition, author);
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, book.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        book.setId(expected);
        Assert.assertEquals(expected, book.getId());
    }

    @Test
    public void getTitleTest() {
        String expected = "Harry Potter and the philosopher's stone";
        Assert.assertEquals(expected, book.getTitle());
    }

    @Test
    public void setTitleTest() {
        String expected = "Harry Potter and the chamber of secrets";
        book.setTitle(expected);
        Assert.assertEquals(expected, book.getTitle());
    }

    @Test
    public void getEditionTest() {
        Assert.assertEquals(edition, book.getEdition());
    }

    @Test
    public void setEditionTest() {
        Edition expected = new Edition(1, "edition1", new Date());
        book.setEdition(expected);
        Assert.assertEquals(expected, book.getEdition());
    }

    @Test
    public void getAuthorTest() {
        Assert.assertEquals(author, book.getAuthor());
    }

    @Test
    public void setAuthorTest() {
        Author expected = new Author("rowling", "Joanne1 Rowling");
        book.setAuthor(expected);
        Assert.assertEquals(expected, book.getAuthor());
    }

    @Test
    public void getBookSrcTest() {
        Assert.assertEquals("harry_potter_and_the_philosopher's_stone.webp", book.getBookSrc());
    }

    @Test
    public void setBookSrcTest() {
        book.setBookSrc("\"harry_potter_and_the_philosopher's_stone1.webp\"");
        Assert.assertEquals("\"harry_potter_and_the_philosopher's_stone1.webp\"", book.getBookSrc());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals(10, book.getPrice());
    }

    @Test
    public void setPriceTest() {
        book.setPrice(15);
        Assert.assertEquals(15, book.getPrice());
    }

    @Test
    public void getFineTest() {
        Assert.assertEquals(5, book.getFine());
    }

    @Test
    public void setFineTest() {
        book.setFine(7);
        Assert.assertEquals(7, book.getFine());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(book, book);
    }

    @Test
    public void equalsTest() {
        Book expected = new Book(1, "Harry Potter and the philosopher's stone",
                "harry_potter_and_the_philosopher's_stone.webp", 10, 5, edition, author);
        Assert.assertEquals(expected, book);
    }
}
