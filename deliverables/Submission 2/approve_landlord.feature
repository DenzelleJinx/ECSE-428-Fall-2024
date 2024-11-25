Feature: Landlord Account Verification
    As an admin,
    I want to be able to approve a landlord account,
    so that the landlord can create postings on the HouseIt platform.

    Background:
        Given an admin is logged into the HouseIt platform
        And there is a pending landlord account awaiting approval

    Scenario: Approving a pending landlord account (Normal Flow)
        Given the admin is on the "Pending Accounts" page
        When the admin selects a pending landlord account
        And the admin clicks on "Approve"
        Then the system should update the landlord account status to "Approved"
        And the landlord should receive a notification of approval
        And the landlord should be able to create a new posting

    Scenario: Rejecting a pending landlord account (Alternate Flow)
        Given the admin is on the "Pending Accounts" page
        When the admin selects a pending landlord account
        And the admin clicks on "Reject"
        And the admin enters a reason for rejection
        Then the system should update the landlord account status to "Rejected"
        And the landlord should receive a notification of rejection with the reason
        And the landlord should not be able to create postings

    Scenario: Rejecting a pending landlord account without a reason (Error Flow)
        Given the admin is on the "Pending Accounts" page
        When the admin selects a pending landlord account
        And the admin clicks on "Reject Account" without providing a reason
        Then the system should display a prompt "Please enter a reason for rejection"
        And the landlord account status should remain "Pending" until a reason is provided

    Scenario: Attempting to approve an already approved account (Error Flow)
        Given the admin is on the "Pending Accounts" page
        And the landlord account status is already "Approved"
        When the admin selects the approved landlord account
        And the admin clicks on "Approve Account"
        Then the system should display an error message "This account has already been approved"
        And no changes should be made to the account status
    
    Scenario: Approval notification failure (Error Flow)
        Given the admin approves a landlord account
        And the system encounters an error in sending the notification
        Then the system should display an error message "Failed to send notification to the landlord"
        And the landlord account status should remain "Pending"
        And the landlord should not be able to create postings

