package com.revature.service;

import com.revature.persistence.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Withdrawal extends DatabaseConnection {

    String account_name;
    String user_id;
    String n;
    Scanner scanner = new Scanner(System.in);
    boolean done;
    int accountType;

    public Withdrawal(String account_name, String user_id, int accountType) {
        this.account_name = account_name;
        this.accountType = accountType;
        double withdraw1;
        double withdraw2;
        this.user_id = user_id;
        switch (accountType) {
            case 1:
                System.out.print("How much would you like to withdraw?\n->$");
                withdraw1 = scanner.nextDouble();
                withdraw2 = 0.00;
                if(withdrawSpecificAccountSQLCommand(account_name, withdraw1, withdraw2, done)){
                    System.out.printf("$%.2f have been withdrawn from account %s.", withdraw1, account_name);
                    System.out.println("");
                }
                break;
            case 2:
                System.out.print("How much would you like to withdraw?\n->$");
                withdraw2 = scanner.nextDouble();
                withdraw1 = 0.00;
                if(withdrawSpecificAccountSQLCommand(account_name, withdraw1, withdraw2, done)){
                    System.out.printf("$%.2f have been withdrawn from account %s.", withdraw2, account_name);
                    System.out.println("");
                }
                break;
            case 0:
                System.out.println("No money withdrawn. Returning to main menu...");
                System.out.println("---------------------------------------------");
                break;
            default:
                System.out.println("Invalid Option. Please try again.");
                break;
            }


    }
    public boolean withdrawSpecificAccountSQLCommand(String account_name, double withdraw1, double withdraw2, boolean done){
        try {
            String accountColumn = "";
            double existingBalance, newBalance = 0;
            if(this.accountType == 1){
                accountColumn = "checkings";
            }
            if(this.accountType == 2){
                accountColumn = "savings";
            }
            String getBalance = "select " + accountColumn + " from " + this.user_id + " where account_name = '" + account_name + "';";
            Statement id = connection.createStatement();
            id.executeQuery(getBalance);
            ResultSet rs = id.executeQuery(getBalance);
            rs.next();
            existingBalance = rs.getDouble(accountColumn);
            if(this.accountType == 1){
                if(existingBalance >= withdraw1){
                    newBalance = existingBalance - withdraw1;
                    done = true;
                    String depositMoneySQLCommand = "UPDATE " + this.user_id + " set " + accountColumn + " = " + newBalance + " where account_name = '" + account_name + "';" ;
                    Statement deposit = connection.createStatement();
                    deposit.executeUpdate(depositMoneySQLCommand);
                }
                else{
                    System.out.println("You may not withdraw more than the amount in your account. Please try again.");
                    done = false;
                }
            }
            if(this.accountType == 2){
                if(existingBalance >= withdraw2){
                    newBalance = existingBalance - withdraw2;
                    done = true;
                    String depositMoneySQLCommand = "UPDATE " + this.user_id + " set " + accountColumn + " = " + newBalance + " where account_name = '" + account_name + "';" ;
                    Statement deposit = connection.createStatement();
                    deposit.executeUpdate(depositMoneySQLCommand);
                }
                else{
                    System.out.println("You may not withdraw more than the amount in your account. Please try again.");
                    done = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }
}
