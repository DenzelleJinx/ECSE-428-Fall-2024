package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.model.Notification;
import HouseIt.model.User;
import HouseIt.model.User.AccountStatus;
import HouseIt.dao.UserDAO;

@SpringBootTest
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @AfterEach
    public void clearDatabase() {
        userDAO.deleteAll();
    }

    @Test
    public void testFindUserByUsername() {
        String username = "testUser";
        String email = "testUser@email.com";
        String password = "password";
        AccountStatus status = AccountStatus.ACTIVE;
        float rating = 5.0f;
        List<Notification> notifications = new ArrayList<>();

        User user = new User(username, email, password, status, rating, notifications);
        userDAO.save(user);

        User foundUser = userDAO.findUserByUsername(username);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindUserByEmail() {
        String username = "testUser2";
        String email = "testUser2@email.com";
        String password = "password2";
        AccountStatus status = AccountStatus.PENDING;
        float rating = 4.0f;
        List<Notification> notifications = new ArrayList<>();

        User user = new User(username, email, password, status, rating, notifications);
        userDAO.save(user);

        User foundUser = userDAO.findUserByEmail(email);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindUserByStatus() {
        String username = "testUser3";
        String email = "testUser3@email.com";
        String password = "password3";
        AccountStatus status = AccountStatus.SUSPENDED;
        float rating = 3.0f;
        List<Notification> notifications = new ArrayList<>();

        User user = new User(username, email, password, status, rating, notifications);
        userDAO.save(user);

        User foundUser = userDAO.findUserByStatus(status);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindUserByNonExistentUsername() {
        User foundUser = userDAO.findUserByUsername("nonExistentUser");
        assertNull(foundUser);
    }

    @Test
    public void testFindUserByNonExistentEmail() {
        User foundUser = userDAO.findUserByEmail("nonExistent@email.com");
        assertNull(foundUser);
    }

    @Test
    public void testDeleteUser() {
        String username = "deletableUser";
        String email = "deletableUser@email.com";
        String password = "deletePassword";
        AccountStatus status = AccountStatus.ACTIVE;
        float rating = 5.0f;
        List<Notification> notifications = new ArrayList<>();

        User user = new User(username, email, password, status, rating, notifications);
        userDAO.save(user);

        userDAO.delete(user);
        User foundUser = userDAO.findUserByUsername(username);
        assertNull(foundUser);
    }
}
