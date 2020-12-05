package com.epam.java.ft.dao;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PrepareTests {
    public static void beforeTests(Connection connection) throws FileNotFoundException, SQLException {
        Statement statement = connection.createStatement();
        String createDBQuery = "CREATE DATABASE IF NOT EXISTS libraryTest";
        statement.executeUpdate(createDBQuery);
        String useDBQuery = "USE libraryTest";
        statement.execute(useDBQuery);
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader("createQueries.sql"));
        sr.runScript(reader);
        //Creating a reader object
        reader = new BufferedReader(new FileReader("testsInserts.sql"));
        //Running the script
        sr.runScript(reader);
    }

    public static void afterTests(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createDBQuery = "DROP DATABASE libraryTest";
        statement.execute(createDBQuery);
    }
}
