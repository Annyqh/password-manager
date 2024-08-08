package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccount {
    private Account testAccount1;
    // private Account testAccount2; 
    // private Account testAccount3; 
    // private Account testAccount4; 
    // private Account testAccount5; 
    
    @BeforeEach
    void runBefore() {
        testAccount1 = new Account("Account 1", "43123467897!Abc",0001);
        // testAccount2 = new Account("Account 2", "43123467897Abc",0002);
        // testAccount3 = new Account("Account 3", "431234678971A",0003);
        // testAccount4 = new Account("Account 4", "4312346789731",0004);
        // testAccount5 = new Account("Account 5", "12789",0005);
    }

    @Test
    void testAccount() {
        assertEquals("Account 1", testAccount1.getName());
        assertEquals("43123467897!Abc", testAccount1.getPassword());
        assertEquals(0001, testAccount1.getUserid());
        testAccount1.setName("Account Name");
    }

}
