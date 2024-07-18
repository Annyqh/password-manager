package ui;

import model.Account;
import model.PasswordManager;

import java.util.List;
import java.util.Scanner;

// Password Manager application
public class ManagerApp {
    private List<Account> accounts;
    private PasswordManager manager;
    
    private Scanner scanner;
    private boolean isRunning;

    // EFFECTS: starts the password manager
    public ManagerApp() {
        scanner = new Scanner(System.in);
        handleUserInput();
    }

    // MODIFIES: this
    // EFFECTS: parses user input until user quits
    private void handleUserInput() {
        boolean isRunning = true;
        String command = null;

        while (isRunning) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();
            parseCommand(command);
        }
    }

    // REQUIRES: non-zero length of name and password
    // MODIFIES: this
    // EFFECTS: creates a new account with given information
    private void addAccount() {
        System.out.println("\nPlease put a name down for your account.");
        String name = scanner.next();
        System.out.println("\nPlease put down your password.");
        String password = scanner.next();
        manager.createAccount(name, password); 
        System.out.println("\nYou have successfully added an account.");
    }

    // REQUIRES: non-zero length of name and password
    // MODIFIES: this
    // EFFECTS: creates a new account with given information
    private void deleteAccount() {
        System.out.println("\nPlease type in the user ID to delete the account that you want.");
        int userid = scanner.nextInt();
        manager.removeAccount(userid);
        System.out.println("\nYou have successfully deleted an account.");
    }

    // EFFECTS: prints a list of accounts
    private void viewAllAccounts() {
        manager.viewAccounts();
    }

    // MODIFIES: this
    // EFFECTS: ends the program
    private void endApp() {
        System.out.println("\nThank you for using it.");
        scanner.close();
    }

    // EFFECTS: generates a list of options for user to do
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add account");
        System.out.println("\tr -> delete account");
        System.out.println("\tv -> view account");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: receives user's input and puts them into action 
    private void parseCommand(String s) {
        if (s.length() > 0) {
            switch (s) {
                case "a":
                    addAccount();
                    break;
                case "r":
                    deleteAccount();
                    break;
                case "v":
                    viewAllAccounts();
                    break;
                case "q":
                    isRunning = false;
                    endApp();
                    break;
                default:
                    System.out.println("Please try a valid command.");
                    break;
            }
        }
    } 
}