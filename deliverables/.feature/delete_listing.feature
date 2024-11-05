Feature: Deleting a Listing
    As a user, I want to be able to delete a posting that I posted, such that it can not be seen by me or by other users.

Background:
    Given the user is logged in
    And the user is a landlord
    And the user is the author of an existing listing
    And a listing exists

Scenario: Delete Listing (Normal Flow)
    When the user chooses to delete the listing
    Then the listing is deleted successfully
    And the deleted listing is no longer displayed

Scenario: Delete Listing (Alternate Flow: Delete Address)
    When the user chooses to delete the address
    And the address is associated to a listing
    Then the address and listing are deleted successfully
    And the deleted listing is no longer displayed

Scenario: Delete Listing (Error Flow: Missing Required Fields)
    When the user chooses to edit the listing
    And the listing is hidden for admin review
    Then the listing is not deleted

