package com.revature.persistence;

import com.revature.collection.HashSet.RevaHashSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsernameDuplicateCheck extends DatabaseConnection {

    String username;
    public boolean check;

    public UsernameDuplicateCheck(String username){
        this.username = username;
        RevaHashSet<String> studentNames = new RevaHashSet<String>();

        try {
            String sql = "SELECT username FROM account_logins";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                String sn = rs.getString("username"); //the name of the column not the value
                studentNames.add(sn); //for String Values
                String o = studentNames.get(sn);
                if (username.equalsIgnoreCase(o)){
                    System.out.print("Username already exists. Please choose a different ");
                    this.check = true;
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean getCheck() {
        return check;
    }

}
