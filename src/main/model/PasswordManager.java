package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a password manager with a list of accounts
 */
public class PasswordManager {
    private List<Account> accounts;

    public PasswordManager() {
        accounts = new ArrayList<Account>();
    }

    // REQUIRES: account
    // MODIFIES: this
    // EFFECTS: adds account to password manager
    public void createAccount(String name, String password) {
        Account account = new Account(name, password);
        accounts.add(account);
    }

    // REQUIRES: account
    // MODIFIES: this
    // EFFECTS: removes account from password manager
    public void removeAccount(Account account) {
        accounts.remove(account);
    }
   
    // EFFECTS: shows a list of accounts with passwords hidden
    public String viewAccounts() {
        String s = "";     
        for (Account account : accounts) {
            s = "Name: " + account.getName() 
            + "Password: " + account.getPassword() 
            + "USER ID: " + account.getUserid();
        }
        return s;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
