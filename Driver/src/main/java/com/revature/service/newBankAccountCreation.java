package com.revature.service;
import java.util.Scanner;

public class newBankAccountCreation {
    String user_id;
    Scanner scanner = new Scanner(System.in);
    boolean done = false;

    /**
     * User specifies which bank column to deposit to upon creating a new bank account.
     * @param user_id
     */
    public newBankAccountCreation(String user_id){
        this.user_id = user_id;
        do{
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Would you like to deposit to your Checkings or Savings account?");
            System.out.println("1) Checkings");
            System.out.println("2) Savings");
            System.out.println("3) Checking and Savings");
            System.out.println("0) Cancel");
            System.out.print("-> ");
            String n = scanner.nextLine();
            if(n.equals("0")){
                done = true;
                System.out.println("-----------------------------------------------------");
                System.out.println("Account creation cancelled. Returning to main menu...");
                System.out.println("-----------------------------------------------------");
                continue;
            }
            if(n.equals("1") || n.equals("2") || n.equals("3")){
                Deposit deposit = new Deposit(user_id, n);
                if(deposit.getDone()){
                    done = true;
                    continue;
                }
            }
            else {
                System.out.println("Invalid Option. Please try again.");
            }
        }while(!done);
    }
}
