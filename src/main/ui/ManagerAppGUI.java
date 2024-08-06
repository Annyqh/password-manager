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
import model.PasswordManager;
import persistence.Reader;
import persistence.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

// Password Manager application
public class ManagerAppGUI extends JFrame implements ActionListener{
    
    private final Dimension WINDOW_SIZE = new Dimension(500, 500);
    private final String SAVE_LOCATION = "./data/manager-gui.json";

    private PasswordManager manager;
    
    private JTable table;

 

    // EFFECTS: starts the password manager
    public ManagerAppGUI(String s) {
        super("Password Manager");
        manager = new PasswordManager();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: creates interface for the password manager
    public void initializeGraphics() {
        setPreferredSize(WINDOW_SIZE);
        JPanel pane = ((JPanel) getContentPane());
        pane.setBorder(new EmptyBorder(13, 13, 13, 13) );
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        persistenceButtons();
        createAccountsPanel();
        taskButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);       
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
                createAddDialogBox();
                break;
            case "Remove":
                removeSelected();
                break;
        } 
    }

    // MODIFIES: this
    // EFFECTS: creates panel with buttons for persistence tasks
    private void persistenceButtons() {
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
    private void taskButtons() {
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
            ArrayList<Account> accounts = (ArrayList<Account>)manager.getAccounts();
            DefaultTableModel model = (DefaultTableModel)table.getModel();


            for (Account account : accounts) {
                model.addRow(new Vector<String>(Arrays.asList(
                    account.getName(), account.getPassword(), Integer.toString(account.getUserid())
                )));
            } 

        } catch (Exception ex) {
            System.out.println("An error occurred while loading your accounts");
        }
    }

    // REQUIRES: valid name and password
    // EFFECTS: creates account 
    private void addAccount(String name, String password) {
        Account createdAccount = manager.createAccount(name, password);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addRow(new Object[]{createdAccount.getName(), createdAccount.getPassword(), Integer.toString(createdAccount.getUserid())});
    }

    // REQUIRES: account selected
    // MODIFIES: this
    // EFFECTS: removes account
    private void removeSelected(){
        int[] indices = table.getSelectedRows();

        for (int curIndex : indices) {
            int modelIndex = table.convertRowIndexToModel(curIndex);
            String userIdStr = (String)table.getModel().getValueAt(modelIndex, 2);
            int userid = Integer.parseInt(userIdStr);

            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.removeRow(modelIndex);
            manager.removeAccount(userid);
            
        }
    }

    // MODIFIES: this
    // EFFECTS: creates popup upon confirmation of saving file
    private void createSavedPopup() {
        ManagerAppGUI mainFrame = this;
        JDialog dialog = new JDialog(mainFrame, "Save Successfully", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(260, 250);


        JLabel successText = new JLabel("Saved successfully");

        Image image = null;
        try {
            image = ImageIO.read(new File("images/cube.jpg")).getScaledInstance(250, 190, DO_NOTHING_ON_CLOSE);
        } catch (IOException ex){
            System.out.println("Error loading image file");
            return;
        }
        
        JLabel successIcon = new JLabel(new ImageIcon(image));

        JButton okButton = new JButton("OK");

        dialog.add(successText);
        dialog.add(successIcon);
        dialog.add(okButton);

        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // EFFECTS: creates popup to handle creation of new account
    private void createAddDialogBox(){
        ManagerAppGUI mainFrame = this;
        JDialog dialog = new JDialog(mainFrame, "Add Account", true);
        dialog.setSize(250, 200);
        dialog.setLayout(new GridLayout(3, 2));
  
         // Add components to the dialog
        JLabel nameLabel = new JLabel("Account Name:");
        JTextField nameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
  
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(passwordLabel);
        dialog.add(passwordField);
  
        JButton addButton = new JButton("Add Account");
        JButton cancelButton = new JButton("Cancel");
  
        // Add action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = nameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                System.out.println(accountName);
                System.out.println(password);
                mainFrame.addAccount(accountName, password);
                dialog.dispose();
            }
        });
  
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
  
        dialog.add(addButton);
        dialog.add(cancelButton);
  
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
      
    }
}