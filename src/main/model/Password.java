package model;

import java.util.regex.Pattern;

// User's password for a given account 
public class Password {
    private String password;
    private Boolean hasSpecialChar;
    private Boolean hasUpperChar;
    private Boolean hasLowerChar;
    private Boolean hasNumChar;
    private int numReqMet;

    // REQUIRES: password has a non-zero length
    // EFFECTS: constructs a password
    public Password(String p) {
        this.password = p;
        numReqMet = 0;
    }

    // MODIFIES: this
    // EFFECTS: produces the number of requirements that the password has met
    public int meetsRequirements() {
        int num = 0;
        meetsCharReq();

        if (hasSpecialChar) {
            num++;
        }
        if (hasUpperChar) {
            num++;
        }
        if (hasLowerChar) {
            num++;
        }
        if (hasNumChar) {
            num++;
        }
        if (password.length() >= 12) {
            num++;
        }
        numReqMet = num;
        return numReqMet;
    }

    // EFFECTS: checks whether or not a password meets the requirements
    public void meetsCharReq() {
        Pattern upperPattern = Pattern.compile("[A-Z]+");
        Pattern lowerPattern = Pattern.compile("[a-z]+");
        Pattern numberPattern = Pattern.compile("\\d+");
        Pattern specialPattern = Pattern.compile("[^A-Za-z0-9]+");

        hasUpperChar = upperPattern.matcher(password).find();
        hasLowerChar = lowerPattern.matcher(password).find();
        hasNumChar = numberPattern.matcher(password).find();
        hasSpecialChar = specialPattern.matcher(password).find();
    }

    public String getPassword() {
        return password;
    }

    public int getNumReqMet() {
        return numReqMet;
    }
}
