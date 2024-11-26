Feature: User Account Menu
    As a user,
    I want to have a menu to manage my account on the same page where I can update my user info,
    so that my account can be managed easily and is visible to other users in a view-only mode.

    Background:
        Given a user is logged into the platform
        And the user is on their account management page

    Scenario: Updating user information (Normal Flow)
        Given the user is on the account management page
        When the user updates their personal information
        And clicks on "Save Changes"
        Then the system should save the updated information
        And the updated user information should be visible on the user’s profile in a view-only mode to other users

    Scenario: Accessing account management menu (Normal Flow)
        Given the user is on the account management page
        When the user opens the account menu
        Then the menu should display options to update user info, manage settings, and view account details

    Scenario: Viewing user account as another user (View-Only Mode)
        Given another user is logged into the platform
        When they navigate to the user’s profile
        Then they should see the user’s listings, ratings, and profile details
        And they should not be able to edit or manage the account information

    Scenario: Attempting to edit account from a view-only profile (Error Flow)
        Given another user is viewing the user’s profile
        When they attempt to modify any account information
        Then the system should display an error message "You do not have permission to edit this account"
        And no changes should be made to the user’s account information

    Scenario: Error while saving updated user information (Error Flow)
        Given the user is on the account management page
        When the user updates their information
        And the system encounters an error while saving changes
        Then the system should display an error message "Failed to save changes. Please try again."
        And the user's information should remain unchanged until successfully saved
