Feature: Deleting a Listing
  As a user, I want to be able to delete a posting that I posted, so that it can no longer be seen by me or by other users.

  Background:
    Given the user is logged in
    And the user is a landlord
    And the user is the author of an existing listing
    And a listing exists with details such as address, price, and amenities

  Scenario: Delete Listing (Normal Flow)
    When the user navigates to their list of active postings
    And the user selects the option to delete the listing
    Then the user is prompted to confirm the deletion
    When the user confirms deletion
    Then the listing is deleted successfully
    And the deleted listing is no longer displayed to the user or other users in search results
    
  Scenario: Delete Listing (Error Flow: Missing Required Fields for Admin Review)
    When the user chooses to edit the listing
    And the listing is missing required fields
    And the listing is flagged for admin review due to incomplete information
    Then the listing cannot be deleted until the review is complete
    And the user is notified that the listing is currently under admin review and cannot be modified or deleted

  Scenario: Delete Listing (Error Flow: Network Error)
    When the user attempts to delete the listing
    And there is a network error preventing the deletion
    Then the listing is not deleted
    And the user is notified of the network issue
    And the listing remains visible to the user and other users

  Scenario: Delete Listing (Error Flow: Unauthorized User Attempt)
    Given another user is logged in
    And the user is not the author of the listing
    When the user attempts to delete the listing
    Then the user is denied permission to delete the listing
    And the listing remains visible in search results to other users

  Scenario: Delete Listing (Edge Case: Delete Listing with Active Reservations)
    Given the listing has active reservations from other users
    When the user chooses to delete the listing
    Then the user is warned that deleting the listing will cancel all active reservations
    When the user confirms deletion
    Then the listing is deleted along with any active reservations
    And the listing is no longer visible in search results to other users
    And the users with active reservations are notified of the cancellation
