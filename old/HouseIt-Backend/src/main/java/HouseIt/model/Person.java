/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 12 "model.ump"
// line 86 "model.ump"
public class Person
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Person> personsByEmail = new HashMap<String, Person>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String password;

  //Person Associations
  private PersonRole personRoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aFirstName, String aLastName, String aEmail, String aPhoneNumber, String aPassword, PersonRole aPersonRoles)
  {
    firstName = aFirstName;
    lastName = aLastName;
    phoneNumber = aPhoneNumber;
    password = aPassword;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setPersonRoles(aPersonRoles))
    {
      throw new RuntimeException("Unable to create Person due to aPersonRoles. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
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
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      personsByEmail.remove(anOldEmail);
    }
    personsByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
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

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static Person getWithEmail(String aEmail)
  {
    return personsByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public PersonRole getPersonRoles()
  {
    return personRoles;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPersonRoles(PersonRole aNewPersonRoles)
  {
    boolean wasSet = false;
    if (aNewPersonRoles != null)
    {
      personRoles = aNewPersonRoles;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    personsByEmail.remove(getEmail());
    personRoles = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "personRoles = "+(getPersonRoles()!=null?Integer.toHexString(System.identityHashCode(getPersonRoles())):"null");
  }
}