Feature: General Menu

  Scenario: Access general menu options
    Given a user is logged into the application
    When they open the main menu
    Then they should see options to "View Saved Listings," "Update Account," "View Account," and "Log Out"

  Scenario: View saved listings
    Given a user is on the main menu
    When they select "View Saved Listings"
    Then they should be redirected to a page showing the property listings they have saved in the McGill Ghetto area

  Scenario: Update account information
    Given a user is on the main menu
    When they select "Update Account"
    Then they should be redirected to a form allowing them to update their personal account details

  Scenario: View account details
    Given a user is on the main menu
    When they select "View Account"
    Then they should see their current account information displayed

  Scenario: Log out of the application
    Given a user is on the main menu
    When they select "Log Out"
    Then they should be logged out and redirected to the login page
