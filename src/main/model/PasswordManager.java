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
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // REQUIRES: account
    // MODIFIES: this
    // EFFECTS: removes account from password manager
    public void removeAccount(Account account) {
        accounts.remove(account);
    }
   
    // EFFECTS: shows a list of accounts with passwords hidden
    public List<Account> viewAccounts() {
        return accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
