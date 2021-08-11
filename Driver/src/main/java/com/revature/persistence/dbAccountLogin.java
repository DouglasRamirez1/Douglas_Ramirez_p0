package com.revature.persistence;
import com.revature.resource.AccountOptions;
import com.revature.collection.HashSet.RevaHashSet;
import com.revature.collection.RevArrayList;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbAccountLogin extends databaseConnectionFactory{

    protected RevArrayList<String> userInfo = new RevArrayList<String>();
    boolean done = false;

    /**
     * Takes the user login information and looks for a match in the database
     * @param userInfo
     */
    public dbAccountLogin(RevArrayList<String> userInfo) {
        RevaHashSet<String> dbUsernames = new RevaHashSet<>();
        RevaHashSet<String> dbPasswords = new RevaHashSet<String>();
        this.userInfo = userInfo;
        String user = userInfo.get(0);
        String pass = userInfo.get(1);

        try{
            String sql = "SELECT username, password FROM account_logins";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                String dbuser = rs.getString("username"); //the name of the column not the value
                String dbpass = rs.getString("password");
                dbUsernames.add(dbuser);
                dbPasswords.add(dbpass);
                if(dbUsernames.get(dbuser).equals(user) && dbPasswords.get(dbpass).equals(pass)){
                    System.out.println("Login Successful! Welcome " + user + ".");
                    AccountOptions accountLogin = new AccountOptions(dbuser);
                    done = true;
                    break;
                }
            }
        }
        catch (SQLException e){
            //
        }
        if(done == false){
            System.out.println("No login information found for that username and password combination.");
            System.out.println("Please try again: ");
        }

    }

    /**
     * Returns a boolean for logging in.
     * @return boolean of whether the login completed successfully or not.
     */
    public boolean getDone() {
        return done;
    }
}