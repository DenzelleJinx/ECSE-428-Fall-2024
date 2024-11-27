package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GeneralMenuStepDefinitions {

    @Given("a user is logged into the application")
    public void aUserIsLoggedIntoTheApplication() {
        // Code to verify that the user is logged into the application
    }

    @When("they open the main menu")
    public void theyOpenTheMainMenu() {
        // Code to simulate opening the main menu
    }

    @Then("they should see options to {string}, {string}, {string}, and {string}")
    public void theyShouldSeeOptions(String option1, String option2, String option3, String option4) {
        // Code to verify that the specified options are displayed in the main menu
    }

    @Given("a user is on the main menu")
    public void aUserIsOnTheMainMenu() {
        // Code to verify the user is currently on the main menu
    }

    @When("they select {string}")
    public void theySelect(String option) {
        // Code to handle selecting the specified option from the main menu
    }

    @Then("they should be redirected to a page showing the property listings they have saved in the McGill Ghetto area")
    public void theyShouldSeeSavedListingsPage() {
        // Code to verify redirection to the saved listings page and display listings
    }

    @Then("they should be redirected to a form allowing them to update their personal account details")
    public void theyShouldSeeUpdateAccountForm() {
        // Code to verify redirection to the update account form
    }

    @Then("they should see their current account information displayed")
    public void theyShouldSeeAccountDetailsDisplayed() {
        // Code to verify account information is displayed
    }

    @Then("they should be logged out and redirected to the login page")
    public void theyShouldBeLoggedOutAndRedirectedToLoginPage() {
        // Code to handle logout functionality and verify redirection to the login page
    }

    @Then("they should see options to {string} {string} {string} and {string}")
    public void they_should_see_options_to_and(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
    }
}
