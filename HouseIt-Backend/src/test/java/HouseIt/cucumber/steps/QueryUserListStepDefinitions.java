package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QueryUserListStepDefinitions {
    @Given("the following accounts are registered:")
    public void theFollowingAccountsAreRegistered(io.cucumber.datatable.DataTable dataTable) {
        // Implement the step to initialize the users from the given table
        // This will map the table to a list of users or use the data for setup
    }

    @Given("the user is an administrator")
    public void theUserIsAnAdministrator() {
        // Implement the step to verify the user has administrator privileges
    }

    @When("the user searches for users by name {string}")
    public void theUserSearchesForUsersByName(String name) {
        // Implement the step to simulate searching for users by name
    }

    @When("the user searches for users by email {string}")
    public void theUserSearchesForUsersByEmail(String email) {
        // Implement the step to simulate searching for users by email
    }

    @Then("a list of users matching the name {string} is displayed:")
    public void aListOfUsersMatchingTheNameIsDisplayed(String name, io.cucumber.datatable.DataTable dataTable) {
        // Implement the step to verify the list of users matching the name is displayed
        // Compare the displayed list with the expected result from the table
    }

    @Then("a list of users with matching email addresses is displayed:")
    public void aListOfUsersWithMatchingEmailAddressesIsDisplayed(io.cucumber.datatable.DataTable dataTable) {
        // Implement the step to verify the list of users matching the email is displayed
        // Compare the displayed list with the expected result from the table
    }

    @Then("a list of active public users matching the name {string} is displayed:")
    public void aListOfActivePublicUsersMatchingTheNameIsDisplayed(String name, io.cucumber.datatable.DataTable dataTable) {
        // Implement the step to verify the list of active public users matching the name is displayed
    }

    @Then("no users are returned in the results")
    public void noUsersAreReturnedInTheResults() {
        // Implement the step to verify that no users are returned in the results
    }

    @Then("a message is displayed stating {string}")
    public void aMessageIsDisplayedStating(String message) {
        // Implement the step to verify that the expected message is displayed
    }
}
