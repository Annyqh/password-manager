
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Account;
import model.PasswordManager;

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
        Account testAccount1 = testManager.createAccount("Account 1", "Password");
        Account testAccount2 = testManager.createAccount("Account 2", "Password");
        assertEquals("Name: Account 1 Password: Password UserID: 0001 Name: Account 2 Password: Password UserID: 0002", testManager.viewAccounts());
    }
}
