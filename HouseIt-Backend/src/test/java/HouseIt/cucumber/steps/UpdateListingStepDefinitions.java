package HouseIt.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateListingStepDefinitions {
    @When("the user updates the location, price, description, and amenities with valid information")
    public void theUserUpdatesWithValidInformation() {
        // Code to simulate the user updating the listing with valid details
    }

    @When("the user adds or removes pictures from the listing")
    public void theUserAddsOrRemovesPictures() {
        // Code to simulate the user adding or removing pictures from the listing
    }

    @Then("the modified pictures are displayed on the listing")
    public void theModifiedPicturesAreDisplayed() {
        // Code to verify that the updated pictures are displayed
    }

    @When("the user leaves the price field empty")
    public void theUserLeavesThePriceFieldEmpty() {
        // Code to simulate the user leaving the price field empty
    }

    @Then("the user is prompted to complete all required fields")
    public void theUserIsPromptedToCompleteAllRequiredFields() {
        // Code to verify that the user sees a prompt to fill required fields
    }

    @When("the user uploads pictures in an unsupported format")
    public void theUserUploadsPicturesInAnUnsupportedFormat() {
        // Code to simulate the user uploading invalid picture formats
    }

    @Then("the pictures are not updated")
    public void thePicturesAreNotUpdated() {
        // Code to verify that the invalid pictures are not added to the listing
    }

    @Then("the user is shown an error message stating {string}")
    public void theUserIsShownAnErrorMessage(String errorMessage) {
        // Code to verify the user sees the specified error message
    }

    @When("the user attempts to change the listing location")
    public void theUserAttemptsToChangeTheListingLocation() {
        // Code to simulate the user trying to change the listing location
    }

    @Then("the listing location will be grayed out")
    public void theListingLocationWillBeGrayedOut() {
        // Code to verify the location field is non-editable and grayed out
    }

    @Then("the user will be unable to edit the listing location text")
    public void theUserWillBeUnableToEditTheListingLocationText() {
        // Code to confirm the location text cannot be changed
    }

    @When("the user uploads pictures in an unsupported format \\(e.g., .txt instead of .jpg or .png)")
    public void the_user_uploads_pictures_in_an_unsupported_format_e_g_txt_instead_of_jpg_or_png() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the updated information is displayed on the listing")
    public void the_updated_information_is_displayed_on_the_listing() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the modified pictures is displayed on the listing")
    public void the_modified_pictures_is_displayed_on_the_listing() {
        // Write code here that turns the phrase above into concrete actions
    }
}
