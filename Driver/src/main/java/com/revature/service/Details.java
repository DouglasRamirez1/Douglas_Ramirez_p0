package com.revature.service;

import com.revature.persistence.DatabaseConnection;
import com.revature.collection.RevArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Details extends DatabaseConnection {

    String user_id;
    int account_id;

    public Details(){}
    public Details(String user_id){
        this.user_id = user_id;
        try {
            String sql = "SELECT * FROM " + user_id + " order by account_name ASC;";
            Statement s = connection.createStatement();
            s.executeQuery(sql);
            ResultSet rs2 = s.executeQuery(sql);
            while (rs2.next())
            {
                String bankAccountName = rs2.getString("account_name");
                double checkings = rs2.getDouble("checkings");
                double savings = rs2.getDouble("savings");
                System.out.println("[Account Name]: " + bankAccountName);
                System.out.printf("[Checkings Balance]: $%.2f",checkings);
                System.out.printf("\n[Savings Balance]: $%.2f",savings);
                System.out.println("\n---------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAccountID() {
        return this.account_id;
    }

    public boolean confirmAccountSelection(String account_id){
        boolean appropriateChoice = false;
        RevArrayList<String> list = new RevArrayList<>();
        try{
            String sql = "SELECT account_name from " + user_id + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                //rs.next();
                String dbuser = rs.getString("account_name"); //the name of the column not the value
                list.add(dbuser);
                if(list.get(dbuser).equals(account_id)){
                    appropriateChoice = true;
                    return appropriateChoice;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return appropriateChoice;
    }
}
