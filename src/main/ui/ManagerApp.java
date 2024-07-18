package ui;

import model.PasswordManager;

import java.util.Scanner;

// Password Manager application
public class ManagerApp {
    private PasswordManager manager;
    
    private Scanner scanner;

    // EFFECTS: starts the password manager
    public ManagerApp() {
        scanner = new Scanner(System.in);
        manager = new PasswordManager();
        handleUserInput();
    }

    // MODIFIES: this
    // EFFECTS: parses user input until user quits
    // Source: TellerApp()
    private void handleUserInput() {
        boolean isRunning = true;
        String command = null;

        while (isRunning) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nThank you for using this password manager.");
                isRunning = false;
            } else {
                parseCommand(command);
            }
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

    // REQUIRES: existing account
    // MODIFIES: this
    // EFFECTS: creates a new account with given information
    private void deleteAccount() {
        System.out.println("\nPlease type in the user ID to delete the account that you want.");
        int userid = scanner.nextInt();
        manager.removeAccount(userid-1);
        System.out.println("\nYou have successfully deleted an account.");
    }

    // EFFECTS: prints a list of accounts
    private void viewAllAccounts() {
        String accountsList = manager.viewAccounts();
        System.out.println(accountsList);

        System.out.println("\nPress any key to return to the main menu.");
        scanner.next();
    }

    // EFFECTS: generates a list of options for user to do
    //Source: TellerApp()
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add account");
        System.out.println("\tr -> delete account");
        System.out.println("\tv -> view accounts");
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
                default:
                    System.out.println("Please try a valid command.");
                    break;
            }
        }
    } 
}