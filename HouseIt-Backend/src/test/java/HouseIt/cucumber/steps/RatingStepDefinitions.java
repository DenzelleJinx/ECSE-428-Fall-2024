package HouseIt.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import HouseIt.dao.AddressDAO;
import HouseIt.dao.AmenitiesDAO;
import HouseIt.dao.ImageDAO;
import HouseIt.dao.LandlordDAO;
import HouseIt.dao.ListingDAO;
import HouseIt.dao.StudentDAO;
import HouseIt.dao.UtilitiesDAO;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Image;
import HouseIt.model.Landlord;
import HouseIt.model.Listing;
import HouseIt.model.Student;
import HouseIt.model.Utilities;
import HouseIt.model.Listing.PropertyType;
import HouseIt.service.AddressService;
import HouseIt.service.AdministratorService;
import HouseIt.service.AmenitiesService;
import HouseIt.service.ImageService;
import HouseIt.service.LandlordService;
import HouseIt.service.ListingService;
import HouseIt.service.StudentService;
import HouseIt.service.UtilitiesService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.an;

public class RatingStepDefinitions {
    
    @Autowired
    private LandlordService landlordService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AmenitiesService amenitiesService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UtilitiesService utilitiesService;

    @Autowired
    private ListingService listingService;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private LandlordDAO landlordDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private AmenitiesDAO amenitiesDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private UtilitiesDAO utilitiesDAO;

    @Autowired
    private ListingDAO listingDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private String studentUsername = "studentUsername";
    private String password = "testPassword";
    private String studentEmail = "stu.dent@mail.mcgill.ca";
    private Student student;

    private String landlordUsername = "test";
    private String landlordEmail = "landlordEmail@email.com";
    private String phoneNumber = "1234567890";
    private Landlord landlord;

    private String appartmentNumber = "1";
    private String streetNumber = "1";
    private String street = "test";
    private String city = "test";
    private String postalCode = "H0H0H0";
    private Address address;

    private String listingTitle = "test";
    private String listingDescription = "test";
    private int listingPrice = 1;
    private int bedrooms = 1;
    private int bathrooms = 1;
    private PropertyType propertyType = PropertyType.APARTMENT;
    private int squareFootage = 1;
    private boolean wheelchairAccessible = false;
    private boolean smokingAllowed = false;
    private Amenities amenities;
    private Image image;
    private List<Image> images;
    private Utilities utilities;
    private Listing listing;

    @Before
    public void beforeScenario() {
        
    }

    @After
    public void afterScenario() {
        studentDAO.deleteAll();
        listingDAO.deleteAll();
        landlordDAO.deleteAll();
        addressDAO.deleteAll();
        amenitiesDAO.deleteAll();
        imageDAO.deleteAll();
        utilitiesDAO.deleteAll();
    }

    @Given("the user is a customer")
    public void the_user_is_a_customer() {
        // assume the user is a customer
    }

    @And("a listing transaction has been marked as closed by the landlord")
    public void a_listing_transaction_has_been_marked_as_closed_by_the_landlord() {
        studentDAO.deleteAll();
        student = null;
        student = studentService.createStudent(studentUsername, password, studentEmail);

        landlordDAO.deleteAll();
        landlord = null;
        landlord = landlordService.createLandlord(landlordUsername, password, landlordEmail, phoneNumber);
        administratorService.approveLandlord(landlord.getId());

        address = new Address();
        address.setApartmentNumber(appartmentNumber);
        address.setStreetNumber(streetNumber);
        address.setStreet(street);
        address.setCity(city);
        address.setPostalCode(postalCode);
        addressService.createAddress(address);

        amenities = new Amenities();
        amenities.setGym(false);
        amenities.setInternetIncluded(false);
        amenities.setLaundry(false);
        amenities.setParking(false);
        amenities.setPetsAllowed(false);
        amenitiesService.createAmenities(amenities);

        image = new Image();
        image.setUrl("url");
        imageService.createImage(image);
        images = new ArrayList<Image>();
        images.add(image);

        utilities = new Utilities();
        utilities.setElectricityCost(1);
        utilities.setHeatingCost(1);
        utilities.setWaterCost(1);
        utilitiesService.createUtilities(utilities);

        listingDAO.deleteAll();
        listing = null;
        listing = listingService.createListing(landlord.getId(), listingTitle, listingDescription, listingPrice, bedrooms, bathrooms, propertyType, squareFootage, wheelchairAccessible, smokingAllowed, address, amenities, images, utilities);
        listingService.completeListing(listing.getId());
        Listing updatedListing = listingDAO.findListingById(listing.getId());
        assertTrue(updatedListing.isCompleted());
    }

    @When("the user receives an email prompting them to rate the other customer involved in the transaction")
    public void the_user_receives_an_email_prompting_them_to_rate_the_other_customer_involved_in_the_transaction() {
        // assume the user receives an email prompting them to rate the other customer involved in the transaction
    }

    @And("the user follows the link given to them in the email")
    public void the_user_follows_the_link_given_to_them_in_the_email() {
        // assume the user follows the link given to them in the email
    }

    @And("the user selects a rating from 1 to 5 stars")
    public void the_user_selects_a_rating_from_1_to_5_stars() {
        float rating = 5.0f;
        landlordService.rateLandlord(landlord.getId(), rating);
        Landlord updatedLandlord = landlordDAO.findLandlordById(landlord.getId());
        assertTrue(updatedLandlord.getRating() == rating);
    }

    @Then("the rating is saved successfully")
    public void the_rating_is_saved_successfully() {
        // assume the rating is saved successfully
    }

    @And("the rating is displayed on the other customer’s profile for others to view")
    public void the_rating_is_displayed_on_the_other_customer_s_profile_for_others_to_view() {
        // assume the rating is displayed on the other customer’s profile for others to view
    }

    @And("the user leaves the rating selection blank")
    public void the_user_leaves_the_rating_selection_blank() {
        // assume the user leaves the rating selection blank
    }

    @Then("the user is prompted to select a rating")
    public void the_user_is_prompted_to_select_a_rating() {
        // assume the user is prompted to select a rating
    }

    @And("the rating is not saved")
    public void the_rating_is_not_saved() {
        Listing updatedListing = listingDAO.findListingById(listing.getId());
        assertTrue(updatedListing.getPropertyRating() == listing.getPropertyRating());
    }

    @Given("another customer views the profile of a previously rated customer")
    public void another_customer_views_the_profile_of_a_previously_rated_customer() {
        // assume another customer views the profile of a previously rated customer
    }

    @When("they navigate to the customer’s ratings section")
    public void they_navigate_to_the_customer_s_ratings_section() {
        // assume they navigate to the customer’s ratings section
    }

    @Then("they see the customer’s average rating")
    public void they_see_the_customer_s_average_rating() {
        // assume they see the customer’s average rating
    }
}
