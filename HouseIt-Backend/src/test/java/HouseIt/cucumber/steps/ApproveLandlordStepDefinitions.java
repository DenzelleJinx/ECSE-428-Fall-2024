package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApproveLandlordStepDefinitions {

    @Given("an admin is logged into the HouseIt platform")
    public void anAdminIsLoggedIntoThePlatform() {
        // Code to simulate an admin logging into the platform
    }

    @Given("there is a pending landlord account awaiting approval")
    public void thereIsAPendingLandlordAccountAwaitingApproval() {
        // Code to simulate a pending landlord account awaiting approval
    }

    @Given("the admin is on the \"Pending Accounts\" page")
    public void theAdminIsOnThePendingAccountsPage() {
        // Code to simulate the admin navigating to the "Pending Accounts" page
    }

    @When("the admin selects a pending landlord account")
    public void theAdminSelectsAPendingLandlordAccount() {
        // Code to simulate the admin selecting a pending landlord account
    }

    @When("the admin clicks on \"Approve\"")
    public void theAdminClicksOnApprove() {
        // Code to simulate the admin clicking the "Approve" button
    }

    @When("the admin clicks on \"Reject\"")
    public void theAdminClicksOnReject() {
        // Code to simulate the admin clicking the "Reject" button
    }

    @When("the admin enters a reason for rejection")
    public void theAdminEntersAReasonForRejection() {
        // Code to simulate the admin entering a reason for rejection
    }

    @When("the admin clicks on \"Reject Account\" without providing a reason")
    public void theAdminClicksOnRejectAccountWithoutProvidingAReason() {
        // Code to simulate clicking the "Reject Account" button without entering a reason
    }

    @When("the admin clicks on \"Approve Account\"")
    public void theAdminClicksOnApproveAccount() {
        // Code to simulate the admin clicking the "Approve Account" button
    }

    @Then("the system should update the landlord account status to \"Approved\"")
    public void theSystemShouldUpdateTheLandlordAccountStatusToApproved() {
        // Code to verify that the system updates the account status to "Approved"
    }

    @Then("the landlord should receive a notification of approval")
    public void theLandlordShouldReceiveANotificationOfApproval() {
        // Code to verify that the landlord receives an approval notification
    }

    @Then("the landlord should be able to create a new posting")
    public void theLandlordShouldBeAbleToCreateANewPosting() {
        // Code to verify that the landlord can now create new postings
    }

    @Then("the system should update the landlord account status to \"Rejected\"")
    public void theSystemShouldUpdateTheLandlordAccountStatusToRejected() {
        // Code to verify that the system updates the account status to "Rejected"
    }

    @Then("the landlord should receive a notification of rejection with the reason")
    public void theLandlordShouldReceiveANotificationOfRejectionWithTheReason() {
        // Code to verify that the landlord receives a rejection notification with the reason
    }

    @Then("the system should display a prompt {string}")
    public void theSystemShouldDisplayAPrompt(String message) {
        // Code to check if the system displays the correct prompt (e.g., "Please enter a reason for rejection")
    }

    @Then("the landlord account status should remain \"Pending\" until a reason is provided")
    public void theLandlordAccountStatusShouldRemainPending() {
        // Code to verify that the account status remains "Pending" until a reason is provided
    }

    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayAnErrorMessage(String errorMessage) {
        // Code to verify if the system displays the correct error message
    }

    @Then("no changes should be made to the account status")
    public void noChangesShouldBeMadeToTheAccountStatus() {
        // Code to verify that no changes are made to the account status if it was already approved
    }

    @Then("the landlord should not be able to create postings")
    public void theLandlordShouldNotBeAbleToCreatePostingsAfterNotificationFailure() {
        // Code to verify that the landlord cannot create postings if the notification failed
    }

    @Given("the landlord account status is already {string}")
    public void the_landlord_account_status_is_already(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the admin selects the approved landlord account")
    public void the_admin_selects_the_approved_landlord_account() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("the admin approves a landlord account")
    public void the_admin_approves_a_landlord_account() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("the system encounters an error in sending the notification")
    public void the_system_encounters_an_error_in_sending_the_notification() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the landlord account status should remain {string}")
    public void the_landlord_account_status_should_remain(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the notification to the landlord fails")
    public void the_notification_to_the_landlord_fails() {
        // Write code here that turns the phrase above into concrete actions
    }

}