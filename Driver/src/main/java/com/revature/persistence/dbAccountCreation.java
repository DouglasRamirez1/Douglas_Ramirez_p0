package com.revature.persistence;
import com.revature.collection.RevArrayList;

import java.sql.Statement;

    public class dbAccountCreation extends DatabaseConnection {
    protected RevArrayList<String> newUserInfo = new RevArrayList<String>();


    public dbAccountCreation(RevArrayList<String> newUserInfo){
        this.newUserInfo = newUserInfo;
        String fn = newUserInfo.get(0);
        String ln = newUserInfo.get(1);
        String u = newUserInfo.get(2);
        String p = newUserInfo.get(3);
        String e = newUserInfo.get(4);
        try {
            String sql = "INSERT INTO account_logins (username, first_name, last_name, password, email) VALUES('" + u + "', '" + fn + "', '" + ln + "', '" + p + "', '" + e + "');";
            Statement s = connection.createStatement();
            s.executeQuery(sql);

        } catch (Exception throwables) {
           //
        }
        try{
            String newAccountTable = "create table " + u + " (account_name VARCHAR(30) primary key, checkings double precision, savings double precision);";
            Statement newAccountTableStatement = connection.createStatement();
            newAccountTableStatement.executeQuery(newAccountTable);
        } catch (Exception throwables) {
            System.out.println("Account Created! Returning to main menu.");
            System.out.println("-------------------------------------------------------");
        }
    }
}
