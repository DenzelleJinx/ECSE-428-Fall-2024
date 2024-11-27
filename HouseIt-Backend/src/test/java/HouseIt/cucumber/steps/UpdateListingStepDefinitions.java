package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.dao.ListingDAO;
import HouseIt.model.Amenities;
import HouseIt.model.Listing;
import HouseIt.service.ListingService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateListingStepDefinitions {

    @Autowired
    private ListingService listingService;

    @Autowired
    private ListingDAO listingDAO;

    private Listing existingListing;
    private String errorMessage;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        // Assume this is handled by authentication middleware
    }

    @Given("the user is a landlord")
    public void theUserIsALandlord() {
        // Assume role-based access is verified
    }

    @Given("the user is the author of an existing listing")
    public void theUserIsTheAuthorOfAnExistingListing() {
        // Create a test listing
        existingListing = listingService.createListing(
            1, // Dummy landlord ID
            "Original Listing",
            "Original Description",
            1000,
            2,
            1,
            Listing.PropertyType.APARTMENT,
            900,
            true,
            true,
            null, // Address placeholder
            null, // Amenities placeholder
            new ArrayList<>(),
            null
        );
    }

    @When("the user chooses to edit the listing")
    public void theUserChoosesToEditTheListing() {
        // User initiates the edit flow
    }

    @When("the user updates the location, price, description, and amenities with valid information")
    public void theUserUpdatesWithValidInformation() {
        existingListing.setDescription("Updated Description");
        existingListing.setPrice(1200);

        Amenities updatedAmenities = new Amenities();
        updatedAmenities.setGym(false);
        updatedAmenities.setLaundry(true);
        updatedAmenities.setInternetIncluded(false);
        existingListing.setAmenities(updatedAmenities);

        listingService.updateListing(existingListing.getId(), existingListing);
    }

    @Then("the changes are saved successfully")
    public void theChangesAreSavedSuccessfully() {
        Listing updatedListing = listingService.getListingById(existingListing.getId());
        assertEquals("Updated Description", updatedListing.getDescription());
        assertEquals(1200, updatedListing.getPrice(), 0);
        assertFalse(updatedListing.getAmenities().isGym());
        assertTrue(updatedListing.getAmenities().isLaundry());
    }

    @When("the user leaves the price field empty")
    public void theUserLeavesThePriceFieldEmpty() {
        try {
            existingListing.setPrice(null);
            listingService.updateListing(existingListing.getId(), existingListing);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the user is prompted to complete all required fields")
    public void theUserIsPromptedToCompleteAllRequiredFields() {
        assertEquals("Price is a required field.", errorMessage);
    }

    @When("the user uploads pictures in an unsupported format")
    public void theUserUploadsPicturesInUnsupportedFormat() {
        try {
            List<String> invalidPictures = List.of("invalid_image.txt");
            existingListing.setPictures(invalidPictures);
            listingService.updateListing(existingListing.getId(), existingListing);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the user is shown an error message stating {string}")
    public void theUserIsShownAnErrorMessageStating(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }

    @When("the user attempts to change the listing location")
    public void theUserAttemptsToChangeTheListingLocation() {
        // No action required, location is read-only
    }

    @Then("the listing location will be grayed out")
    public void theListingLocationWillBeGrayedOut() {
        assertFalse(existingListing.getAddress() == null); // Location cannot be modified
    }

    @When("the user chooses to close the listing")
    public void theUserChoosesToCloseTheListing() {
        existingListing.setActive(false);
        listingService.updateListing(existingListing.getId(), existingListing);
    }

    @Then("the listing will be marked as closed")
    public void theListingWillBeMarkedAsClosed() {
        Listing updatedListing = listingService.getListingById(existingListing.getId());
        assertFalse(updatedListing.isActive());
    }

    @Then("the listing will no longer be visible on the home page")
    public void theListingWillNoLongerBeVisibleOnTheHomePage() {
        List<Listing> activeListings = listingService.getActiveListings();
        assertFalse(activeListings.contains(existingListing));
    }

    @Then("the listing will be visible in the landlord's listings")
    public void theListingWillBeVisibleInTheLandlordSListings() {
        List<Listing> landlordListings = listingService.getListingsByLandlordId(1);
        assertTrue(landlordListings.contains(existingListing));
    }

    @Then("the listing will be marked as closed")
    public void theListingWillBeMarkedAsClosedAgain() {
        assertFalse(existingListing.isActive());
    }
}
