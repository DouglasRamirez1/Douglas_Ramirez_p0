package com.revature.resource;
import com.revature.resource.AccountLogin;
import com.revature.resource.NewAccountCreation;

import java.util.Scanner;

public class Driver {
    /**Creates the welcome message for the program and acts based on an input.
     *
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);                                          // Create a Scanner object
        String mMs;
        boolean done = false;
        do {
            firstMessage();
            mMs = scan.nextLine();
            switch (mMs){
                case "1":
                    NewAccountCreation newAccountCreation = new NewAccountCreation();  //Initializes the Class NewAccountCreation
                    done = false;
                    continue;
                case "2":
                    AccountLogin l = new AccountLogin();                               //Initializes the Class AccountLogin
                    continue;
                case "0":
                    System.exit(0);                                             //Allows a user to end the program
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }while (!done);
    }

    public static void firstMessage(){
        System.out.println("Welcome to your Local Bank! Please select an option from the following:");
        System.out.println("New User Registration (1):");
        System.out.println("Existing User login (2):");
        System.out.print("Exit (0):\n->");
    }                                           //Displays the opening message

}
