Feature: View listing

  As a User,
  I want to be able to view available listings,
  Such that they can be selected and opened on a separate page containing all of their relavant information

  Scenario: View all available listings (Normal Flow)
    Given the user is logged in
    And there are available listings
    When the user enters the listings page
    Then the list of available listings should be displayed

  Scenario: View empty list of available listings (Alternate Flow)
    Given the user is logged in
    And there are no available listings
    When the user enters the listings page
    Then a message should be displayed indicating that no listings are available

  Scenario: View a single listing (Normal Flow)
    Given the user is logged in
    And a listing is available
    And the user is in the listing page
    When the user selects an available listing
    Then a new page should open displaying detailed information about the selected listing

  Scenario: View all available listing while logged out (Error Flow)
    Given the user is not logged in
    And there are available listings
    When the user enters the listings page
    Then the list of available listings should not be displayed
    And the user is redirected to the login page