package com.epam.java.ft.models;

import java.util.Objects;

public class OrderType {
    private int id;
    private String type;

    public OrderType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderType orderType = (OrderType) o;
        return id == orderType.id &&
                Objects.equals(type, orderType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
