package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPasswordManager {
    private PasswordManager testManagerEmpty;
    private PasswordManager testManager;
    private Account testAccount1;
    private Account testAccount2;
    private List<Account> testAccounts;
    
    @BeforeEach
    void runBefore() {
        testManagerEmpty = new PasswordManager();
        testManager = new PasswordManager();
        testAccount1 = new Account("Account 1", "Password");
        testAccount2 = new Account("Account 2", "Password");
    }

    @Test
    void testPasswordManager() {
        assertTrue(testManagerEmpty.getAccounts().isEmpty());
    }

    @Test
    void testAddAccount() {
        testManager.createAccount("Account 3","Password");
        assertEquals(1,testManager.getAccounts().size());
        assertEquals("Account 3",testManager);
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


