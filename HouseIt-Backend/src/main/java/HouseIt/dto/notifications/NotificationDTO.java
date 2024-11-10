package HouseIt.dto.notifications;

import HouseIt.model.Notification;

public class NotificationDTO {
    
    private int id;
    private String message;
    private String localDateTime;
    private String type;
    private int senderId;

    public NotificationDTO() {}

    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.localDateTime = notification.getLocalDateTime().toString();
        this.type = notification.getType().toString();
        this.senderId = notification.getSender().getId();
    }

    public NotificationDTO(int id, String message, String localDateTime, String type, int senderId) {
        this.id = id;
        this.message = message;
        this.localDateTime = localDateTime;
        this.type = type;
        this.senderId = senderId;
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

    public int getSenderId() {
        return senderId;
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

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
