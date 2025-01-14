package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import model.Account;
import model.Event;
import model.EventLog;
import model.PasswordManager;
import persistence.Reader;
import persistence.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

// Password Manager application
public class ManagerAppGUI extends JFrame implements ActionListener {

    private PasswordManager manager;
    private Account newAccount;
    private JDialog popup;
    private JTable table;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String SAVE_LOCATION = "./data/managerGUI.json";

    // EFFECTS: starts the password manager
    public ManagerAppGUI(String s) {
        super("Password Manager");
        manager = new PasswordManager();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: creates interface for the password manager
    public void initializeGraphics() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel pane = ((JPanel) getContentPane());
        pane.setBorder(new EmptyBorder(13, 13, 13, 13));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        persistencePanel();
        createAccountsPanel();
        taskPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates panel with buttons for persistence tasks
    private void persistencePanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        saveButton.setActionCommand("Save");
        loadButton.setActionCommand("Load");
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        buttonPane.add(saveButton);
        buttonPane.add(loadButton);

        add(buttonPane);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with a list of accounts
    private void createAccountsPanel() {
        Vector<Vector<String>> tableData = new Vector<Vector<String>>();
        Vector<String> columnNames = new Vector<>(Arrays.asList("Name", "Password", "User ID"));

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);

        table = new JTable(model);

        table.setAutoCreateRowSorter(true);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tablePane.setMaximumSize(new Dimension(480, 350));

        add(tablePane);
    }

    // MODIFIES: this
    // EFFECTS: creates panel with buttons for adding/removing tasks
    private void taskPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add Account");
        JButton removeButton = new JButton("Remove Account");

        addButton.setActionCommand("Add");
        removeButton.setActionCommand("Remove");
        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        buttonPane.add(addButton);
        buttonPane.add(removeButton);

        add(buttonPane);
    }

    // REQUIRES: working file
    // EFFECTS: saves password manager
    private void saveManager() {
        Writer writer = null;
        try {
            writer = new Writer(SAVE_LOCATION);
            writer.open();
            writer.write(manager);
        } catch (Exception ex) {
            System.out.println("An error occurred while saving");
        } finally {
            createSavedPopup();
            writer.close();
        }
    }

    // REQUIRES: saved file
    // EFFECTS: loads password manager from previous save
    private void loadManager() {
        try {
            Reader reader = new Reader(SAVE_LOCATION);
            manager = reader.read();
            ArrayList<Account> accounts = (ArrayList<Account>) manager.getAccounts();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            for (Account account : accounts) {
                model.addRow(new Vector<String>(Arrays.asList(
                        account.getName(), account.getPassword(), Integer.toString(account.getUserid()))));
            }

        } catch (Exception ex) {
            System.out.println("An error occurred while loading your accounts");
        }
    }

    // REQUIRES: valid name and password
    // EFFECTS: creates account
    private void addAccount(String name, String password) {
        newAccount = manager.createAccount(name, password);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] { newAccount.getName(), newAccount.getPassword(),
                Integer.toString(newAccount.getUserid()) });
    }
    
    // MODIFIES: this
    // EFFECTS: removes account
    private void removeSelected() {
        int[] indices = table.getSelectedRows();

        for (int curIndex : indices) {
            int modelIndex = table.convertRowIndexToModel(curIndex);
            String userIdStr = (String) table.getModel().getValueAt(modelIndex, 2);
            int userid = Integer.parseInt(userIdStr);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(modelIndex);
            manager.removeAccount(userid);

        }
    }

    // EFFECTS: initializes popup
    private void initializePopup(String title) {
        ManagerAppGUI mainFrame = this;
        popup = new JDialog(mainFrame, title, true);
        popup.setSize(260, 250);

        popup.setLocationRelativeTo(mainFrame);
    }

    // MODIFIES: this
    // EFFECTS: creates popup upon confirmation of saving file
    private void createSavedPopup() {
        // ManagerAppGUI mainFrame = this;
        // popup = new JDialog(mainFrame, "Save Successfully", true);
        initializePopup("Saved Successfully");
        popup.setLayout(new FlowLayout());

        JLabel successText = new JLabel("Saved successfully");

        Image image = null;
        try {
            image = ImageIO.read(new File("images/cube.jpg")).getScaledInstance(250, 190, DO_NOTHING_ON_CLOSE);
        } catch (IOException ex) {
            System.out.println("Error loading image file");
            return;
        }

        JLabel successIcon = new JLabel(new ImageIcon(image));
        JButton okButton = new JButton("OK");

        popup.add(successText);
        popup.add(successIcon);
        popup.add(okButton);

        popup.setVisible(true);
    }

    // EFFECTS: creates popup to handle creation of new account
    // Source:
    // https://stackoverflow.com/questions/19064358/how-to-create-a-popup-jpanel-in-a-jframe
    private void createAccountPopup() {
        initializePopup("Add account");
        popup.setLayout(new GridLayout(3, 2));

        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton addButton = new JButton("Add Account");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        popup.add(new JLabel("Account Name:"));
        popup.add(nameField);
        popup.add(new JLabel("Password:"));
        popup.add(passwordField);

        popup.add(addButton);
        popup.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountName = nameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                addAccount(accountName, password);
                popup.dispose();
            }
        });

        popup.setVisible(true);
    }

    // EFFECTS: handles mouse events
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                saveManager();
                break;
            case "Load":
                loadManager();
                break;
            case "Add":
                createAccountPopup();
                break;
            case "Remove":
                removeSelected();
                break;
            case "Cancel":
                popup.dispose();
        }
    }

    public static void printLog() {
        EventLog log = EventLog.getInstance();
        String eventLog = "Event log: \n";

        for (Event event : log){
            eventLog += event.toString() + "\n";
        }

        System.out.print(eventLog);
    }
}