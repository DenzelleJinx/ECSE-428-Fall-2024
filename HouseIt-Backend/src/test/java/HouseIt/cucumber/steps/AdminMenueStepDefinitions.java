package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.dao.AccountDAO;
import HouseIt.dao.ListingDAO;
import HouseIt.model.Account;
import HouseIt.model.Listing;
import HouseIt.service.AccountService;
import HouseIt.service.AdminService;
import HouseIt.service.ListingService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminMenuStepDefinitions {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ListingService listingService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ListingDAO listingDAO;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        // This setup is assumed to be handled by authentication middleware
    }

    @Given("the user is an admin")
    public void theUserIsAnAdmin() {
        // Ensure the user has admin privileges
        Account admin = accountService.getCurrentUser();
        assertTrue(admin.isAdmin());
    }

    @When("the user navigates to their dashboard")
    public void theUserNavigatesToTheirDashboard() {
        // Simulate navigation to the admin dashboard
    }

    @Then("they should see an {string} option")
    public void theyShouldSeeAnOption(String option) {
        List<String> menuOptions = adminService.getAdminMenuOptions();
        assertTrue(menuOptions.contains(option));
    }

    @Then("the {string} should contain options for {string} and {string}")
    public void theMenuShouldContainOptionsForAnd(String menu, String option1, String option2) {
        List<String> options = adminService.getMenuOptions(menu);
        assertTrue(options.contains(option1));
        assertTrue(options.contains(option2));
    }

    @Given("the user is on the {string}")
    public void theUserIsOnThe(String menu) {
        // Ensure the user has navigated to the specified menu
        String currentMenu = adminService.getCurrentMenu();
        assertEquals(menu, currentMenu);
    }

    @When("the user selects the {string} option")
    public void theUserSelectsTheOption(String option) {
        adminService.selectOption(option);
    }

    @Then("they should see a list of all accounts in the system, including account name and type")
    public void theyShouldSeeAListOfAllAccountsInTheSystemIncludingAccountNameAndType() {
        List<Account> accounts = accountService.getAllAccounts();
        assertTrue(accounts.size() > 0);
        accounts.forEach(account -> {
            assertTrue(account.getName() != null && account.getType() != null);
        });
    }

    @Then("they should have options to activate or deactivate the accounts")
    public void theyShouldHaveOptionsToActivateOrDeactivateTheAccounts() {
        List<String> accountOptions = adminService.getAccountManagementOptions();
        assertTrue(accountOptions.contains("Activate"));
        assertTrue(accountOptions.contains("Deactivate"));
    }

    @Then("they should see a list of all listings in the system")
    public void theyShouldSeeAListOfAllListingsInTheSystem() {
        List<Listing> listings = listingService.getAllListings();
        assertTrue(listings.size() > 0);
    }

    @Then("they should have options to change listings visibility or delete listings")
    public void theyShouldHaveOptionsToChangeListingsVisibilityOrDeleteListings() {
        List<String> listingOptions = adminService.getListingManagementOptions();
        assertTrue(listingOptions.contains("Change Visibility"));
        assertTrue(listingOptions.contains("Delete"));
    }

    @Given("there are multiple admin accounts in the system")
    public void thereAreMultipleAdminAccountsInTheSystem() {
        List<Account> admins = accountService.getAdminAccounts();
        assertTrue(admins.size() > 1);
    }

    @When("the user attempts to deactivate an account that does not belong to them")
    public void theUserAttemptsToDeactivateAnAccountThatDoesNotBelongToThem() {
        Account anotherAdmin = accountService.getAnotherAdminAccount();
        boolean result = adminService.deactivateAccount(anotherAdmin.getId());
        assertTrue(!result); // The operation should fail
    }

    @Then("the system should display an error message stating {string}")
    public void theSystemShouldDisplayAnErrorMessageStating(String errorMessage) {
        String actualMessage = adminService.getErrorMessage();
        assertEquals(errorMessage, actualMessage);
    }

    @Then("the account should remain active")
    public void theAccountShouldRemainActive() {
        Account anotherAdmin = accountService.getAnotherAdminAccount();
        assertTrue(anotherAdmin.isActive());
    }
}
