package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountMenuStepDefinitions {

    @Given("a user is logged into the platform")
    public void aUserIsLoggedIntoThePlatform() {
        // Code to simulate a user logging into the platform
    }

    @Given("the user is on their account management page")
    public void theUserIsOnTheirAccountManagementPage() {
        // Code to simulate the user being on the account management page
    }

    @When("the user updates their personal information")
    public void theUserUpdatesTheirPersonalInformation() {
        // Code to simulate updating the user's personal information
    }

    @When("the user clicks on \"Save Changes\"")
    public void theUserClicksOnSaveChanges() {
        // Code to simulate the user clicking on the "Save Changes" button
    }

    @Then("the system should save the updated information")
    public void theSystemShouldSaveTheUpdatedInformation() {
        // Code to check if the system saves the updated information
    }

    @Then("the updated user information should be visible on the user’s profile in a view-only mode to other users")
    public void theUpdatedUserInformationShouldBeVisibleToOtherUsers() {
        // Code to verify that updated information is visible to other users in view-only mode
    }

    @When("the user opens the account menu")
    public void theUserOpensTheAccountMenu() {
        // Code to simulate opening the account menu
    }

    @Then("the menu should display options to update user info, manage settings, and view account details")
    public void theMenuShouldDisplayAccountOptions() {
        // Code to verify that the account menu displays the correct options
    }

    @Given("another user is logged into the platform")
    public void anotherUserIsLoggedIntoThePlatform() {
        // Code to simulate another user logging into the platform
    }

    @When("they navigate to the user’s profile")
    public void theyNavigateToTheUserProfile() {
        // Code to simulate navigating to the user’s profile
    }

    @Then("they should see the user’s listings, ratings, and profile details")
    public void theyShouldSeeUserListingsAndDetails() {
        // Code to verify that another user can view the user's listings, ratings, and profile details
    }

    @Then("they should not be able to edit or manage the account information")
    public void theyShouldNotBeAbleToEditAccountInformation() {
        // Code to verify that the other user cannot edit account information
    }

    @When("they attempt to modify any account information")
    public void theyAttemptToModifyAccountInformation() {
        // Code to simulate attempting to modify account information from a view-only profile
    }

    @Then("no changes should be made to the user’s account information")
    public void noChangesShouldBeMadeToAccountInformation() {
        // Code to verify that no changes were made to the user's account information
    }

    @When("the system encounters an error while saving changes")
    public void theSystemEncountersErrorWhileSavingChanges() {
        // Code to simulate an error while saving changes
    }

    @Then("the user's information should remain unchanged until successfully saved")
    public void theUsersInformationShouldRemainUnchanged() {
        // Code to ensure the user's information remains unchanged if the save fails
    }

    @Given("the user is on the account management page")
    public void the_user_is_on_the_account_management_page() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("clicks on {string}")
    public void clicks_on(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("another user is viewing the user’s profile")
    public void another_user_is_viewing_the_user_s_profile() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the user updates their information")
    public void the_user_updates_their_information() {
        // Write code here that turns the phrase above into concrete actions
    }


}
