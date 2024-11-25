Feature: Create a Listing
    As a registered landlord user
    I want to be able to create accomodation listings with details such as location, price and amenities
    Such that it can be seen by other users

Scenario: Create New Listing (Normal Flow)
    Given the user is logged in
    And the user is a landlord
    When the user chooses to post a listing
    And the user enters valid details for location, price, amenities, description, and availability
    Then the listing is created with the provided information
    And the listing is visible to other users in search results

Scenario: Create New Listing (Alternate Flow: Add Pictures)
    Given the user is logged in
    And the user is a landlord
    When the user chooses to post a listing
    And the user enters valid details for location, price, amenities, description, and availability
    And the user uploads pictures of the accommodation
    Then the listing is created with the provided information
    And the listing is visible to other users in the search results
    And the listing has a picture visible in the search results

Scenario: Create New Listing (Error Flow: Missing Location)
    Given the user is logged in
    And the user is a landlord
    When the user chooses to post a listing
    And the user does not provide a location
    Then the user is prompted to complete all required fields
    And the listing is not created

Scenario: Create New Listing (Error Flow: Picture Upload Failure)
    Given the user is logged in
    And the user is a landlord
    When the user uploads pictures for the listing
    But the picture upload fails due to a file size limit
    Then the user is notified of the upload failure
    And the listing can still be created without the pictures