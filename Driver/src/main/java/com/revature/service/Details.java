package com.revature.service;
import com.revature.persistence.databaseConnectionFactory;
import com.revature.collection.RevArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Details extends databaseConnectionFactory {

    String user_id;

    /**
     * Retrieves all columns with bank account balances under the user's foreign key.
     * @param user_id
     */
    public Details(String user_id){
        this.user_id = user_id;
        try {
            String sql = "SELECT * FROM account_holdings where username = '" + user_id + "' order by account_name ASC;";
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

    /**
     * Confirms an account exists when selecting to deposit to it.
     * @param account_name
     * @return
     */
    public boolean confirmAccountSelection(String account_name){
        boolean appropriateChoice = false;
        RevArrayList<String> list = new RevArrayList<>();
        try{
            String sql = "SELECT account_name from account_holdings where username = '" + user_id + "';";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                String dbuser = rs.getString("account_name"); //the name of the column not the value
                list.add(dbuser);
                if(list.get(dbuser).equals(account_name)){
                    appropriateChoice = true;
                    return appropriateChoice;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return appropriateChoice;
    }
}
