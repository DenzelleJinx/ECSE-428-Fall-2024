package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.model.Landlord;
import HouseIt.dao.LandlordDAO;
import HouseIt.model.User.AccountStatus;

@SpringBootTest
public class LandlordDAOTests {

    @Autowired
    private LandlordDAO landlordDAO;

    private String username = "testLandlord";
    private String email = "test@email.com";
    private String password = "password";
    private String phoneNumber = "1234567890";
    private AccountStatus status = AccountStatus.ACTIVE;
    private float rating = 5.0f;
    private Landlord landlord;

    @AfterEach
    public void tearDown() {
        landlordDAO.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        landlord = new Landlord();
        landlord.setUsername(username);
        landlord.setEmail(email);
        landlord.setPassword(password);
        landlord.setPhoneNumber(phoneNumber);
        landlord.setStatus(status);
        landlord.setRating(rating);

        landlordDAO.save(landlord);
    }

    @Test
    public void testFindLandlordByUsername() {
        Landlord foundLandlord = landlordDAO.findLandlordByUsername(username);

        assertNotNull(foundLandlord);
        assertEquals(landlord, foundLandlord);
    }

    @Test
    public void testFindLandlordByEmail() {
        Landlord foundLandlord = landlordDAO.findLandlordByEmail(email);

        assertNotNull(foundLandlord);
        assertEquals(landlord, foundLandlord);
    }

    @Test
    public void testFindLandlordsByStatus() {
        Landlord foundLandlord = landlordDAO.findLandlordByStatus(status).get(0);

        assertNotNull(foundLandlord);
        assertEquals(landlord, foundLandlord);
    }

    @Test
    public void testFindLandlordsByRating() {
        Landlord foundLandlord = landlordDAO.findLandlordByRating(rating).get(0);

        assertNotNull(foundLandlord);
        assertEquals(landlord, foundLandlord);
    }

    @Test
    public void testFindLandlordById() {
        Landlord foundLandlord = landlordDAO.findLandlordById(landlord.getId());

        assertNotNull(foundLandlord);
        assertTrue(landlord.equals(foundLandlord));
        assertEquals(landlord, foundLandlord);
    }
}
