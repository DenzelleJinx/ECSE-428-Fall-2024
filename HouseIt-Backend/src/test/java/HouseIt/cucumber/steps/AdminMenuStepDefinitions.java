package HouseIt.cucumber.steps;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.model.User;
import HouseIt.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


public class AdminMenuStepDefinitions {
    @Autowired
    private UserService userService;


    private User admin;



    @Given("the user is an admin")
    public void theUserIsAnAdmin() {
        admin = userService.getUserByEmail("admin@house.it");
        if (admin == null) {
            admin = userService.createAdmin();
        }
    }

    @When("the user navigates to their dashboard")
    public void theUserNavigatesToTheirDashboard() {
        
    }

    @Then("they should see an \"Admin Menu\" option")
    public void theyShouldSeeAnOption() {

    }

    @Then("the \"Admin Menu\" should contain options for \"Manage Accounts\" and \"Manage Listings\"")
    public void theMenuShouldContainsOptions() {

    }

    @Given("the user is on the \"Admin Menu\"")
    public void theUserIsOnTheMenu() {

    }

    @Then("they should see a list of all accounts in the system, including account name and type")
    public void theyShouldSeeAListOfAllAccountsInTheSystem() {

    }

    @Then("they should have options to activate or deactivate the accounts")
    public void theyShouldHaveOptionsToActivateOrDeactivateTheAccounts() {

    }

    @Then("they should see a list of all listings in the system")
    public void theyShouldSeeAListOfAllListingsInTheSystem() {

    }

    @Then("they should have options to change listings visibility or delete listings")
    public void theyShouldHaveOptionsToChangeListingsVisibilityOrDeleteListings() {

    }

    @Given("there are multiple admin accounts in the system")
    public void thereAreMultipleAdminAccountsInTheSystem() {

    }

    @When("the user attempts to deactivate an account that does not belong to them")
    public void theUserAttemptsToDeactivateAnAccountThatDoesNotBelongToThem() {

    }

    @Then("the system should display an error message stating \"You are not authorized to deactivate other admin accounts\"")
    public void theSystemShouldDisplayAnErrorMessageStating() {

    }

    @Then("the account should remain active")
    public void theAccountShouldRemainActive() {

    }

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String string) {
        
    }

    @When("the user selects the {string} option")
    public void theUserSelectsTheOption(String string) {
        
    }
}
