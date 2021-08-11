package com.revature.service;
import com.revature.persistence.DatabaseConnection;
import com.revature.collection.HashSet.RevaHashSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Deposit extends DatabaseConnection {
    Scanner scanner = new Scanner(System.in);
    String user_id, n, account_name, bankAccountName;
    boolean done = false;
    int accountType;
    public Deposit(String user_id, String n){
        double deposit1;
        double deposit2;
        this.user_id = user_id;
        this.n = n;
        setAccountName();
        switch (n){
            case "1":
                System.out.print("How much would you like to deposit?\n->$");
                deposit1 = scanner.nextDouble();
                deposit2 = 0.00;
                depositSQLCommand(deposit1, deposit2, bankAccountName);
                System.out.printf("$%.2f have been deposited.",deposit1);
                System.out.println("\nReturning to account menu.");
                System.out.println("--------------------------------------------------------------");
                done = true;
                break;
            case "2":
                System.out.print("How much would you like to deposit?\n->$");
                deposit2 = scanner.nextDouble();
                deposit1 = 0.00;
                depositSQLCommand(deposit1, deposit2, bankAccountName);
                System.out.printf("$%.2f have been deposited.",deposit2);
                System.out.println("\nReturning to account menu.");
                System.out.println("--------------------------------------------------------------");
                done = true;
                break;
            case "3":
                System.out.print("How much would you like to deposit to your checkings account?\n->$");
                deposit1 = scanner.nextDouble();
                System.out.println("");
                System.out.println("--------------------------------------------------------------");
                System.out.print("How much would you like to deposit to your savings account?\n->$");
                deposit2 = scanner.nextDouble();
                depositSQLCommand(deposit1, deposit2, bankAccountName);
                System.out.printf("$%.2f have been deposited into your checkings account.\n",deposit1);
                System.out.printf("$%.2f have been deposited into your savings account.",deposit2);
                System.out.println("\nReturning to account menu.");
                System.out.println("--------------------------------------------------------------");
                done = true;
                break;
            default:
                System.out.println("Invalid Option. Please try again.");
                break;
        }


    }
    public Deposit(String account_name, String user_id, int accountType){
        this.accountType = accountType;
        this.account_name = account_name;
        double depositAmount;
        this.user_id = user_id;
        switch (accountType){
            case 1:
            case 2:
                System.out.print("How much would you like to deposit?\n->$");
                depositAmount = scanner.nextDouble();
                depositSpecificAccountSQLCommand(account_name, depositAmount);
                System.out.printf("$%.2f have been deposited into account [%s].\n",depositAmount, account_name);
                break;
            case 0:
                System.out.println("No money deposited. Returning to main menu...");
                System.out.println("---------------------------------------------");
                break;
            default:
                System.out.println("Invalid Option. Please try again.");
                break;
        }


    }
    public void depositSQLCommand(double deposit1, double deposit2, String bankAccountName){
        try {
            System.out.println("DEPOSITING MONEY...");
            String sql = "INSERT INTO " + user_id + " (account_name, checkings, savings) VALUES('" + bankAccountName + "', '" + deposit1 + "', '" + deposit2 + "');";
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void depositSpecificAccountSQLCommand(String account_name, double depositAmount){
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
            while(rs.next()) {
                existingBalance = rs.getDouble("" + accountColumn + "");
                newBalance = depositAmount + existingBalance;
                String depositMoneySQLCommand = "UPDATE " + this.user_id + " set " + accountColumn + " = '" + newBalance + "' where account_name = '" + account_name + "';";
                Statement deposit = connection.createStatement();
                deposit.executeUpdate(depositMoneySQLCommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean getDone() {
        return done;
    }
    public void setAccountName(){
        do {
            System.out.print("What would you like to call this new account?: ");
            bankAccountName = scanner.nextLine();
            done = false;
            if (bankAccountName.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
                continue;
            }
            else if(bankAccountName.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
            }
            else{
                if(bankAccountNameDuplicateCheck(bankAccountName, user_id)){
                    done = false;
                    continue;
                }
                System.out.print("Bank Account name entered (" + bankAccountName + "). Please confirm your choice (Y/N): ");  // Output user input
                String bankAccountConfirmation = scanner.nextLine();
                switch (bankAccountConfirmation){
                    case "Y":
                    case "y":
                        System.out.println("Confirmed. Bank Account name is " + bankAccountName);
                        done = true;
                        continue;
                    case "N":
                    case "n":
                        System.out.print ("Bank Account name cleared.");
                        continue;
                    default:
                        System.out.print("Invalid Input. Please input (Y/N) to confirm your ");
                        continue;
                }
            }


        } while (!done);
        done = false;
    }
    public boolean bankAccountNameDuplicateCheck(String bankAccountName, String TableName){
        boolean check = false;
        RevaHashSet<String> bankAccountNames = new RevaHashSet<String>();

        try {
            String sql = "SELECT account_name FROM " + TableName + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {

                String sn = rs.getString("account_name"); //the name of the column not the value
                bankAccountNames.add(sn); //for String Values
                String o = bankAccountNames.get(sn);
                if (bankAccountName.equalsIgnoreCase(o)){
                    System.out.print("Account Name already exists. Please try again.\n");
                    check = true;
                }
            }
        }catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return check;
    }
}
