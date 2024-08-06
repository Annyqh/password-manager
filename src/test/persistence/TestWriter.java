package persistence;

import model.PasswordManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class TestWriter {
    final String DESTINATION = "./data/testFileWriter.json";
    final String INVALID_DESTINATION = "./data/0Z.:fake:";

    @Test
    void testWriterInvalidFile() {
        try {
            Writer writer = new Writer(INVALID_DESTINATION);
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPasswordManager() {
        try {
            PasswordManager emptyManager = new PasswordManager();
            Writer writer = new Writer(DESTINATION);
            writer.open();
            writer.write(emptyManager);
            writer.close();

            Reader reader = new Reader(DESTINATION);
            emptyManager = reader.read();
            assertEquals(0, emptyManager.getAccounts().size());

        } catch (IOException ex) {
            fail("IOException occurred which should not be thrown");
        }
    }

    @Test
    void testWriterNormalPasswordManager() {
        try {
            PasswordManager passwordManager = new PasswordManager();
            passwordManager.createAccount("My Account 1", "MyPassword1");
            passwordManager.createAccount("My Account 2", "MyPassword2");
            String accStringPreWrite = passwordManager.viewAccounts();

            Writer writer = new Writer(DESTINATION);
            writer.open();
            writer.write(passwordManager);
            writer.close();

            Reader reader = new Reader(DESTINATION);
            passwordManager = reader.read();
            assertEquals(2, passwordManager.getAccounts().size());

            assertTrue(accStringPreWrite.equals(passwordManager.viewAccounts()));

        } catch (IOException ex) {
            fail("Exception should not be thrown");
        }

    }
}
