package model;

/*
 * Represents a user account with a name, password, and unique account ID
 */
public class Account {
    private String name;                                            // Account name
    private String password;                                        // password
    private int userid;                                             // Account ID
    // REQUIRES: password and ID has a non-zero length
    // EFFECTS: constructs an account with an account name, password, and ID
    public Account(String name, String password, int userid) {
        this.name = name;
        this.password = password;
        this.userid = userid;
    }

    // EFFECTS: prints a message about password security level
    public String securityLevel(int n) {
        String status;
        String message;

        switch (n) {
            case 5:
                status = "very strong";
                break;
            case 4:
                status = "strong";
                break;
            case 3:
                status = "medium";
                break;
            case 2:
                status = "weak";
                break;
            default:
                status = "very weak";
                break;
        }
        message = "Your password strength is " + status + ".";
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getUserid() {
        return this.userid;
    }
}
