package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.dao.ListingDAO;
import HouseIt.model.Listing;

@SpringBootTest
public class ListingDAOTests {

    @Autowired
    private ListingDAO listingDAO;

    private String title = "testListing";
    private String description = "testDescription";
    private int monthlyPrice = 1000;
    private float propertyRating = 5.0f;
    private int bedrooms = 2;
    private int bathrooms = 1;
    private int squareFootage = 1000;
    private Boolean wheelchairAccessible = true;
    private Boolean hidden = false;
    private Boolean smokingAllowed = false;
    private Listing.PropertyType propertyType = Listing.PropertyType.APARTMENT;
    private Listing listing;

    @BeforeEach
    public void setUp() {
        listing = new Listing();
        listing.setTitle(title);
        listing.setDescription(description);
        listing.setMonthlyPrice(monthlyPrice);
        listing.setPropertyRating(propertyRating);
        listing.setBedrooms(bedrooms);
        listing.setBathrooms(bathrooms);
        listing.setSquareFootage(squareFootage);
        listing.setWheelchairAccessible(wheelchairAccessible);
        listing.setHidden(hidden);
        listing.setSmokingAllowed(smokingAllowed);
        listing.setPropertyType(propertyType);

        listingDAO.save(listing);
    }

    @BeforeEach
    @AfterEach
    public void tearDown() {
        listingDAO.deleteAll();
    }

    @Test
    public void testFindListingByTitle() {
        Listing foundListing = listingDAO.findListingByTitle(title);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByDescription() {
        Listing foundListing = listingDAO.findListingByDescription(description);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByMonthlyPrice() {
        Listing foundListing = listingDAO.findListingByMonthlyPrice(monthlyPrice).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByPropertyRating() {
        Listing foundListing = listingDAO.findListingByPropertyRating(propertyRating).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByBedrooms() {
        Listing foundListing = listingDAO.findListingByBedrooms(bedrooms).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByBathrooms() {
        Listing foundListing = listingDAO.findListingByBathrooms(bathrooms).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByPropertyType() {
        Listing foundListing = listingDAO.findListingByPropertyType(propertyType).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingBySquareFootage() {
        Listing foundListing = listingDAO.findListingBySquareFootage(squareFootage).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByWheelchairAccessible() {
        Listing foundListing = listingDAO.findListingByWheelchairAccessible(wheelchairAccessible).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingByHidden() {
        Listing foundListing = listingDAO.findListingByHidden(hidden).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingBySmokingAllowed() {
        Listing foundListing = listingDAO.findListingBySmokingAllowed(smokingAllowed).get(0);

        assertNotNull(foundListing);
        assertEquals(listing, foundListing);
    }

    @Test
    public void testFindListingById() {
        Listing foundListing = listingDAO.findListingById(listing.getId());

        assertNotNull(foundListing);
        assertTrue(listing.equals(foundListing));
        assertEquals(listing, foundListing);
    }
}