package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.dto.users.UserListDTO;
import HouseIt.dto.notifications.NotificationDTO;
import HouseIt.dto.notifications.NotificationListDTO;
import HouseIt.dto.users.UserDTO;
import HouseIt.service.UserService;
import HouseIt.model.Notification;
import HouseIt.model.User;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/users", "/users/"})
    public ResponseEntity<UserListDTO> getAllUsers() {
        List<UserDTO> userResponseDTOs = new ArrayList<>();
        UserDTO userDTO;
        for (User user : userService.getAllUsers()) {
            userDTO = new UserDTO(user);
            userResponseDTOs.add(userDTO);
        }
        return ResponseEntity.ok(new UserListDTO(userResponseDTOs));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/users/{username}/notifications")
    public ResponseEntity<String> createNotificationForUser(@PathVariable("username") String username, @RequestBody NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            userService.createNotificationForUser(username, notificationDTO.getType(), notificationDTO.getMessage(), notificationDTO.getSenderUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.created(null).body("Notification created succesfully for user " + username);
    }

    @GetMapping("/users/{username}/notifications")
    public ResponseEntity<NotificationListDTO> getNotificationsByUsername(@PathVariable("username") String username) {
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        NotificationDTO notificationDTO;
        try {
            for (Notification notification : userService.getNotificationsByUserUsername(username)) {
                notificationDTO = new NotificationDTO(notification);
                notificationDTOs.add(notificationDTO);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(new NotificationListDTO(notificationDTOs));
    }
    

}
