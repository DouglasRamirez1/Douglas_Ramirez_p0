package com.revature.resource;
import com.revature.service.*;
import java.util.Scanner;

public class AccountOptions {
    boolean done = false;
    String user;
    String account_ID;
    int accountType;
    public AccountOptions(String user){
        Scanner scan = new Scanner(System.in);
        Details accountSelection;
        this.user = user;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1) Create new bank account.");
            System.out.println("2) Make a Deposit.");
            System.out.println("3) Make a Withdrawal");
            System.out.println("4) View bank account details.");
            System.out.println("5) Edit personal information.");
            System.out.print("0) Sign Out\n->");
            String accountOptionsSelection = scan.nextLine();
            switch (accountOptionsSelection) {
                case "1":
                    newBankAccountCreation newBankAccountCreation = new newBankAccountCreation(user);
                    break;
                case "2":
                    System.out.println("Which account would you like to deposit to?");
                    accountSelection = new Details(user);
                    System.out.print("->");
                    account_ID = scan.nextLine();
                    if(accountSelection.confirmAccountSelection(account_ID)){
                        accountType = CheckingsOrSavings();
                        Deposit pureDeposit = new Deposit(account_ID, user, accountType);
                        break;
                    }
                    else{
                        System.out.println("Account does not exist. Returning to main menu.");
                        System.out.println("---------------------------------------");
                    }
                    break;
                case "3":
                    System.out.println("Which account would you like to withdraw from?");
                    accountSelection = new Details(user);
                    System.out.print("->");
                    account_ID = scan.nextLine();
                    if(accountSelection.confirmAccountSelection(account_ID)){
                        accountType = CheckingsOrSavings();
                        Withdrawal pureWithdrawal = new Withdrawal(account_ID, user, accountType);
                    }
                    else{
                        System.out.println("Account does not exist. Returning to main menu.");
                        System.out.println("---------------------------------------");
                    }
                    break;
                case "4":
                    Details details = new Details(user);
                    break;
                case "5":
                    EditAccount editAccount = new EditAccount(user);
                    break;
                case "0":
                    System.out.println("Logging Out...Have a wonderful day!");
                    System.out.println("----------------------------------------------------------");
                    done = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please select one of the given options.");
                    System.out.println("----------------------------------------------------------");
                    break;
            }
        }while(!done);

    }
    public static int CheckingsOrSavings(){
        Scanner scan = new Scanner(System.in);
        boolean c_or_s = false;
        int selection = 0;
        do{
            System.out.println("Please select to your Checkings or Savings account:");
            System.out.print("1) Checkings\n2) Savings\n0) Cancel\n->");
            selection = scan.nextInt();
            if(selection ==1 | selection == 2 || selection ==0){
                c_or_s = true;
                return selection;
            }
            else{
                System.out.println("Invalid selection. Please try again.");
                continue;
            }
        }while(!c_or_s);

        return selection;
    }
}
