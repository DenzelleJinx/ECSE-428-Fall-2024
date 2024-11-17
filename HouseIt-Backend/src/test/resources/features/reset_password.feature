Feature: Password Reset Functionality for Existing Users

  As a registered user
  I want to reset my password by providing my email, username, and new password
  So that I can regain access to my account if I forget my credentials

  Background:
    Given the following user exists in the system:
      | username     | password          | email                        | accountType |
      | teststudent  | testpassword123   | test.student@mail.mcgill.ca  | student     |

  Scenario: Successfully reset password with valid email and username
    When the user submits the following reset password request:
      | email                        | username    | newPassword        | accountType |
      | test.student@mail.mcgill.ca  | teststudent | newTestPassword456 | student     |
    Then the system should respond with "Password has been reset successfully."
    And the user with info exists in the system:
      | username     | password          | email                        | accountType |
      | teststudent  | newTestPassword456 | test.student@mail.mcgill.ca | student     |

  Scenario: Reset password with incorrect email
    When the user submits the following reset password request:
      | email                        | username    | newPassword        | accountType |
      | wrong.email@mail.mcgill.ca   | teststudent | newTestPassword456 | student     |
    Then the system should respond with "No student found with the provided email and username."

  Scenario: Reset password with incorrect username
    When the user submits the following reset password request:
      | email                        | username    | newPassword        | accountType |
      | test.student@mail.mcgill.ca  | wronguser   | newTestPassword456 | student     |
    Then the system should respond with "No student found with the provided email and username."

  Scenario: Reset password with invalid account type
    When the user submits the following reset password request:
      | email                        | username    | newPassword        | accountType |
      | test.student@mail.mcgill.ca  | teststudent | newTestPassword456 | invalid     |
    Then the system should respond with "Invalid account type provided."

  Scenario: Reset password with missing fields
    When the user submits the following reset password request:
      | email                        | username | newPassword | accountType |
      | test.student@mail.mcgill.ca  |          |             | student     |
    Then the system should respond with "No student found with the provided email and username."
