package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPasswordManager {
    private PasswordManager testManagerEmpty;
    private PasswordManager testManager;
    private Account testAccount1;
    private Account testAccount2;
    
    @BeforeEach
    void runBefore() {
        testManagerEmpty = new PasswordManager();
        testManager = new PasswordManager();
        testAccount1 = new Account("Account 1", "Password");
        testAccount2 = new Account("Account 2", "Password");

        testManager.addAccount(testAccount1);
        testManager.addAccount(testAccount2);
    }

    @Test
    void testPasswordManager() {
        assertTrue(testManagerEmpty.getAccounts().isEmpty());
        assertEquals(2,testManager.getAccounts().size());
    }

    @Test
    void testAddAccount() {
        Account testAccount3 = new Account("Account 3", "Password");
        testManager.addAccount(testAccount3);
        assertEquals(3,testManager.getAccounts().size());
        assertEquals(testAccount1,testManager.getAccounts().get(0));
        assertEquals(testAccount2,testManager.getAccounts().get(1));
        assertEquals(testAccount3,testManager.getAccounts().get(2));
    }

    @Test
    void testRemoveAccount() {
        assertEquals(2,testManager.getAccounts().size());
        testManager.removeAccount(testAccount1);
        assertEquals(testAccount2,testManager.getAccounts().get(0));
        assertEquals(1,testManager.getAccounts().size());
    }

    @Test
    void testViewAccounts() {
        assertEquals(testManager.getAccounts(), testManager.viewAccounts());
    }
}


