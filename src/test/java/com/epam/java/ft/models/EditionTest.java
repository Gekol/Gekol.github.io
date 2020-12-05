package com.epam.java.ft.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class EditionTest {
    Edition edition = null;
    
    @Before
    public void setUp() {
        edition = new Edition(1, "Edition 1", new java.sql.Date(120, 0, 1));
    }

    @Test
    public void getIdTest() {
        int expected = 1;
        Assert.assertEquals(expected, edition.getId());
    }

    @Test
    public void setIdTest() {
        int expected = 2;
        edition.setId(expected);
        Assert.assertEquals(expected, edition.getId());
    }

    @Test
    public void getTitleTest() {
        String expected = "Edition 1";
        Assert.assertEquals(expected, edition.getTitle());
    }

    @Test
    public void setTitleTest() {
        String expected = "edition2";
        edition.setTitle(expected);
        Assert.assertEquals(expected, edition.getTitle());
    }

    @Test
    public void getDateTest() {
        Date expected = new Date(120, 0, 1);
        Assert.assertEquals(expected, edition.getDate());
    }

    @Test
    public void setDateTest() {
        Date expected = new Date(120, 10, 6);
        edition.setDate(expected);
        Assert.assertEquals(expected, edition.getDate());
    }

    @Test
    public void equalsTest() {
        Assert.assertEquals(edition, edition);
    }

    @Test
    public void equalsSameTest() {
        Edition expected = new Edition(1, "Edition 1", new java.sql.Date(120, 0, 1));
        Assert.assertEquals(expected, edition);
    }
}
