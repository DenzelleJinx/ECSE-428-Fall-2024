Feature: Query User List
    As a registered administrator
    Or as a registered user
    I would like to query a list of users based on various criteria like name, email, account type, and if administrator, account status
    So that I can view a list of users corresponding to my search.

Background:
    Given the following accounts are registered:
        |Name            |Email                             |Account Type     |Account Status |Account Privacy|
        |Michael Popescu |sebastian.popescu@mail.mcgill.ca  |Administrator    |Active         |Public         |
        |John Doe        |john.doe@mail.mcgill.ca           |Student          |Active         |Private        |
        |Jane Moe        |jane.moe@mail.mcgill.ca           |Student          |Active         |Public         |
        |Boe Guy         |mai.guy@mcgill.ca                 |Landlord         |Active         |Private        |
        |Roe Mann        |roe.mann@mcgill.ca                |Student          |Suspended      |Public         |

Scenario: Query User List by Name (Normal Flow for Administrator)
    Given the user is logged in
    And the user is an administrator
    When the user searches for users by name "oe"
    Then a list of users matching the name "oe" is displayed:
        |Name            |Email                             |Account Type     |Account Status |
        |John Doe        |john.doe@mail.mcgill.ca           |Student          |Active         |
        |Jane Moe        |jane.moe@mail.mcgill.ca           |Student          |Active         |
        |Boe Guy         |mai.guy@mcgill.ca                 |Landlord         |Active         |
        |Roe Mann        |roe.mann@mcgill.ca                |Student          |Suspended      |

Scenario: Query User List by Email (Normal Flow for Administrator)
    Given the user is logged in
    And the user is an administrator
    When the user searches for users by email "mail"
    Then a list of users with matching email addresses is displayed:
        |Name            |Email                             |Account Type     |Account Status |
        |Michael Popescu |sebastian.popescu@mail.mcgill.ca  |Administrator    |Active         |
        |John Doe        |john.doe@mail.mcgill.ca           |Student          |Active         |
        |Jane Moe        |jane.moe@mail.mcgill.ca           |Student          |Active         |


Scenario: Query User List by Name (Normal Flow for Regular User)
    Given the user is logged in
    When the user searches for users by name "oe"
    Then a list of active public users matching the name "oe" is displayed:
        |Name            |Email                             |Account Type     |
        |Jane Moe        |jane.moe@mail.mcgill.ca           |Student          |

Scenario: Query User List (Error Flow: No Matching Users)
    Given the user is logged in
    When the user searches for users by name "abcdefghijklmnopqrstuvwxyz"
    Then no users are returned in the results
    And a message is displayed stating "No users found matching your search criteria"