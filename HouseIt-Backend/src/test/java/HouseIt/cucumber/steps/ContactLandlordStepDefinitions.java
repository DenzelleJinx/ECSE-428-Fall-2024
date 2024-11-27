package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactLandlordStepDefinitions {
    
    @Given("the user is viewing a listing")
    public void theUserIsViewingAListing() {
        // Code to simulate the user viewing a listing
    }

    @When("the user clicks the \"contact\" button on the post")
    public void theUserClicksTheContactButtonOnThePost() {
        // Code to simulate the user clicking the "contact" button on the listing
    }

    @When("the user clicks the \"request more information\" button on the post")
    public void theUserClicksTheRequestMoreInformationButtonOnThePost() {
        // Code to simulate the user clicking the "request more information" button on the listing
    }

    @When("the user specifies what additional information they need")
    public void theUserSpecifiesWhatAdditionalInformationTheyNeed() {
        // Code to simulate the user specifying additional information they need
    }

    @Then("a notification is sent to the landlord")
    public void aNotificationIsSentToTheLandlord() {
        // Code to verify that the landlord is notified (e.g., email or platform notification)
    }

    @Then("the user is added to the landlord's contacts")
    public void theUserIsAddedToTheLandlordsContacts() {
        // Code to verify that the user is added to the landlord's contact list
    }

    @Then("the request is added to the landlord requests page")
    public void theRequestIsAddedToTheLandlordRequestsPage() {
        // Code to verify that the request is added to the landlord's requests page
    }

    @Then("the user is shown an error message explaining the failure")
    public void theUserIsShownAnErrorMessageExplainingTheFailure() {
        // Code to verify that the user sees an error message explaining the failure (e.g., notification failure or message not sent)
    }

    @When("the message cannot be sent to the landlord")
    public void the_message_cannot_be_sent_to_the_landlord() {
        // Write code here that turns the phrase above into concrete actions
    }
}
