package persistence;

import model.PasswordManager;
import model.Account;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestReader extends TestWritable {
    final String DESTINATION = "./data/testFileReader.json";
    final String EMPTY_FILE_DESTINATION = "./data/testFileReaderEmpty.json";
    final String INVALID_DESTINATION = "./data/fileDNE.json";

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader(INVALID_DESTINATION);
        try {
            PasswordManager pm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPasswordManager() {
        Reader reader = new Reader(EMPTY_FILE_DESTINATION);

        try {
            PasswordManager pm = reader.read();
            assertEquals(0, pm.getAccounts().size());
        } catch (IOException ex) {
            fail("Unexpected IOException, couldn't read the file");
        }

    }

    @Test
    void testReaderNormalPasswordManager() {
        Reader reader = new Reader(DESTINATION);
        try {
            PasswordManager pm = reader.read();

            List<Account> accounts = pm.getAccounts();
            assertEquals(2, accounts.size());
            Account account1 = accounts.get(0);
            Account account2 = accounts.get(1);

            assertNotNull(account1);
            assertNotNull(account2);
            assertEquals(1, account1.getUserid());
            assertEquals(2, account2.getUserid());
            assertTrue("My Account 1".equals(account1.getName()));
            assertTrue("My Account 2".equals(account2.getName()));
            assertTrue("MyPassword1".equals(account1.getPassword()));
            assertTrue("MyPassword2".equals(account2.getPassword()));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}
