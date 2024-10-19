Feature: Updating Account Info
    As a registered user
    I want to edit my personal or account information
    So that it displays or uses different details.

Scenario: Update Personal Information (Normal Flow)
    Given the user is logged in
    When the user navigates to the account settings
    And the user updates their first name, last name, and phone number with valid inputs
    Then the changes are saved successfully
    And the user sees a confirmation message stating "Your personal information has been updated"
    And the updated information is displayed in the account settings

Scenario: Update Email Address (Normal Flow)
    Given the user is logged in
    When the user navigates to the account settings
    And the user updates their email address with a valid new email
    Then a confirmation email is sent to the new address
    And the user is informed that the email will be updated once the new email is verified
    And the old email remains active until verification is complete

Scenario: Update Account Password (Normal Flow)
    Given the user is logged in
    When the user navigates to the account settings
    And the user updates their password by entering the current password and a valid new password
    Then the account password is updated successfully
    And the user sees a confirmation message stating "Your password has been changed"

Scenario: Update Account Info (Error Flow: Invalid Email Domain)
    Given the user is logged in
    When the user navigates to the account settings
    And the user updates their email with one that does not end in @mail.mcgill.ca or @mcgill.ca
    Then the changes are not saved
    And the user is prompted to enter a valid email address
    
Scenario: Update Password (Error Flow: Incorrect Current Password)
    Given the user is logged in
    When the user navigates to the account settings
    And the user attempts to update their password but enters an incorrect current password
    Then the password is not updated
    And the user is shown an error message stating "Incorrect current password"

# Not sure if emails should be allowed to be changed.
Scenario: Update Email Address (Error Flow: Email Already in Use)
    Given the user is logged in
    When the user navigates to the account settings
    And the user tries to update their email address to one that is already registered
    Then the email is not updated
    And the user is shown an error message stating "This email address is already in use"