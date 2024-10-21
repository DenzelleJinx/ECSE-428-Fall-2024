package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 1 "Umplesrc"
public class User
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum AccountStatus { Pending, Active, Suspended, Deactivated, Denied }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, User> usersById = new HashMap<Integer, User>();
  private static Map<String, User> usersByUsername = new HashMap<String, User>();
  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int id;
  private String username;
  private String email;
  private String password;
  private AccountStatus status;
  private float rating;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating)
  {
    password = aPassword;
    status = aStatus;
    rating = aRating;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
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

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      usersById.remove(anOldId);
    }
    usersById.put(aId, this);
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
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
  /* Code from template attribute_GetUnique */
  public static User getWithId(int aId)
  {
    return usersById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
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

  public void delete()
  {
    usersById.remove(getId());
    usersByUsername.remove(getUsername());
    usersByEmail.remove(getEmail());
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
}