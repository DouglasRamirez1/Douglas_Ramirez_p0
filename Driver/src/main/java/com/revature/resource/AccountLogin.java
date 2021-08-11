package com.revature.resource;
import com.revature.persistence.dbAccountLogin;
import com.revature.collection.RevArrayList;

import java.util.Scanner;

public class AccountLogin {

    Scanner scan = new Scanner(System.in);  // Create a Scanner object
    String username, password;
    boolean done = false;

    public AccountLogin(){
        System.out.println("---------------------------------------------");
        System.out.println("--USER LOGIN--");
        do{
            RevArrayList<String> existingUserInfo = new RevArrayList<String>();
            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            existingUserInfo.add(username);
            existingUserInfo.add(password);
            dbAccountLogin dbAccountLogin = new dbAccountLogin(existingUserInfo);
            if(dbAccountLogin.getDone()){
                done = true;
            }
            }while(!done);
        }
    }

