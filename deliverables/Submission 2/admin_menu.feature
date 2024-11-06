Feature: Admin Menu for Account Management
    As an admin,
    I want access to an admin menu,
    So that I can manage accounts and listings.

    Background:
        Given the user is logged in
        And the user is an admin

    Scenario: Accessing the Admin Menu
        When the user navigates to their dashboard
        Then they should see an "Admin Menu" option
        And the "Admin Menu" should contain options for "Manage Accounts" and "Manage Listings"

    Scenario: Manage Accounts (Normal Flow)
        Given the user is on the "Admin Menu"
        When the user selects the "Manage Accounts" option
        Then they should see a list of all accounts in the system, including account name and type
        And they should have options to activate or deactivate the accounts

    Scenario: Manage Listings (Normal Flow)
        Given the user is on the "Admin Menu"
        When the user selects the "Manage Listings" option
        Then they should see a list of all listings in the system
        And they should have options to change listings visibility or delete listings

    Scenario: Attempt to Deactivate Another Admin Account (Error Flow)
        Given the user is on the "Manage Accounts" page
        And there are multiple admin accounts in the system
        When the user attempts to deactivate an account that does not belong to them
        Then the system should display an error message stating "You are not authorized to deactivate other admin accounts"
        And the account should remain active
