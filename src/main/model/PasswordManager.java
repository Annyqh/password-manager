package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a password manager with a list of accounts
 */
public class PasswordManager implements Writable {
    private List<Account> accounts;
    private int userid = 0;

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
        EventLog.getInstance().logEvent(new Event("Added new Account"));
        return account;
    }
    
    // REQUIRES: new Account with user id >= 1
    // MODIFIES: this
    // EFFECTS: adds an account to the password manager, replacing any account with the same userid, and updates userid if >= current userid
    public void setAccount(Account account) {
        int accountId = account.getUserid();
        removeAccount(accountId);
        accounts.add(account);

        if (accountId >= userid){
            userid = accountId + 1;
        }
    }

    // REQUIRES: existing user account 
    // MODIFIES: this
    // EFFECTS: removes account from password manager
    public List<Account> removeAccount(int userid) {
        Iterator<Account> accountsIterator = accounts.iterator();
        while (accountsIterator.hasNext()){
            Account curAccount = accountsIterator.next();
            if (userid == curAccount.getUserid()){
                accountsIterator.remove();
            }
        }
        return accounts;
    }
   
    // EFFECTS: shows a list of accounts with passwords hidden
    public String viewAccounts() {
        String name = "";   
        String password = "";
        int id = 0;   
        String list = "";  
        for (Account account : accounts) {
            name = account.getName();
            password = account.getPassword();
            id = account.getUserid();
            list += "Name: \"" + name + "\"\tPassword: \"" + password  + "\"\tUser ID: " + "" + id + "\n";
        }
        return list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accounts", accountsToJson());
        return json;
    }

    private JSONArray accountsToJson(){
        JSONArray accountsJson = new JSONArray();
        
        for (Account account : accounts){
            accountsJson.put(account.toJson());
        }

        return accountsJson;
    }

}
