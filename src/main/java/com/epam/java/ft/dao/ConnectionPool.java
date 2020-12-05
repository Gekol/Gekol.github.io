package com.epam.java.ft.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    public static Logger logger = Logger.getLogger("ConnectionPool");
    private static ConnectionPool instance = null;
    Connection connection;
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
            cpds.setUser("root");
            cpds.setPassword("1111");
        } catch (PropertyVetoException e) {
            // handle the exception
        }
    }

    private ConnectionPool(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance(String url, String user, String password) {
        if (instance == null)
            instance = new ConnectionPool(url, user, password);
        return instance;
    }

    public static Connection getConnection() {
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            logger.debug("Cannot establish connection!!! " + e.getMessage());
        }
        return null;
    }

    public Connection getConnectionWithDriverManager() {
        return connection;
    }
}