package model;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a user account with a name, password, and unique account ID
 */
public class Account implements Writable {
    private String name; // Account name
    private int userid; // Account ID
    private Password password; // password

    // REQUIRES: password and ID has a non-zero length
    // EFFECTS: constructs an account with an account name, password, and ID
    public Account(String name, String password, int userid) {
        this.name = name;
        this.userid = userid;
        this.password = new Password(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("userid", userid);
        json.put("password", password.getPassword());
        return json;
    }
}
