package com.revature;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class NewAccountCreation{

    Scanner scan = new Scanner(System.in);          // Create a Scanner object
    boolean done = false;                           //boolean for loop conditions
    String newUserName, newUsernameConfirmation;    //Strings to create and verify username
    String newPassword, newPasswordMatch;           //Strings to create and verify password
    //public String[][] Array1 = {{"Test1","Test2"}};
    public String[][] Array1 = new String[2][2];

    NewAccountCreation() {
        System.out.println("Please create a username:");
        newUserName = scan.nextLine();  // Read user input
        System.out.println("Username entered (" + newUserName + "). Please confirm your choice (Y/N):");  // Output user input

        do {
            newUsernameConfirmation = scan.nextLine();
            switch (newUsernameConfirmation){
                case "Y":
                case "y":
                    System.out.println("Confirmed. Username is " + newUserName);
                    done = true;
                    break;
                case "N":
                case "n":
                    System.out.println("Username cleared. Please enter a username:");
                    newUserName = scan.nextLine();  // Read user input
                    System.out.println("Username entered (" + newUserName + "). Please confirm your choice (Y/N):");  // Output user input
                    break;
                default:
                    System.out.println("Invalid Input. Please confirm your username "+ newUserName + "(Y/N):");
            }
        } while (!done);

        done = false;
        System.out.println("Please create a password: ");
        newPassword = scan.nextLine();
        System.out.println("Please confirm your password: ");
        while(!done){
            newPasswordMatch = scan.nextLine();
            if(newPassword.equals(newPasswordMatch)){
                System.out.println("Password confirmed. Thank you. Returning to main menu.");
                done = true;
            }
            else if("0".equals(newPasswordMatch)){
                System.out.println("Password cleared. Please create a password: ");
                newPassword = scan.nextLine();
                System.out.println("Please confirm your password: ");
            }
            else{
                System.out.println("Passwords do not match, try again or select (0) to clear your password:");
            }
        }
        Array1[0][0] = newUserName;
        Array1[0][1] = newPassword;
        System.out.println(Array1[0][0]);
        System.out.println(Array1[0][1]);
    }

    NewAccountCreation(String Login){
        Array1[0][0] = newUserName;
        Array1[0][1] = newPassword;
        getArray1();
    }

    public String[][] getArray1() {
        return Array1;
    }
}
