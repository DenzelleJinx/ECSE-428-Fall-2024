Feature: Accounts Functionality
    As a unregistered user
    I would like to register an account on the webapp
    So that I can access additional features like posting a listing or sending direct messages.

Scenario: Register New Student Account (Normal Flow)
    Given the user is not logged in
    When the user chooses to register a student account
    And the user enters their email, password, first name, last name, and phone number
    Then the account is created with the provided information
    But the account is only activated once the user clicks the verification link sent by email

Scenario: Register New Landlord Account (Alternate Flow)
    Given the user is not logged in
    When the user chooses to register a landlord account
    And the user enters their email, password, first name, last name, and phone number
    And the user provides a picture of their face
    Then the account is created with the provided information
    But the account is only activated once the user clicks the verification link sent by email

Scenario: Register New Student Account (Error Flow: Invalid Email)
    Given the user is not logged in
    When the user chooses to register a student account
    And the user enters an email that does not end with @mail.mcgill.ca or @mcgill.ca
    Then the user is prompted to enter a valid email address conforming to the domain requirements
    And the account is not created until a valid email is provided

Scenario: Register New Landlord Account (Error Flow: Missing Picture)
    Given the user is not logged in
    When the user chooses to register a landlord account
    And the user enters their email, password, first name, last name, and phone number
    But the user does not provide a picture of their face
    Then the user is prompted to upload a picture to proceed