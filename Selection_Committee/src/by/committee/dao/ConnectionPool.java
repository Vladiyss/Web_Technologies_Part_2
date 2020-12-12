package by.committee.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;

public class ConnectionPool {
    private String DATABASE_URL ="jdbc:mysql://localhost:3306/";
    private String DATABASE_NAME = "selection";
    private String DATABASE_USER_NAME = "User1";
    private String DATABASE_PASSWORD = "qwerty";
    private String OPTIONS = "?serverTimezone=Europe/Minsk&allowMultiQueries=true";

    private static ConnectionPool instance;

    private ConnectionPool() {
    	
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection connect() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            connection = DriverManager.getConnection(DATABASE_URL+ DATABASE_NAME + OPTIONS, DATABASE_USER_NAME, DATABASE_PASSWORD);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}