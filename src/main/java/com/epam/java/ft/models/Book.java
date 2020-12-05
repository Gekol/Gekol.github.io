package com.epam.java.ft.models;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String bookSrc;
    private int price;
    private int fine;
    private Edition edition;
    private Author author;

    public Book(int id, String title, String bookSrc, int price, int fine, Edition edition, Author author) {
        this.id = id;
        this.title = title;
        this.bookSrc = bookSrc;
        this.price = price;
        this.fine = fine;
        this.edition = edition;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookSrc() {
        return bookSrc;
    }

    public void setBookSrc(String bookSrc) {
        this.bookSrc = bookSrc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                price == book.price &&
                fine == book.fine &&
                Objects.equals(title, book.title) &&
                Objects.equals(bookSrc, book.bookSrc) &&
                Objects.equals(edition, book.edition) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, bookSrc, price, fine, edition, author);
    }

    @Override
    public String toString() {
        return "BookEditor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookSrc='" + bookSrc + '\'' +
                ", price=" + price +
                ", fine=" + fine +
                ", edition=" + edition +
                ", author=" + author +
                '}';
    }
}
