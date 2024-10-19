# I'm assuming only landlords can update listings
Feature: Updating a Listing
    As a registered landlord user
    I want to be able to update listing information
    So that it displays or uses different information

Background:
    Given the user is logged in
    And the user is a landlord
    And the user is the author of an existing listing

Scenario: Update Listing Information (Normal Flow)
    When the user chooses to edit the listing
    And the user updates the location, price, description, and amenities with valid information
    Then the changes are saved successfully
    And the updated information is displayed on the listing

Scenario: Update Listing Information (Alternate Flow: Add or Remove Pictures)
    When the user chooses to edit the listing
    And the user adds or removes pictures from the listing
    Then the changes are saved successfully
    And the modified pictures is displayed on the listing

Scenario: Update Listing Information (Error Flow: Missing Required Fields)
    When the user chooses to edit the listing
    And the user leaves the price field empty
    Then the user is prompted to complete all required fields
    And the changes are not saved

Scenario: Update Listing Pictures (Error Flow: Invalid Picture Format)
    When the user chooses to edit the listing
    And the user uploads pictures in an unsupported format (e.g., .txt instead of .jpg or .png)
    Then the pictures are not updated
    And the user is shown an error message stating "Invalid file format. Please upload images in .jpg or .png format"

Scenario: Update Listing Location (Error Flow: Attempting to Change Listing Location)
    When the user chooses to edit the listing
    And the user attempts to change the listing location
    Then the listing location will be grayed out
    And the user will be unable to edit the listing location text