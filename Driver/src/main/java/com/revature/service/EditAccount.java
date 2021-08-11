package com.revature.service;

import com.revature.persistence.DatabaseConnection;

import java.sql.Statement;
import java.util.Scanner;

public class EditAccount extends DatabaseConnection {

    String user;
    Scanner scanner = new Scanner(System.in);
    String editSelection;
    boolean done;

    public EditAccount(String user){
        this.user = user;
        do {
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
                    EditInfo(editSelection, this.user);
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

    public void InvalidMessage(){
        System.out.println("Invalid Selection. Please choose from the following.");
    }

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
