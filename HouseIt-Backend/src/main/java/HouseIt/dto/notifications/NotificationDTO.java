package HouseIt.dto.notifications;

import HouseIt.model.Notification;

public class NotificationDTO {
    
    private int id;
    private String message;
    private String localDateTime;
    private String type;
    private String senderUsername;

    public NotificationDTO() {}

    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.localDateTime = notification.getLocalDateTime().toString();
        this.type = notification.getType().toString();
        this.senderUsername = notification.getSender().getUsername();
    }

    public NotificationDTO(int id, String message, String localDateTime, String type, String senderUsername) {
        this.id = id;
        this.message = message;
        this.localDateTime = localDateTime;
        this.type = type;
        this.senderUsername = senderUsername;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public String getType() {
        return type;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
}
