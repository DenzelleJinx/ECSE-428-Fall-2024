package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.dao.AmenitiesDAO;
import HouseIt.model.Amenities;

@SpringBootTest
public class AmenitiesDAOTests {

    @Autowired
    private AmenitiesDAO amenitiesDAO;

    private boolean gym = true;
    private boolean laundry = true;
    private boolean petsAllowed = true;
    private boolean parking = true;
    private boolean internetIncluded = true;
    private Amenities amenities;

    @BeforeEach
    public void setUp() {
        amenities = new Amenities();
        amenities.setGym(gym);
        amenities.setLaundry(laundry);
        amenities.setPetsAllowed(petsAllowed);
        amenities.setParking(parking);
        amenities.setInternetIncluded(internetIncluded);

        amenitiesDAO.save(amenities);
    }

    @BeforeEach
    @AfterEach
    public void tearDown() {
        amenitiesDAO.deleteAll();
    }

    @Test
    public void testFindAmenitiesByGym() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiessByGym(gym).get(0);

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testFindAmenitiesByLaundry() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiessByLaundry(laundry).get(0);

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testFindAmenitiesByPetsAllowed() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiessByPetsAllowed(petsAllowed).get(0);

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testFindAmenitiesByParking() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiessByParking(parking).get(0);

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testFindAmenitiesByInternetIncluded() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiessByInternetIncluded(internetIncluded).get(0);

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testFindAmenitiesById() {
        Amenities foundAmenities = amenitiesDAO.findAmenitiesById(amenities.getId());

        assertNotNull(foundAmenities);
        assertEquals(amenities, foundAmenities);
    }

    @Test
    public void testDeleteAmenitiesById() {
        amenitiesDAO.deleteById(amenities.getId());

        Amenities foundAmenities = amenitiesDAO.findAmenitiesById(amenities.getId());

        assertNull(foundAmenities);
    }
}