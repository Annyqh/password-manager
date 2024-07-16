package ui;

import model.PasswordManager;

import java.util.Scanner;

// Password Manager application
public class ManagerApp {
    private boolean runApp;
    private PasswordManager manager;
    private Scanner input;

    // EFFECTS: starts the password manager
    public ManagerApp() {
        input= new Scanner(System.in);
        handleUserInput();
        this.manager = manager;
    }

    // MODIFIES: this
    //EFFECTS: parses user input until user quits
    private void handleUserInput() {
        boolean runApp = true;
        String command =null;

        while (runApp) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            parseCommand(command);
        }
    }

    // REQUIRES: non-zero length of name and password
    // MODIFIES: this
    // EFFECTS: creates a new account with given information
    private void addAccount() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: ends the program
    private void endApp() {
        System.out.println("\nThank you for using it.");
        input.close();
    }

    // EFFECTS: generates a list of options for user to do
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add account");
        System.out.println("\tr -> remove account");
        System.out.println("\tv -> view account");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: receives user's input and puts them into action 
    private void parseCommand(String s) {
        if (s.length() > 0) {
            switch (s) {
                case "a":
                    //addAccount();
                    break;
                case "r":
                    // removeAccount();
                    break;
                case "v":
                    manager.viewAccounts();
                    break;
                case "q":
                    runApp = false;
                    endApp();
                    break;
                default:
                    System.out.println("Please try a valid command.");
                    displayMenu();
                    break;
            }
        }
    } 
}




/*
 *      PasswordManager manager = new PasswordManager();
        manager.addAccount("Jennifer","abcdef");
        manager.viewAccounts();
 */
