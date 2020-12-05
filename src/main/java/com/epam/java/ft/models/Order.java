package com.epam.java.ft.models;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private int id;
    private User user;
    private Book book;
    private int fine;
    private Date deadline;
    private OrderStatus orderStatus;
    private OrderType orderType;

    public Order(int id, User user, Book book, int fine, Date deadline, OrderStatus orderStatus, OrderType orderType) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.fine = fine;
        this.deadline = deadline;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                fine == order.fine &&
                Objects.equals(user, order.user) &&
                Objects.equals(deadline, order.deadline) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(orderType, order.orderType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, fine, deadline, orderStatus, orderType);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", fine=" + fine +
                ", deadline=" + deadline +
                ", orderStatus=" + orderStatus +
                ", orderType=" + orderType +
                '}';
    }
}
