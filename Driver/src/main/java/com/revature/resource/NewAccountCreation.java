package com.revature.resource;
import com.revature.persistence.EmailDuplicateCheck;
import com.revature.persistence.UsernameDuplicateCheck;
import com.revature.persistence.dbAccountCreation;
import com.revature.collection.RevArrayList;
import java.util.Scanner;

public class NewAccountCreation{

    Scanner scan = new Scanner(System.in);                                      // Create a Scanner object
    boolean done = false;                                                       //boolean for loop conditions
    String firstName, lastName, email, newUserName, newUsernameConfirmation;    //Strings to create and verify information
    String newPassword, newPasswordMatch;                                       //Strings to create and verify password
    public RevArrayList<String> newUserInfo = new RevArrayList<String>();           //ArrayList to contain information to add to database
    int lengthLimit = 50;
    public NewAccountCreation() {
        setFirstName();
        setLastName();
        setEmail();
        setNewUserName();
        setNewPassword();
        confirmInformation();
        newUserInfo.add(firstName);
        newUserInfo.add(lastName);
        newUserInfo.add(newUserName);
        newUserInfo.add(newPassword);
        newUserInfo.add(email);
        dbAccountCreation db = new dbAccountCreation(newUserInfo);

    }
    public void setNewUserName(){
        do {
            done = false;
            System.out.print("Username: ");
            newUserName = scan.nextLine();  // Read user input
            if (newUserName.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
                continue;
            }
            else if(newUserName.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
            }
            else if(newUserName.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please input a ");
            }
            else{
                UsernameDuplicateCheck usernameDuplicateCheck = new UsernameDuplicateCheck(newUserName);
                if(usernameDuplicateCheck.getCheck()){
                    done = false;
                    continue;
                }
                System.out.print("Username entered (" + newUserName + "). Please confirm your choice (Y/N): ");  // Output user input
                newUsernameConfirmation = scan.nextLine();
                switch (newUsernameConfirmation){
                    case "Y":
                    case "y":
                        System.out.println("Confirmed. Username is " + newUserName);
                        done = true;
                        continue;
                    case "N":
                    case "n":
                        System.out.print ("Username cleared. Please enter a ");
                        continue;
                    default:
                        System.out.print("Invalid Input. Please input (Y/N) to confirm your ");
                        continue;
                }
            }


        } while (!done);
        done = false;
    }
    public void setNewPassword(){done = false;

        do{
            done = false;
            System.out.print("Please create a password: ");
            newPassword = scan.nextLine();
            if (newPassword.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
                continue;
            }
            else if(newPassword.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
            }
            else if(newPassword.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please input a ");
            }
            else{
                System.out.print("Please confirm your password or press 0 to clear it: ");
                while(!done){
                    newPasswordMatch = scan.nextLine();
                    if(newPassword.equals(newPasswordMatch)){
                        System.out.println("Password confirmed.");
                        done = true;
                    }
                    else if("0".equals(newPasswordMatch)){
                        System.out.print("Password cleared. Please create a password: ");
                        newPassword = scan.nextLine();
                        System.out.print("Please confirm your password: ");
                    }
                    else{
                        System.out.print("Passwords do not match, try again or select (0) to clear your password: ");
                    }
                }
            }
        }while(!done);

        done = false;
    }
    public void setFirstName(){
        boolean empty = true;
        do{
            System.out.print("First Name: ");
            firstName = scan.nextLine();
            if (firstName.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
                continue;
            }
            else if(firstName.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
            }
            else if(firstName.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please input a ");
            }
            else{
                empty = false;
            }
        }while (empty);


    }
    public void setLastName(){
        boolean empty = true;
        do{
            System.out.print("Last Name: ");
            lastName = scan.nextLine();
            if (lastName.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
                continue;
            }
            else if(lastName.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input a ");
            }
            else if(lastName.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please input a ");
            }
            else{
                empty = false;
            }
        }while (empty);
    }
    public void setEmail(){
        done = false;
        do{
            System.out.print("Email: ");
            email = scan.nextLine();
            if (email.contains(" ")){
                System.out.print ("Field may not be empty or contain spaces. Please input an ");
                continue;
            }
            else if(email.equals("")){
                System.out.print ("Field may not be empty or contain spaces. Please input an ");
            }
            else if(email.length() >= lengthLimit){
                System.out.print ("Field may not be 50 characters long. Please input a ");
            }
            else{
                EmailDuplicateCheck emailDuplicateCheck = new EmailDuplicateCheck(email);
                if(emailDuplicateCheck.getEmailCheck()){
                    done = false;
                    continue;
                }
                else{
                    done = true;
                }
            }
        }while (!done);

        done = false;
    }
    public void confirmInformation(){
        done = false;
        String s;
        do {
            System.out.println("----------------------------------------------------");
            System.out.println( "Please review, edit, and confirm your information: ");
            System.out.println("1) First Name: " + firstName);
            System.out.println("2) Last Name: " + lastName);
            System.out.println("3) Username: "+ newUserName);
            System.out.println("4) Password: " + newPassword);
            System.out.println("5) Email: " + email);
            System.out.println("0) Confirm the information above: ");
            System.out.print("Selection: ");
            s = scan.nextLine();
            switch (s){
                case "1":
                    setFirstName();
                    continue;
                case "2":
                    setLastName();
                    continue;
                case "3":
                    setNewUserName();
                    continue;
                case "4":
                    setNewPassword();
                    continue;
                case "5":
                    setEmail();
                    continue;
                case "0":
                    done = true;
                    continue;
                default:
                    System.out.println("Invalid option, please select one of the following:");
                    break;
            }
        }while (!done);
    }
}
