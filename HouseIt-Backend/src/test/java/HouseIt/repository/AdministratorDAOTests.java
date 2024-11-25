package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.model.Administrator;
import HouseIt.dao.AdministratorDAO;
import HouseIt.model.User.AccountStatus;

@SpringBootTest
public class AdministratorDAOTests {

    @Autowired
    private AdministratorDAO administratorDAO;

    private String username = "testAdministrator";
    private String email = "test@mail.mcgill.ca";
    private String password = "password";
    private AccountStatus status = AccountStatus.ACTIVE;
    private float rating = 5.0f;
    private Administrator administrator;

    @BeforeEach
    @AfterEach
    public void tearDown() {
        administratorDAO.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setEmail(email);
        administrator.setPassword(password);
        administrator.setStatus(status);
        administrator.setRating(rating);

        administratorDAO.save(administrator);
    }

    @Test
    public void testFindAdministratorByUsername() {
        Administrator foundAdministrator = administratorDAO.findAdministratorByUsername(username);

        assertNotNull(foundAdministrator);
        assertEquals(administrator, foundAdministrator);
    }

    @Test
    public void testFindAdministratorByEmail() {
        Administrator foundAdministrator = administratorDAO.findAdministratorByEmail(email);

        assertNotNull(foundAdministrator);
        assertEquals(administrator, foundAdministrator);
    }

    @Test
    public void testFindAdministratorsByStatus() {
        Administrator foundAdministrator = administratorDAO.findAdministratorByStatus(status).get(0);

        assertNotNull(foundAdministrator);
        assertEquals(administrator, foundAdministrator);
    }

    @Test
    public void testFindAdministratorsByRating() {
        Administrator foundAdministrator = administratorDAO.findAdministratorByRating(rating).get(0);

        assertNotNull(foundAdministrator);
        assertEquals(administrator, foundAdministrator);
    }

    @Test
    public void testFindAdministratorById() {
        Administrator foundAdministrator = administratorDAO.findAdministratorById(administrator.getId());

        assertNotNull(foundAdministrator);
        assertEquals(administrator, foundAdministrator);
    }
}
