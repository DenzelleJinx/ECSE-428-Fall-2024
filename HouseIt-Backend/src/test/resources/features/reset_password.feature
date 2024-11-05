Feature: Password Reset Functionality
  As a customer,
  I want to reset my password
  So that I can access my account if I forget my login information.

  Scenario Outline: Requesting a password reset
    Given that the user "<email>" with password "<old_password>" is already in the system
    And the user is on the login page
    When the user clicks on the "Forgot Password" link
    And the user enters their registered email address "<email>"
    And the user submits the password reset request
    Then the system sends an email with a reset link to the user's registered email address
    And the reset link expires after 24 hours

    Examples:
      | email             | old_password |
      | user1@example.com | OldPass#123  |
      | user2@example.com | Password!$   |

  Scenario Outline: Setting a new password
    Given that the user "<email>" with password "<old_password>" is already in the system
    And the user has received a password reset email
    And the reset link in the email is valid
    When the user clicks on the reset link in the email
    And the user is redirected to the password reset page
    And the user enters a new password "<new_password>" that meets the system’s security requirements
    And the user confirms the new password "<new_password>"
    And the user submits the new password
    Then the system updates the user’s password
    And the user is notified that the password has been successfully reset
    And the user in the database will have the new password "<new_password>"

    Examples:
      | email             | old_password | new_password |
      | user1@example.com | OldPass#123  | NewPass#123  |
      | user2@example.com | Password!$   | Passw0rd!$   |

  Scenario Outline: Attempting to use an expired reset link
    Given that the user "<email>" with password "<old_password>" is already in the system
    And the reset link in the email has expired
    When the user clicks on the expired reset link
    Then the system displays a message indicating that the reset link has expired
    And the user is instructed to request a new password reset
    And the user in the database will still have the old password "<old_password>"

    Examples:
      | email             | old_password |
      | user1@example.com | OldPass#123  |
      | user2@example.com | Password!$   |

