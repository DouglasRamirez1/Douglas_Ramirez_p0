package com.revature;

import java.util.Scanner;

public class MainMenuSelect {
    Scanner scan = new Scanner(System.in);  // Create a Scanner object
    String mMs;
    boolean done = false;

    MainMenuSelect() {
        do {
            mMs = scan.nextLine();
            switch (mMs){
                case "1":
                    NewAccountCreation nu = new NewAccountCreation();
                    done = true;
                    break;
                case "2":
                    AccountLogin l = new AccountLogin();
                    done = true;
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Sorry, please select one of the two:");
                    System.out.println("New User Registration (1):");
                    System.out.println("Existing User login (2):");
                    break;
            }
        }while (!done);

    }
}
