package HouseIt.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import HouseIt.model.Notification;
import HouseIt.model.User;

public interface NotificationDAO extends CrudRepository<Notification, Integer> {

    Notification findNotificationById(int id);

    List<Notification> findNotificationsByMessage(String message);

    List<Notification> findNotificationsByType(Notification.NotificationType type);

    List<Notification> findNotificationsBySender(User sender);

    List<Notification> findNotificationsByLocalDateTime(LocalDateTime localDateTime);

}
