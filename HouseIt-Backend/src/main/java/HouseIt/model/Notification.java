package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;

// line 17 "model.ump"
// line 162 "model.ump"
@Entity
public class Notification
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum NotificationType { CONTACT, REVIEW, OTHER }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Notification Attributes
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime localDateTime;
    private String message;
    private NotificationType type;
    @ManyToOne
    private User sender;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Notification() {}

    public Notification(LocalDateTime aLocalDateTime, String aMessage, NotificationType aType, User aSender)
    {
        localDateTime = aLocalDateTime;
        message = aMessage;
        type = aType;
        sender = aSender;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setLocalDateTime(LocalDateTime aLocalDateTime)
    {
        boolean wasSet = false;
        localDateTime = aLocalDateTime;
        wasSet = true;
        return wasSet;
    }

    public boolean setMessage(String aMessage)
    {
        boolean wasSet = false;
        message = aMessage;
        wasSet = true;
        return wasSet;
    }

    public boolean setType(NotificationType aType)
    {
        boolean wasSet = false;
        type = aType;
        wasSet = true;
        return wasSet;
    }

    public boolean setSender(User aSender)
    {
        boolean wasSet = false;
        sender = aSender;
        wasSet = true;
        return wasSet;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime()
    {
        return localDateTime;
    }

    public String getMessage()
    {
        return message;
    }

    public NotificationType getType()
    {
        return type;
    }

    public User getSender()
    {
        return sender;
    }

    public void delete()
    {}


    public String toString()
    {
        return super.toString() + "["+
                "id" + ":" + getId()+ "," +
                "message" + ":" + getMessage()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "localDateTime" + "=" + (getLocalDateTime() != null ? !getLocalDateTime().equals(this)  ? getLocalDateTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "sender = "+(getSender()!=null?Integer.toHexString(System.identityHashCode(getSender())):"null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification notification = (Notification) o;
        if (notification.getMessage() != null && message != null && !notification.getMessage().equals(message)){
            return false;
        }
        if (notification.getSender() != null && sender != null && !notification.getSender().equals(sender)){
            return false;
        }
        return id == notification.id &&
                localDateTime.equals(notification.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, localDateTime, message);
    }
}