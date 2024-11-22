package HouseIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import HouseIt.model.Administrator;
import HouseIt.model.Notification;
import HouseIt.model.User;
import HouseIt.model.User.AccountStatus;
import HouseIt.utils.Helper;
import HouseIt.dao.AdministratorDAO;
import HouseIt.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AdministratorDAO administratorDAO;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public Administrator createAdmin() {
        String rawPassword = "adminpassword";
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        
        User admin = getUserByEmail("admin@house.it");
        if (admin == null) {
            Administrator newAdmin = new Administrator("admin", "admin@house.it", encodedPassword, AccountStatus.ACTIVE, 0.0f, List.of());
            administratorDAO.save(newAdmin);
            userDAO.save(newAdmin);
            return newAdmin;
        } else {
            throw new IllegalArgumentException("Admin already exists.");
        }
    }
    
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
    public User getUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional
    public Notification createNotificationForUser(String username, String notificationType, String message, String senderUsername) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with username " + username + " does not exist, and can not receive a notification.");
        }
        User sender = getUserByUsername(senderUsername);
        if (sender == null) {
            throw new IllegalArgumentException("User with username " + senderUsername + " does not exist, and cannot send a notification.");
        }
        Notification notification;
        try {
            notification = notificationService.createNotification(notificationType, message, sender);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        user.addNotification(notification);
        userDAO.save(user);
        return notification;
    }

    @Transactional
    public List<Notification> getNotificationsByUserUsername(String username) {
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with username " + username + " does not exist.");
        }
        return user.getNotifications();
    }
}
