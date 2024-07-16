package model;


import java.util.Random;

/*
 * Represents a user account with a name, password, and unique account ID
 */
public class Account {
    private String name;                                            // Account name
    private String password;                                        // password
    private int userid = 0000;                                      // Account ID
    private SecurityStatus status;                                  // password security status
    private static final Random RND = new Random(); 

    // REQUIRES: password and ID has a non-zero length
    // EFFECTS: constructs an account with an account name, password, and ID
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        userid = userid++;
    }

    // EFFECTS: prints a message about password security level
    public String securityLevel(SecurityStatus s) {
        String text;
        String message;

        switch (s) {
            case VERYSTRONG:
                text = "very strong";
                break;
            case STRONG:
                text = "strong";
                break;
            case MEDIUM:
                text = "medium";
                break;
            case WEAK:
                text = "weak";
                break;
            default:
                text = "very weak";
                break;
        }
        message = "Your password strength is" + text + ".";
        return message;
    }

    // REQUIRES: password has a non-zero length
    // EFFECTS: produces "very strong" status if it meets all 5 conditions,
    //                   "strong" status if it meets 4 conditions,
    //                   "medium" status if it meets 3 conditions,
    //                   "weak" status if it meets 2 conditions,
    //                   "very weak" status if it meets 0 or 1 condition,
    public SecurityStatus conditionsMet(String password) {
        // stub
        return status;
    }

    public int passwordContains(String password) {
        // stub
        return 0;
    }

    public Boolean passwordLength(String password) {
        return password.length() >= 12;
    }

    public Boolean passwordContainsSpecialChar() {
        String specialChar = "[^A-Za-z0-9]+";

        return password.matches(specialChar);
    }   
    
    public Boolean passwordContainsLowerCase() {
        String lowerCase = "[a-z]+";

        return password.matches(lowerCase);
    } 
    
    public Boolean passwordContainsUpperCase() {
        String upperCase = "[A-Z]+";

        return password.matches(upperCase);
    }  

    public Boolean passwordContainsNumChar() {
        String numChar = "[0-9]+";

        return password.matches(numChar);
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityStatus(SecurityStatus status) {
        this.status = status;
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
