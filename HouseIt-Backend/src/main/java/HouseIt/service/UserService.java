package HouseIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import HouseIt.model.Notification;
import HouseIt.model.User;
import HouseIt.utils.Helper;
import HouseIt.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Transactional
    public List<User> getAllUsers() {
        return Helper.toList(userDAO.findAll());
    }

    @Transactional
    public User getUserById(int id) {
        return userDAO.findUserById(id);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Transactional
    public List<Notification> getNotificationsByUserId(int id) {
        User user = userDAO.findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        return user.getNotifications();
    }
}
