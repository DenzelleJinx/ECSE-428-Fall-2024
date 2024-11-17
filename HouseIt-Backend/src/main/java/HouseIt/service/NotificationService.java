package HouseIt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HouseIt.dao.NotificationDAO;
import HouseIt.dto.notifications.NotificationDTO;
import HouseIt.model.Notification;
import HouseIt.model.Notification.NotificationType;
import HouseIt.utils.Helper;
import HouseIt.model.User;
import jakarta.transaction.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Transactional
    public Notification createNotification(String notificationType, String message, User sender) {
        if (notificationType == null) {
            throw new IllegalArgumentException("Notification type cannot be empty.");
        }
        switch (notificationType.toUpperCase()) {
            case "CONTACT":
                if (sender == null) {
                    throw new IllegalArgumentException("Sender with username " + sender.getUsername() + " does not exist.");
                }
                Notification contactNotification = new Notification();
                contactNotification.setSender(sender);
                contactNotification.setType(NotificationType.CONTACT);
                contactNotification.setLocalDateTime(LocalDateTime.now());
                notificationDAO.save(contactNotification);
                return contactNotification;

            case "REVIEW":
                Notification reviewNotification = new Notification();
                reviewNotification.setType(NotificationType.REVIEW);
                reviewNotification.setMessage("Your account status has changed, please review it in your account page.");
                reviewNotification.setLocalDateTime(LocalDateTime.now());
                notificationDAO.save(reviewNotification);
                return reviewNotification;

            case "OTHER":
                Notification otherNotification = new Notification();
                otherNotification.setType(NotificationType.OTHER);
                if (message == null || message.isEmpty() || message.equals("")) {
                    throw new IllegalArgumentException("Message cannot be empty for notification type OTHER.");
                }
                otherNotification.setMessage(message);
                otherNotification.setLocalDateTime(LocalDateTime.now());
                notificationDAO.save(otherNotification);
                return otherNotification;
        
            default:
                throw new IllegalArgumentException("Invalid notification type, must be one of 'CONTACT', 'REVIEW', or 'OTHER'.");
        }
    }

    @Transactional
    public Notification getNotificationById(int id) {
        Notification notification = notificationDAO.findNotificationById(id);
        if (notification == null) {
            throw new IllegalArgumentException("Notification with ID " + id + " does not exist.");
        }
        return notification;
    }

    @Transactional
    public List<Notification> getAllNotifications() {
        return Helper.toList(notificationDAO.findAll());
    }

    public NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setLocalDateTime(notification.getLocalDateTime().toString());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType().toString());
        dto.setSenderUsername(notification.getSender().getUsername());
        return dto;
    }
    
}
