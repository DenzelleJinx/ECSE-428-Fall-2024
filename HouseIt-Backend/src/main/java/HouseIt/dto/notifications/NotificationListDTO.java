package HouseIt.dto.notifications;

import java.util.List;

public class NotificationListDTO {
    
    private List<NotificationDTO> notifications;

    public NotificationListDTO() {}

    public NotificationListDTO(List<NotificationDTO> notifications) {
        this.notifications = notifications;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDTO> notifications) {
        this.notifications = notifications;
    }
}
