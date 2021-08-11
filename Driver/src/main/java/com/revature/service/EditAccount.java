package com.revature.service;

import com.revature.persistence.databaseConnectionFactory;

import java.sql.Statement;
import java.util.Scanner;

public class EditAccount extends databaseConnectionFactory {

    String user;
    Scanner scanner = new Scanner(System.in);
    String editSelection;
    boolean done;

    /**
     * Allows a user to edit their personal information from the parent table.
     * @param user
     */
    public EditAccount(String user){
        this.user = user;
        do {
            user = this.user;
            System.out.println("What would you like to edit?");
            System.out.println("1) Name");
            System.out.println("2) Username");
            System.out.println("3) Password");
            System.out.println("4) Email");
            System.out.print("0) Done\n->");
            editSelection = scanner.nextLine();
            switch (editSelection) {
                case "1":
                case "2":
                case "3":
                case "4":
                    EditInfo(editSelection, user);
                    System.out.println("---------------------------------------------");
                    break;
                case "0":
                    System.out.println("Returning to main menu.");
                    System.out.println("---------------------------------------------");
                    done = true;
                    break;
                default:
                    InvalidMessage();
            }

        } while(!done);
    }

    /**
     * Message for invalid input
     */
    public void InvalidMessage(){
        System.out.println("Invalid Selection. Please choose from the following.");
    }

    /**
     * Takes in the new user information
     * @param editSelection
     * @param user
     */
    public void EditInfo(String editSelection, String user){
        this.user = user;
        String column;
        switch (editSelection) {
            case "1":
                column = getName();
                System.out.print("Enter your new " + column + "\n->");
                EditInfoSQLCommand(column, user);
                break;
            case "2":
                column = "username";
                System.out.print("Enter your new username\n->");
                EditInfoSQLCommand(column, user);
                break;
            case "3":
                column = "password";
                System.out.print("Enter your new password\n->");
                EditInfoSQLCommand(column, user);
                break;
            case "4":
                column = "email";
                System.out.print("Enter your new email\n->");
                EditInfoSQLCommand(column, user);
                break;
        }
    }

    /**
     * SQL Command to UPDATE entries in the parent table.
     * @param column
     * @param user
     */
    public void EditInfoSQLCommand(String column, String user){
        String newEntry = scanner.nextLine();
        System.out.println("Current column is " + column);
        System.out.println("Current newEntry is " + newEntry);
        System.out.println("Current user username row is " + user);
        try{
            String sql = "UPDATE account_logins SET " + column + " = '" + newEntry + "' where username = '" + user + "';";
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (column.equals("username")){
            this.user = newEntry;
            System.out.println(this.user);
        }
    }

    /**
     * Allows a user to specify changing their first or last name
     * @return
     */
    public String getName(){
        String column;
        boolean nameSelectBoolean = false;
        do {
            System.out.println("Would you like to edit your first or last name?");
            System.out.print("1) First\n2) Last\n->");
            String nameSelection = scanner.nextLine();
            if (nameSelection.equals("1")){
                column = "first_name";
                nameSelectBoolean = true;
                return column;
            }
            else if(nameSelection.equals("2")){
                column = "last_name";
                nameSelectBoolean = true;
                return column;
            }
            else{
                System.out.println("Invalid Option. Please select a valid option.");
            }
        }while(!nameSelectBoolean);
        return "Null";
    }
}
