package HouseIt.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(status);
        user.setRating(rating);

        userDAO.save(user);

        User foundUser = userDAO.findUserByUsername(username);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }
}