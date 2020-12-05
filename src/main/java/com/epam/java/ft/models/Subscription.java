package com.epam.java.ft.models;

import java.sql.Date;
import java.util.Objects;

public class Subscription {
    private int id;
    private Date given;
    private Date expires;

    public Subscription(int id, Date given, Date expires) {
        this.id = id;
        this.given = given;
        this.expires = expires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGiven() {
        return given;
    }

    public void setGiven(Date given) {
        this.given = given;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id &&
                Objects.equals(given, that.given) &&
                Objects.equals(expires, that.expires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, given, expires);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", given=" + given +
                ", expires=" + expires +
                '}';
    }
}
