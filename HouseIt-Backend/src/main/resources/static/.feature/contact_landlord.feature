Feature: Contact Landlord Through Listing
    As a user
    I want to be able to contact a landlord by clicking a button on their post
    So that I can send inquiries and get more information about the listing

Scenario: Contact Landlord (Normal Flow)
    Given the user is viewing a listing
    When the user clicks the "contact" button on the post
    Then a notification is sent to the landlord
    And the user is added to the landlord's contacts

Scenario: Request More Information (Normal Flow)
    Given the user is viewing a listing
    When the user clicks the "request more information" button on the post
    And the user specifies what additional information they need
    Then the request is added to the landlord requests page

Scenario: Contact Landlord (Error Flow: Landlord Not Notified)
    Given the user is viewing a listing
    When the user clicks the "contact" button on the post
    But the notification to the landlord fails
    Then the user is shown an error message explaining the failure

Scenario: Request More Information (Error Flow: Message Not Sent)
    Given the user is viewing a listing
    When the user clicks the "request more information" button on the post
    But the message cannot be sent to the landlord
    Then the user is shown an error message explaining the failure