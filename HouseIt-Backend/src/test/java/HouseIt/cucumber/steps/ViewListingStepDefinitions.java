package HouseIt.cucumber.steps;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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

public class ViewListingStepDefinitions {

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

    @After
    public void beforeScenario() {
        landlordDAO.deleteAll();
        listingDAO.deleteAll();
        addressDAO.deleteAll();
        amenitiesDAO.deleteAll();
    }

    @Given("there are available listings")
    public void thereAreAvailableListings() {
        Landlord landlord = landlordService.createLandlord("username", "password", "email@gmail.com", "1234567890");
        
        Address dummyAddress = new Address();
        dummyAddress.setCity("testCity");
        dummyAddress.setPostalCode("A1A1A1");
        dummyAddress.setStreet("testStreet");
        dummyAddress.setStreetNumber("123");
        dummyAddress.setApartmentNumber("123");

        Address dummyAddress2 = new Address();
        dummyAddress2.setCity("testCity2");
        dummyAddress2.setPostalCode("A1A1A2");
        dummyAddress2.setStreet("testStreet2");
        dummyAddress2.setStreetNumber("234");
        dummyAddress2.setApartmentNumber("234");

        Amenities dummyAmenities = new Amenities();
        dummyAmenities.setGym(true);
        dummyAmenities.setLaundry(true);
        dummyAmenities.setPetsAllowed(true);
        dummyAmenities.setParking(true);
        dummyAmenities.setInternetIncluded(true);
        
        Amenities dummyAmenities2 = new Amenities();
        dummyAmenities.setGym(true);
        dummyAmenities.setLaundry(true);
        dummyAmenities.setPetsAllowed(true);
        dummyAmenities.setParking(true);
        dummyAmenities.setInternetIncluded(true);

        listingService.createListing(
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

        listingService.createListing(
            landlord.getId(),
            "testListing2",
            "testDescription2",
            2000,
            4,
            3,
            Listing.PropertyType.HOUSE,
            2000,
            false,
            true,
            dummyAddress2,
            dummyAmenities2,
            new ArrayList<>(),
            null
        );
    }

    @Given("there are no available listings")
    public void thereAreNoAvailableListings() {
        // Do not create any listings
    }

    @Given("a listing is available")
    public void aListingIsAvailable() {
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

        listingService.createListing(
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

    @Given("the user is in the listing page")
    public void theUserIsInTheListingPage() {
        // Implemented in the frontend
    }

    @When("the user enters the listings page")
    public void theUserEntersTheListingsPage() {
        // Implemented in the frontend
    }

    @When("the user selects an available listing")
    public void theUserSelectsAnAvailableListing() {
        // Implemented in the frontend
    }

    @Then("the list of available listings should be displayed")
    public void theListOfAvailableListingsShouldBeDisplayed() {
        List<Listing> listings = listingService.getAllListings();
        assertTrue(listings.size() > 0);
        // The list of available listings is then displayed in the frontend
    }

    @Then("a message should be displayed indicating that no listings are available")
    public void aMessageShouldBeDisplayedIndicatingThatNoListingsAreAvailable() {
        List<Listing> listings = listingService.getAllListings();
        assertTrue(listings.size() == 0);
        // The message is then displayed in the frontend
    }

    @Then("a new page should open displaying detailed information about the selected listing")
    public void aNewPageShouldOpenDisplayingDetailedInformationAboutTheSelectedListing() {
        // Implemented in the frontend
    }

    @Then("the list of available listings should not be displayed")
    public void theListOfAvailableListingsShouldNotBeDisplayed() {
        // Implemented in the frontend
    }

    @Then("the user is redirected to the login page")
    public void theUserIsRedirectedToTheLoginPage() {
        // Implemented in the frontend
    }
}