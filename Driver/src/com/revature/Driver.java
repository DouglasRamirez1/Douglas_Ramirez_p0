package com.revature;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        System.out.println("Welcome to your Local Bank! Please select an option from the following:\n");
        System.out.println("New User Registration (1):");
        System.out.println("Existing User login (2):");
        System.out.println("Exit (0):");

        MainMenuSelect m = new MainMenuSelect();
    }
 
}
