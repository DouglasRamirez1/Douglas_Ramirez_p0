package com.revature.resource;
import com.revature.persistence.BankAccountDAO;
import com.revature.collection.RevArrayList;
import com.revature.persistence.duplicateChecker;

import java.util.Scanner;

public class NewAccountCreation{

    Scanner scan = new Scanner(System.in);                                      // Create a Scanner object
    boolean done = false;                                                       //boolean for loop conditions
    String firstName, lastName, email, newUserName, newUsernameConfirmation;    //Strings to create and verify information
    String newPassword, newPasswordMatch, columnName;                                       //Strings to create and verify password
    public RevArrayList<String> newUserInfo = new RevArrayList<String>();           //ArrayList to contain information to add to database
    int lengthLimit = 50;

    /**
     * Creates a new User to log in.
     */
    public NewAccountCreation() {
        System.out.print("First Name: ");
        firstName = setString();

        System.out.print("Last Name: ");
        lastName = setString();

        System.out.print("Email: ");
        this.columnName = "email";
        email = setUniqueString();

        System.out.print("Please create a username: ");
        this.columnName = "username";
        newUserName = setUniqueString();

        System.out.print("Please create a password: ");
        newPassword = setString();

        confirmInformation();

        BankAccountDAO db = new BankAccountDAO();
        db.save(newUserName, firstName, lastName, newPassword, email); //Runs the SQL command to insert a new row into the logins table.
        System.out.println("Bank Account Created");
    }

    /**
     * Receives user input for a column entry
     * @return a string for a column entry
     */
    public String setString(){
        boolean defects = true;
        do{
            String input;
            input = scan.nextLine();
            if (input.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please try again: ");
            }
            else if(input.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please try again: ");
            }
            else if(input.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please try again: ");
            }
            else{
                defects = false;
                return input;
            }
        }while (defects);
        return "null";
    }

    /**
     * Receives a user input for a UNIQUE column entry
     * @return a unique string for a column entry
     */
    public String setUniqueString(){
        done = false;
        do{
            String uniqueInput = scan.nextLine();
            if (uniqueInput.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please try again: ");
            }
            else if(uniqueInput.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please try again: ");
            }
            else if(uniqueInput.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please try again: ");
            }
            else{
                duplicateChecker duplicateChecker = new duplicateChecker(uniqueInput, this.columnName);
                if(duplicateChecker.getDuplicateCheck()){
                    done = false;
                }
                else{
                    done = true;
                    return uniqueInput;
                }
            }
        }while (!done);

        done = false;
        return "null";
    }

    /**
     * Confirms the entries from the 2 methods above.
     */
    public void confirmInformation(){
        done = false;
        String s;
        do {
            System.out.println( "Please review, edit, and confirm your information: ");
            System.out.println("1) First Name: " + firstName);
            System.out.println("2) Last Name: " + lastName);
            System.out.println("3) Username: "+ newUserName);
            System.out.println("4) Password: " + newPassword);
            System.out.println("5) Email: " + email);
            System.out.println("0) Confirm the information above: ");
            System.out.print("->");
            s = scan.nextLine();
            switch (s){
                case "1":
                    System.out.print("First Name: ");
                    firstName = setString();
                    continue;
                case "2":
                    System.out.print("Last Name: ");
                    lastName = setString();
                    continue;
                case "3":
                    System.out.print("Please create a username: ");
                    this.columnName = "username";
                    newUserName = setUniqueString();
                    done = false;
                    continue;
                case "4":
                    System.out.print("Please create a password: ");
                    newPassword = setString();
                    continue;
                case "5":
                    System.out.print("Email: ");
                    this.columnName = "email";
                    email = setUniqueString();
                    done = false;
                    continue;
                case "0":
                    done = true;
                    continue;
                default:
                    System.out.println("Invalid option, please select one of the following:");
                    break;
            }
        }while (!done);
    }
}
