package com.revature;
import java.util.Scanner;

public class AccountLogin {

    //NewAccountCreation newClassObj = new NewAccountCreation("Login");
    Scanner scan = new Scanner(System.in);  // Create a Scanner object
    String username, password;
    boolean done = false;
    String[] tempLogin = new String[2];
    String[][] Array2 = {{"DougRami","123"},{"John","456"},{"Dave","789"}};
    int count = 0;
    AccountLogin(){
        System.out.println("WORK IN PROGRESS");
        System.out.println(Array2.length);
        System.out.println(Array2[0].length);

        do{
            System.out.println("Username: ");
            username = scan.nextLine();
            tempLogin[0] = username;
            System.out.println("Password: ");
            password = scan.nextLine();
            tempLogin[1] = password;
            for (int i=0; i < Array2.length ; i++)
            {
                for (int j=0; j < Array2[0].length ; j++){
                    if(tempLogin[0].equals(Array2[i][j])) {
                        j++;
                        if (tempLogin[1].equals(Array2[i][j])) {
                            done = true;
                        }
                    }
                }
            }
            if(!done){
                System.out.println("Incorrect username and password combination. Please try again.");
            }
            else{
                System.out.println("Login successful!");
            }
        }while(!done);
    }
}
