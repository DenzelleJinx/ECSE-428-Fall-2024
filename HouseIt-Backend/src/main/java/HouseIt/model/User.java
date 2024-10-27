package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

// line 2 "model.ump"
// line 108 "model.ump"
@Entity
@Table(name = "houseIt_user")
public class User
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum AccountStatus { PENDING, ACTIVE, SUSPENDED, DEACTIVATED, DENIED }

  //------------------------
  // STATIC VARIABLES
  //------------------------


  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  @Id
  @GeneratedValue
  private int id;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  private AccountStatus status;
  private float rating;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User() {}

  // do not use this constructor
  public User(String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating)
  {
    password = aPassword;
    status = aStatus;
    rating = aRating;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(AccountStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setRating(float aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getUsername()
  {
    return username;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public AccountStatus getStatus()
  {
    return status;
  }

  public float getRating()
  {
    return rating;
  }

  // use repository to delete
  public void delete()
  {

  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "username" + ":" + getUsername()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "rating" + ":" + getRating()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return id == user.id &&
            Float.compare(user.rating, rating) == 0 &&
            Objects.equals(username, user.username) &&
            Objects.equals(email, user.email) &&
            Objects.equals(password, user.password) &&
            status == user.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, status, rating);
  }
}