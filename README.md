# Account Manager

This account manager will store users' account(s) information and passwords. It is open to everyone and anyone that wants to use it. Some people have issues with remembering their passwords and usernames for different platforms. This account manager would help people if they forget what their usernames and passwords.

## User stories:
- As a user, I want to be able to add an account with a password and unique ID to my account manager.
- As a user, I want to be able to remove an account in my account manager.
- As a user, I want to be able to view the accounts in my account manager.
- As a user, I want to be able to find out how secure my password is.
- As a user, I want to be able to save my password manager to file (if I so choose)
- As a user, I want to be able to be able to load my password manager from file (if I so choose)

# Instructions for Grader

- You can add multiple accounts to a password manager by clicking "Add account"
- You can delete multiple account from the password manager by clicking "Removing account"
- You can locate my visual component by clicking "Save" and find the image on the popup screen
- You can save the state of my application by clicking "Save"
- You can reload the state of my application by clicking "Load"

# Phase 4: Task 2
- Logs every account that has been added and prints out "Added new Account with ID " + userid
- Logs every account that has been removed and prints out "Removed an account from the the PasswordManager with ID: " + userid

# Phase 4: Task 3
I would have turned security status into a separate class (most likely enum) so it's more easy to sort from the password manager if I want to create more functions in the future. Since the security of an account relies heavily of a password, I think it should also be equally accessible for both the Account and Password class to retrieve its security status. In a similar vein, user id is currently set in password manager but in reality, a user's id should be attached to account and be created at the same time as the account. Printer should also be in a different class that separate from the GUI.