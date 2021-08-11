package com.revature.persistence;
import com.revature.collection.RevArrayList;
import com.revature.model.UserInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankAccountDAO extends databaseConnectionFactory implements DAO {
    /**
     * @param: The user's personal information
     * @return: The user's username and password
     */
    @Override
    public RevArrayList<UserInfo> getLoginInfo() {
        RevArrayList<UserInfo> dbUsers = new RevArrayList<>();

        try {
            String sql = "SELECT username, password FROM account_logins";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                String dbuser = rs.getString("username"); //the name of the column not the value
                String dbpass = rs.getString("password");

                UserInfo info = new UserInfo();
                info.setUsername(dbuser);
                info.setPassword(dbpass);

                dbUsers.add(info);
            }
        } catch (SQLException e) {
            //
        }

        return dbUsers;
    }//Retrieves login information for logging in.

    @Override
    public void save(String username, String firstName, String lastName, String password, String email)//Inserts a new row in account_logins with user information.
    {
        boolean success = true;
        try {
            String sql = "INSERT INTO account_logins (username, first_name, last_name, password, email) " +
                    "VALUES('" + username + "', '" + firstName + "', '" + lastName + "', '" + password + "', '" + email + "');";
            Statement s = connection.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = false;
        }
        if(success){
            System.out.println("New user has been created!");
            System.out.println("Returning to main menu.");
            System.out.println("---------------------------------------------");
        }
    }
}
