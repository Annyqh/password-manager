package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Source: Json Serialization Demo
// Represents a reader that reads password manager from JSON data stored in file
public class Reader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PasswordManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePasswordManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses password manager from JSON object and returns it
    private PasswordManager parsePasswordManager(JSONObject jsonObject) {
        PasswordManager pm = new PasswordManager();
        addAccounts(pm, jsonObject);
        return pm;
    }

    // MODIFIES: password manager
    // EFFECTS: parses all accounts from JSON object and adds them to the
    // PasswordManager
    private void addAccounts(PasswordManager pm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(pm, nextAccount);
        }
    }

    // MODIFIES: password manager
    // EFFECTS: parses account from JSON object and adds it to password manager
    private void addAccount(PasswordManager pm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        int userid = jsonObject.getInt("userid");
        Account account = new Account(name, password, userid);
        pm.setAccount(account);
    }
}
