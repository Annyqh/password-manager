package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPasswordManager {
    private PasswordManager testManagerEmpty;
    private PasswordManager testManager;

    @BeforeEach
    void runBefore() {
        testManagerEmpty = new PasswordManager();
        testManager = new PasswordManager();
    }

    @Test
    void testPasswordManager() {
        assertTrue(testManagerEmpty.getAccounts().isEmpty());
    }

    @Test
    void testAddAccount() {
        Account testAccount3 = testManager.createAccount("Account 3", "Password");
        assertEquals(1, testManager.getAccounts().size());
        assertEquals(testAccount3, testManager.getAccounts().get(0));
    }

    @Test
    void testRemoveAccount() {
        testManager.createAccount("Account 1", "Password");
        testManager.createAccount("Account 2", "Password");
        assertEquals(2, testManager.getAccounts().size());
        testManager.removeAccount(1);
        assertEquals(1, testManager.getAccounts().size());
    }

    @Test
    void testViewAccounts() {
        String empty = testManagerEmpty.viewAccounts();
        assertTrue(empty.isEmpty());

        // Account testAccount1 = testManager.createAccount("Account 1", "Password1");
        // Account testAccount2 = testManager.createAccount("Account 2", "Password2");

        assertEquals("Name: \"Account 1\"\tPassword: \"Password1\"\tUser ID: 1\nName: \"Account 2\"\tPassword: \"Password2\"\tUser ID: 2\n", testManager.viewAccounts());
    }


}
