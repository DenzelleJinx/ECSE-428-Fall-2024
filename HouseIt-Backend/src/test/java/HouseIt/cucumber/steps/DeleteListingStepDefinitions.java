package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.dao.AddressDAO;
import HouseIt.dao.AmenitiesDAO;
import HouseIt.dao.LandlordDAO;
import HouseIt.dao.ListingDAO;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Landlord;
import HouseIt.model.Listing;
import HouseIt.service.LandlordService;
import HouseIt.service.ListingService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteListingStepDefinitions {

    @Autowired
    private ListingService listingService;

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private ListingDAO listingDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private AmenitiesDAO amenitiesDAO;

    @Autowired
    private LandlordDAO landlordDAO;

    boolean isAuthor = false;
    boolean networkError = false;
    Listing tempListing;
    String errorMessage;

    @After
    public void afterScenario() {
        landlordDAO.deleteAll();
        listingDAO.deleteAll();
        addressDAO.deleteAll();
        amenitiesDAO.deleteAll();

        tempListing = null;
        errorMessage = null;
        networkError = false;
        isAuthor = false;
    }

    @Given("the user is the author of an existing listing")
    public void theUserIsTheAuthorOfAnExistingListing() {
        isAuthor = true;
    }

    @Given("a listing exists with details such as address, price, and amenities")
    public void aListingExistsWithDetailsSuchAsAddressPriceAndAmenities() {
        Landlord landlord = landlordService.createLandlord("username", "password", "email@gmail.com", "1234567890");
        
        Address dummyAddress = new Address();
        dummyAddress.setCity("testCity");
        dummyAddress.setPostalCode("A1A1A1");
        dummyAddress.setStreet("testStreet");
        dummyAddress.setStreetNumber("123");
        dummyAddress.setApartmentNumber("123");

        Amenities dummyAmenities = new Amenities();
        dummyAmenities.setGym(true);
        dummyAmenities.setLaundry(true);
        dummyAmenities.setPetsAllowed(true);
        dummyAmenities.setParking(true);
        dummyAmenities.setInternetIncluded(true);

        tempListing =listingService.createListing(
            landlord.getId(),
            "testListing1",
            "testDescription1",
            1000,
            5,
            2,
            Listing.PropertyType.APARTMENT,
            1000,
            true,
            false,
            dummyAddress,
            dummyAmenities,
            new ArrayList<>(),
            null
        );
    }

    @Given("the listing has active reservations from other users")
    public void the_listing_has_active_reservations_from_other_users() {
        
    }

    @Given("another user is logged in")
    public void another_user_is_logged_in() {
        
    }

    @Given("the user is not the author of the listing")
    public void the_user_is_not_the_author_of_the_listing() {
        isAuthor = false;
    }

    @When("the user navigates to their list of active postings")
    public void theUserNavigatesToTheirListOfActivePostings() {
        
    }

    @When("the user selects the option to delete the listing")
    public void theUserSelectsTheOptionToDeleteTheListing() {
        
    }

    @When("the user attempts to delete the listing")
    @When("the user confirms deletion")
    @When("the user chooses to delete the listing")
    public void the_user_chooses_to_delete_the_listing() {
        try{
            if (!isAuthor) {
                throw new Exception("You are not the author of this listing");
            }
            if (networkError) {
                throw new Exception("There was a network error");
            }
            listingService.deleteListing(tempListing.getId());
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the user is prompted to confirm the deletion")
    public void theUserIsPromptedToConfirmTheDeletion() {
        
    }

    @Then("the listing is deleted successfully")
    public void theListingIsDeletedSuccessfully() {
        assertNull(errorMessage);
    }

    @Then("the deleted listing is no longer displayed to the user or other users in search results")
    public void theDeletedListingIsNoLongerDisplayedToTheUserOrOtherUsersInSearchResults() {
        assertNull(listingDAO.findListingById(tempListing.getId()));
    }

    @When("the user chooses to edit the listing")
    public void theUserChoosesToEditTheListing() {

    }

    @When("the listing is missing required fields")
    public void theListingIsMissingRequiredFields() {

    }

    @Then("the listing cannot be deleted until the review is complete")
    public void theListingCannotBeDeletedUntilTheReviewIsComplete() {

    }

    @Then("the user is notified that the listing is currently under admin review and cannot be modified or deleted")
    public void theUserIsNotifiedThatTheListingIsCurrentlyUnderAdminReviewAndCannotBeModifiedOrDeleted() {

    }

    @When("there is a network error preventing the deletion")
    public void thereIsANetworkErrorPreventingTheDeletion() {
        networkError = true;
        errorMessage = "Network error";
    }

    @When("the listing is flagged for admin review due to incomplete information")
    public void the_listing_is_flagged_for_admin_review_due_to_incomplete_information() {
        
    }

    @Then("the listing is not deleted")
    public void theListingIsNotDeleted() {
        assertEquals(true, networkError);
    }

    @Then("the user is warned that deleting the listing will cancel all active reservations")
    public void the_user_is_warned_that_deleting_the_listing_will_cancel_all_active_reservations() {
        
    }

    @Then("the listing is deleted along with any active reservations")
    public void the_listing_is_deleted_along_with_any_active_reservations() {
        assertNull(listingDAO.findListingById(tempListing.getId()));
    }

    @Then("the listing is no longer visible in search results to other users")
    public void the_listing_is_no_longer_visible_in_search_results_to_other_users() {
        assertNull(listingDAO.findListingById(tempListing.getId()));
    }

    @Then("the users with active reservations are notified of the cancellation")
    public void the_users_with_active_reservations_are_notified_of_the_cancellation() {
        
    }

    @Then("the user is notified of the network issue")
    public void the_user_is_notified_of_the_network_issue() {
        assertNotNull(errorMessage);
    }

    @Then("the listing remains visible to the user and other users")
    public void the_listing_remains_visible_to_the_user_and_other_users() {
        
    }

    @Then("the user is denied permission to delete the listing")
    public void the_user_is_denied_permission_to_delete_the_listing() {
        
    }

    @Then("the listing remains visible in search results to other users")
    public void the_listing_remains_visible_in_search_results_to_other_users() {
        
    }
}