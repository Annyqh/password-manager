package ui;

import model.Account;
import model.Password;
import model.PasswordManager;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Password Manager application
public class ManagerApp {
    private final String SAVE_LOCATION = "./data/manager-cli.json";

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
        manager.removeAccount(userid - 1);
        System.out.println("\nYou have successfully deleted an account.");
    }

    // EFFECTS: prints a list of accounts
    private void viewAllAccounts() {
        String accountsList = manager.viewAccounts();
        System.out.println(accountsList);

        System.out.println("\nPress any key to return to the main menu.");
        scanner.next();
    }

    // REQUIRES: account must exist
    // EFFECTS: prints security status of a give account
    private void checkStatus() {
        int userid = scanner.nextInt();
        String accountPassword = "";
        String status = "";

        System.out.println("\nPlease type in the user ID to view the security of the account that you want.");
        for (Account account : manager.getAccounts()) {
            if (userid == account.getUserid()) {
                accountPassword = account.getPassword();
                Password password = new Password(accountPassword);
                status = password.getSecurityLevel();
            }
        }
        System.out.println(status);

        System.out.println("\nPress any key to return to the main menu.");
        scanner.next();
    }

    // EFFECTS: creates new file if it doesn't exist
    private void setupStorageFile() {
        File checkFile = new File(SAVE_LOCATION);

        try {
            if (!checkFile.exists()) {
                checkFile.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("An error occurred while setting up the save file. ");
        }
    }

    // REQUIRES: working file
    // EFFECTS: saves password manager
    private void saveManager() {
        Writer writer = null;
        try {
            writer = new Writer(SAVE_LOCATION);
            writer.open();
            writer.write(manager);
        } catch (Exception ex) {
            System.out.println("An error occurred while saving.");
        } finally {
            writer.close();
        }
    }

    // REQUIRES: saved file
    // EFFECTS: loads password manager from previous save
    private void loadManager() {
        try {
            Reader reader = new Reader(SAVE_LOCATION);
            manager = reader.read();
        } catch (Exception ex) {
            System.out.println("An error occurred while loaing your accounts");
        }
    }

    // EFFECTS: generates a list of options for user to do
    // Source: TellerApp()
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add account");
        System.out.println("\tr -> remove account");
        System.out.println("\tv -> view accounts");
        System.out.println("\tc -> check security status of account");
        System.out.println("\ts -> save manager");
        System.out.println("\tl -> load manager");
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
                case "c":
                    checkStatus();
                    break;
                case "s":
                    saveManager();
                    break;
                case "l":
                    loadManager();
                    break;
                default:
                    System.out.println("Please try a valid command.");
                    break;
            }
        }
    }
}