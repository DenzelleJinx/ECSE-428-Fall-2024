package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.model.User;
import HouseIt.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountMenuStepDefinitions {

    @Autowired
    private UserService userService;

    private User loggedInUser;
    private User viewedUser;
    private String errorMessage;
    private boolean saveSuccessful;

    @Given("a user is logged into the platform")
    public void aUserIsLoggedIntoThePlatform() {
        loggedInUser = userService.getLoggedInUser();
    }

    @Given("the user is on their account management page")
    public void theUserIsOnTheirAccountManagementPage() {
        // Assume the user navigated to their account management page
    }

    @When("the user updates their personal information")
    public void theUserUpdatesTheirPersonalInformation() {
        loggedInUser.setName("Updated Name");
        loggedInUser.setEmail("updatedemail@example.com");
        saveSuccessful = userService.saveUser(loggedInUser);
    }

    @When("clicks on {string}")
    public void clicksOnSaveChanges(String button) {
        // Simulate clicking the button
        if ("Save Changes".equals(button) && !saveSuccessful) {
            errorMessage = "Failed to save changes. Please try again.";
        }
    }

    @Then("the system should save the updated information")
    public void theSystemShouldSaveUpdatedInformation() {
        assertTrue(saveSuccessful);
    }

    @Then("the updated user information should be visible on the user’s profile in a view-only mode to other users")
    public void theUpdatedUserInformationShouldBeVisibleInViewOnlyMode() {
        User savedUser = userService.findUserById(loggedInUser.getId());
        assertEquals("Updated Name", savedUser.getName());
        assertEquals("updatedemail@example.com", savedUser.getEmail());
    }

    @When("the user opens the account menu")
    public void theUserOpensTheAccountMenu() {
        // Assume user opens the menu
    }

    @Then("the menu should display options to update user info, manage settings, and view account details")
    public void theMenuShouldDisplayAccountOptions() {
        String[] menuOptions = userService.getAccountMenuOptions();
        assertTrue(menuOptions.length > 0);
        assertTrue(menuOptions[0].contains("update user info"));
        assertTrue(menuOptions[1].contains("manage settings"));
        assertTrue(menuOptions[2].contains("view account details"));
    }

    @Given("another user is logged into the platform")
    public void anotherUserIsLoggedIntoThePlatform() {
        viewedUser = userService.findAnotherUser();
    }

    @When("they navigate to the user’s profile")
    public void theyNavigateToUserProfile() {
        // Assume navigation to the profile
    }

    @Then("they should see the user’s listings, ratings, and profile details")
    public void theyShouldSeeUsersListingsRatingsAndDetails() {
        assertTrue(viewedUser.getListings().size() > 0);
        assertTrue(viewedUser.getRatings().size() > 0);
    }

    @Then("they should not be able to edit or manage the account information")
    public void theyShouldNotBeAbleToEditAccountInfo() {
        assertFalse(userService.canEditProfile(viewedUser.getId()));
    }

    @Given("another user is viewing the user’s profile")
    public void anotherUserIsViewingTheUserProfile() {
        // Assume another user is on the profile page
    }

    @When("they attempt to modify any account information")
    public void theyAttemptToModifyAccountInfo() {
        try {
            userService.modifyAccount(viewedUser.getId(), "Attempted Change");
        } catch (Exception e) {
            errorMessage = "You do not have permission to edit this account";
        }
    }

    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayErrorMessage(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }

    @Then("no changes should be made to the user’s account information")
    public void noChangesShouldBeMadeToAccountInfo() {
        User unchangedUser = userService.findUserById(viewedUser.getId());
        assertEquals(viewedUser, unchangedUser); // Ensure user data remains unchanged
    }

    @When("the system encounters an error while saving changes")
    public void theSystemEncountersErrorWhileSavingChanges() {
        saveSuccessful = false; // Simulate a save failure
    }

    @Then("the user's information should remain unchanged until successfully saved")
    public void userInfoShouldRemainUnchangedUntilSaved() {
        User currentUser = userService.findUserById(loggedInUser.getId());
        assertEquals("Original Name", currentUser.getName()); // Replace with actual original data
    }
}
