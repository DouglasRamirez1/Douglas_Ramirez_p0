package com.revature.resource;
import com.revature.resource.AccountLogin;
import com.revature.resource.NewAccountCreation;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String mMs;
        boolean done = false;
        do {
            firstMessage();
            mMs = scan.nextLine();
            switch (mMs){
                case "1":
                    NewAccountCreation nu = new NewAccountCreation();
                    done = false;
                    continue;
                case "2":
                    AccountLogin l = new AccountLogin();
                    continue;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }while (!done);
        //MainMenuSelect m = new MainMenuSelect();
    }

    public static void firstMessage(){
        System.out.println("Welcome to your Local Bank! Please select an option from the following:");
        System.out.println("New User Registration (1):");
        System.out.println("Existing User login (2):");
        System.out.print("Exit (0):\n->");
    }

}
