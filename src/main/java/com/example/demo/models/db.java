package com.example.demo.models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    Connection dblink;

    public static Connection getConnection() {
        String username = "root";
        String password = "toor";
        String host = "jdbc:mysql://localhost/javafx2";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection dblink = DriverManager.getConnection(host, username, password);
            return dblink;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
