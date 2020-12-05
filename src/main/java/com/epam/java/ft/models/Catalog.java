package com.epam.java.ft.models;

import java.util.List;
import java.util.Objects;

public class Catalog {
    private List<Book> books = null;
    private List<Integer> bookCounts = null;

    public Catalog(List<Book> books, List<Integer> book_counts) {
        this.books = books;
        this.bookCounts = book_counts;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Integer> getBookCounts() {
        return bookCounts;
    }

    public void setBookCounts(List<Integer> bookCounts) {
        this.bookCounts = bookCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(books, catalog.books) &&
                Objects.equals(bookCounts, catalog.bookCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, bookCounts);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "books=" + books +
                ", bookCounts=" + bookCounts +
                '}';
    }
}
