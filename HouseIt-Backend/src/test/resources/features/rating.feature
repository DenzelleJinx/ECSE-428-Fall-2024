Feature: Rating a Customer
    As a customer
    I want to rate another customer after a listing is marked as closed
    So that their rating can be seen by other customers

Background:
    Given the user is logged in
    And the user is a customer
    And a listing transaction has been marked as closed by the landlord

Scenario: Rate a Customer (Normal Flow)
    When the user receives an email prompting them to rate the other customer involved in the transaction
    And the user follows the link given to them in the email
    And the user selects a rating from 1 to 5 stars
    Then the rating is saved successfully
    And the rating is displayed on the other customer’s profile for others to view

Scenario: Rate a Customer (Error Flow: No Rating Selected)
    When the user receives an email prompting them to rate the other customer involved in the transaction
    And the user follows the link given to them in the email
    And the user leaves the rating selection blank
    Then the user is prompted to select a rating
    And the rating is not saved

Scenario: View Previous Ratings of a Customer
    Given another customer views the profile of a previously rated customer
    When they navigate to the customer’s ratings section
    Then they see the customer’s average rating