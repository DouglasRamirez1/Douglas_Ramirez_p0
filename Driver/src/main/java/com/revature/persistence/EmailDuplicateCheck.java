package com.revature.persistence;

import com.revature.collection.HashSet.RevaHashSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmailDuplicateCheck extends DatabaseConnection {

    String email;
    public boolean check;

    public EmailDuplicateCheck(String email){
        this.email = email;
        RevaHashSet<String> emails = new RevaHashSet<String>();

        try {
            String sql = "SELECT email FROM account_logins";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                String sn = rs.getString("email"); //the name of the column not the value
                emails.add(sn); //for String Values
                String o = emails.get(sn);
                if (email.equalsIgnoreCase(o)){
                    System.out.print("Email already exists. Please use a different ");
                    this.check = true;
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean getEmailCheck() {
        return check;
    }

}
