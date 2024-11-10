package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.dto.users.UserListDTO;
import HouseIt.dto.notifications.NotificationDTO;
import HouseIt.dto.notifications.NotificationListDTO;
import HouseIt.dto.users.UserDTO;
import HouseIt.service.NotificationService;
import HouseIt.service.UserService;
import HouseIt.model.Notification;
import HouseIt.model.User;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

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
    public ResponseEntity<UserDTO> getUserById(@RequestParam("id") int id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/users/{id}/notifications")
    public ResponseEntity<NotificationDTO> createNotification(@RequestParam("id") int id, @RequestBody NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = userService.getUserById(id);
        Notification notification = notificationService.createNotification(notificationDTO);
        user.addNotification(notification);
        NotificationDTO dto = notificationService.convertToDTO(notification);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("/users/{id}/notifications")
    public ResponseEntity<NotificationListDTO> getNotificationsByUserId(@RequestParam("id") int id) {
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        NotificationDTO notificationDTO;
        for (Notification notification : userService.getNotificationsByUserId(id)) {
            notificationDTO = new NotificationDTO(notification);
            notificationDTOs.add(notificationDTO);
        }
        return ResponseEntity.ok(new NotificationListDTO(notificationDTOs));
    }
    

}
