package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.dto.notifications.NotificationDTO;
import HouseIt.dto.notifications.NotificationListDTO;
import HouseIt.model.Notification;
import HouseIt.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable("id") int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        Notification notification = notificationService.getNotificationById(id);
        NotificationDTO dto = notificationService.convertToDTO(notification);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/notifications")
    public ResponseEntity<NotificationListDTO> getAllNotifications() {
        List<NotificationDTO> notificationsDTOs = new ArrayList<>();
        NotificationDTO notificationDTO;
        for (Notification notification : notificationService.getAllNotifications()) {
            notificationDTO = new NotificationDTO(notification);
            notificationsDTOs.add(notificationDTO);
        }
        return ResponseEntity.ok(new NotificationListDTO(notificationsDTOs));
    }
}
