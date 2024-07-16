package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.SecurityStatus.*;

public class TestAccount {
    private Account testAccount1;
    private Account testAccount2; 
    private Account testAccount3; 
    private Account testAccount4; 
    private Account testAccount5; 
    
    @BeforeEach
    void runBefore() {
        testAccount1 = new Account("Account 1", "43123467897!Abc");
        testAccount2 = new Account("Account 2", "43123467897Abc");
        testAccount3 = new Account("Account 3", "431234678971A");
        testAccount4 = new Account("Account 4", "4312346789731");
        testAccount5 = new Account("Account 5", "12789");
    }

    @Test
    void testAccount() {
        assertEquals("Account 1", testAccount1.getName());
        assertEquals("43123467897!Abc", testAccount1.getPassword());
        assertEquals(0000, testAccount1.getUserid());
    }

    @Test
    void testSecurityLevel() {
        assertEquals("Your password strength is very strong.",testAccount1.securityLevel(VERYSTRONG));
        assertEquals("Your password strength is strong.",testAccount2.securityLevel(STRONG));
        assertEquals("Your password strength is medium.",testAccount3.securityLevel(MEDIUM));
        assertEquals("Your password strength is weak.",testAccount4.securityLevel(WEAK));
        assertEquals("Your password strength is very weak.",testAccount5.securityLevel(VERYWEAK));
    }

    @Test
    void testConditionsMet() {
        assertEquals(VERYSTRONG,testAccount1.conditionsMet("43123467897!Abc"));
        assertEquals(STRONG,testAccount2.conditionsMet("43123467897Abc"));
        assertEquals(MEDIUM,testAccount3.conditionsMet("431234678971A"));
        assertEquals(WEAK,testAccount4.conditionsMet("4312346789731"));
        assertEquals(VERYWEAK,testAccount5.conditionsMet("12789"));
    }
}
