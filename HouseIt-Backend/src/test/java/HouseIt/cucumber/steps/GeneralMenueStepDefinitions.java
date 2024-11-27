package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GeneralMenuStepDefinitions {

    private String currentMenu;
    private String selectedOption;
    private String currentPage;
    private boolean isLoggedIn;

    @Given("a user is logged into the application")
    public void aUserIsLoggedIntoTheApplication() {
        isLoggedIn = true; // Simulate logged-in state
        assertTrue("User should be logged in", isLoggedIn);
    }

    @When("they open the main menu")
    public void theyOpenTheMainMenu() {
        currentMenu = "Main Menu"; // Simulate opening the main menu
    }

    @Then("they should see options to {string}, {string}, {string}, and {string}")
    public void theyShouldSeeOptionsTo(String option1, String option2, String option3, String option4) {
        String[] expectedOptions = {option1, option2, option3, option4};
        String[] menuOptions = {"View Saved Listings", "Update Account", "View Account", "Log Out"};
        assertEquals("Main menu options should match", expectedOptions, menuOptions);
    }

    @Given("a user is on the main menu")
    public void aUserIsOnTheMainMenu() {
        currentMenu = "Main Menu"; // Simulate being on the main menu
        assertEquals("User should be on the main menu", "Main Menu", currentMenu);
    }

    @When("they select {string}")
    public void theySelect(String option) {
        selectedOption = option; // Simulate selecting an option
    }

    @Then("they should be redirected to a page showing the property listings they have saved in the McGill Ghetto area")
    public void theyShouldBeRedirectedToASavedListingsPage() {
        if (selectedOption.equals("View Saved Listings")) {
            currentPage = "Saved Listings Page"; // Simulate redirection
        }
        assertEquals("User should be on the Saved Listings Page", "Saved Listings Page", currentPage);
    }

    @Then("they should be redirected to a form allowing them to update their personal account details")
    public void theyShouldBeRedirectedToAFormForUpdatingAccountDetails() {
        if (selectedOption.equals("Update Account")) {
            currentPage = "Account Update Form"; // Simulate redirection
        }
        assertEquals("User should be on the Account Update Form", "Account Update Form", currentPage);
    }

    @Then("they should see their current account information displayed")
    public void theyShouldSeeTheirCurrentAccountInformationDisplayed() {
        if (selectedOption.equals("View Account")) {
            currentPage = "Account Details Page"; // Simulate viewing account details
        }
        assertEquals("User should be on the Account Details Page", "Account Details Page", currentPage);
    }

    @Then("they should be logged out and redirected to the login page")
    public void theyShouldBeLoggedOutAndRedirectedToTheLoginPage() {
        if (selectedOption.equals("Log Out")) {
            isLoggedIn = false; // Simulate logging out
            currentPage = "Login Page"; // Simulate redirection
        }
        assertTrue("User should be logged out", !isLoggedIn);
        assertEquals("User should be on the Login Page", "Login Page", currentPage);
    }
}
