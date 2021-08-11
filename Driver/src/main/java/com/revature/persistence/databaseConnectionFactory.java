package com.revature.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Responsible for establishing a connection to our database.
 */
public class databaseConnectionFactory {
    //"jdbc:postgresql://[ENDPOINT]/[DATABASE]
    private static final String URL = "jdbc:postgresql://database-1.cpzpfw42jf4u.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=douglas_ramirez_p0";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "RevaturePass1234*";

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