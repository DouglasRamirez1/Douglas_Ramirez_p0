package com.revature.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Responsible for establishing a connection to our database.
 */
public class databaseConnectionFactory {
    //"jdbc:postgresql://[ENDPOINT]/[DATABASE]
    private static final String URL = "URL";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    public static Connection connection;

    public databaseConnectionFactory(){

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("---------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}