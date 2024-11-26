Feature: Password Reset Functionality
  As a customer,
  I want to reset my password
  So that I can access my account if I forget my login information.

  Scenario: Requesting a password reset (Normal Flow)
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

  Scenario: Setting a new password (Normal Flow)
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

  Scenario: Deciding not to reset the password after receiving the reset link (Alternate Flow)
    Given that the user "<email>" with password "<old_password>" is already in the system
    And the user has received a password reset email
    And the reset link in the email is valid
    When the user clicks on the reset link in the email
    And the user is redirected to the password reset page
    And the user decides not to reset their password
    And the user clicks on a "Cancel" or "Back to Login" button
    Then the system redirects the user back to the login page
    And the user’s password remains unchanged as "<old_password>"

    Examples:
      | email             | old_password |
      | user1@example.com | OldPass#123  |
      | user2@example.com | Password!$   |

  Scenario: Attempting to use an expired reset link (Error Flow)
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

  Scenario: Requesting a password reset with an unregistered email (Error Flow)
    Given that the user is on the login page
    When the user clicks on the "Forgot Password" link
    And the user enters an unregistered email address "<unregistered_email>"
    And the user submits the password reset request
    Then the system displays an error message indicating that the email address is not associated with any account
    And the user is instructed to check the email address or create a new account if they don't have one

    Examples:
      | unregistered_email     |
      | nonuser@example.com    |
      | testuser@unknown.com   |
