package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a password manager with a list of accounts
 */
public class PasswordManager {
    private List<Account> accounts;
    private int userid = 0000;

    public PasswordManager() {
        accounts = new ArrayList<Account>();
    }

    // REQUIRES: name and password
    // MODIFIES: this
    // EFFECTS: adds account to password manager
    public Account createAccount(String name, String password) {
        userid++;
        Account account = new Account(name, password, userid);
        accounts.add(account);
        return account;
    }

    // REQUIRES: existing user account 
    // MODIFIES: this
    // EFFECTS: removes account from password manager
    public List<Account> removeAccount(int userid) {
        for (Account account : accounts) {
            if (userid == account.getUserid()) {
                accounts.remove(account);
            }
        }
        return accounts;
    }
   
    // EFFECTS: shows a list of accounts with passwords hidden
    public String viewAccounts() {
        String name = "";   
        String password = "";   
        String list = "";  
        for (Account account : accounts) {
            name = account.getName();
            password = account.getPassword();
            list = "\nName: " + name + "\tPassword: " + password + "\tUser ID: " + "\"" + userid + "\"";
        }
        return list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getUserid() {
        return userid;
    }

}
