package com.revature;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String mMs;
        boolean done = false;

        do {
            System.out.println("Welcome to your Local Bank! Please select an option from the following:\n");
            System.out.println("New User Registration (1):");
            System.out.println("Existing User login (2):");
            System.out.println("Exit (0):");
            mMs = scan.nextLine();
            switch (mMs){
                case "1":
                    NewAccountCreation nu = new NewAccountCreation();
                    break;
                case "2":
                    AccountLogin l = new AccountLogin();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Sorry, please select one of the two:");
                    System.out.println("New User Registration (1):");
                    System.out.println("Existing User login (2):");
                    System.out.println("Exit (0):");
                    break;
            }
        }while (!done);
        //MainMenuSelect m = new MainMenuSelect();
    }

}
