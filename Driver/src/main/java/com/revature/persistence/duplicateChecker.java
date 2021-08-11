package com.revature.persistence;

import com.revature.collection.HashSet.RevaHashSet;
import com.revature.persistence.databaseConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class duplicateChecker extends databaseConnectionFactory {

    String columnName;
    public boolean check;

    /**
     * When creating an email or username, this checks for any existing entries.
     * @param uniqueInput
     * @param columnName
     */
    public duplicateChecker(String uniqueInput, String columnName){
        RevaHashSet<String> columnEntries = new RevaHashSet<String>();

        try {
            String sql = "SELECT " + columnName + " FROM account_logins;";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String sn = rs.getString(columnName); //the name of the column not the value
                columnEntries.add(sn); //for String Values
                String o = columnEntries.get(sn);
                if (uniqueInput.equalsIgnoreCase(o)){
                    System.out.print(columnName.toUpperCase() + " already exists. Please try again: ");
                    this.check = true;
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     *Returns a boolean based on if a match was found earlier.
     */
    public boolean getDuplicateCheck() {
        return this.check;
    }

}