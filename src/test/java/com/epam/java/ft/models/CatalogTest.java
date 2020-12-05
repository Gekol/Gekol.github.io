package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest {
    Catalog catalog = null;
    Edition edition = new Edition(1, "Edition 1", new java.sql.Date(120, 0, 1));
    Author author = new Author("rowling", "Joanne Rowling");

    @Before
    public void setUp() {
        List<Book> books = new ArrayList<Book>() {
            {
                add(new Book(1, "Harry Potter and the philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5, edition, author));
                add(new Book(1, "Harry Potter and the chamber of secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5, edition, author));
            }
        };
        List<Integer> bookCounts = new ArrayList<Integer>() {
            {
                add(3);
                add(7);
                add(1);
            }
        };
        catalog = new Catalog(books, bookCounts);
    }

    @Test
    public void getBooksTest() {
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(1, "Harry Potter and the philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5,
                        edition, author));
                add(new Book(1, "Harry Potter and the chamber of secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5,
                        edition, author));
            }
        };
        Assert.assertEquals(expected, catalog.getBooks());
    }

    @Test
    public void setBooksTest() {
        List<Book> expected = new ArrayList<Book>() {
            {
                add(new Book(1, "Harry Potter and the philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5, edition, author));
                add(new Book(1, "Harry Potter and the chamber of secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5, edition, author));
            }
        };
        catalog.setBooks(expected);
        Assert.assertEquals(expected, catalog.getBooks());
    }

    @Test
    public void getBooksCountTest() {
        List<Integer> expected = new ArrayList<Integer>() {
            {
                add(3);
                add(7);
                add(1);
            }
        };
        Assert.assertEquals(expected, catalog.getBookCounts());
    }

    @Test
    public void setBooksCountTest() {
        List<Integer> expected = new ArrayList<Integer>() {
            {
                add(3);
                add(7);
                add(1);
            }
        };
        catalog.setBookCounts(expected);
        Assert.assertEquals(expected, catalog.getBookCounts());
    }

    @Test
    public void equalsSameTest() {
        Assert.assertEquals(catalog, catalog);
    }

    @Test
    public void equalsTest() {
        List<Book> books = new ArrayList<Book>() {
            {
                add(new Book(1, "Harry Potter and the philosopher's stone", "harry_potter_and_the_philosopher's_stone.webp", 10, 5, edition, author));
                add(new Book(1, "Harry Potter and the chamber of secrets", "harry_potter_and_the_chamber_of_secrets.jpg", 10, 5, edition, author));
            }
        };
        List<Integer> bookCounts = new ArrayList<Integer>() {
            {
                add(3);
                add(7);
                add(1);
            }
        };
        Catalog expected = new Catalog(books, bookCounts);
        Assert.assertEquals(expected, catalog);
    }
}
