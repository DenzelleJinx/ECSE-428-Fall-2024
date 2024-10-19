//%% NEW FILE User BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 82 "model.ump"
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



//%% NEW FILE Administrator BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 31 "model.ump"
// line 99 "model.ump"
public class Administrator extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating)
  {
    super(aId, aUsername, aEmail, aPassword, aStatus, aRating);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}



//%% NEW FILE Address BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



/**
 * Optionally address is just a string
 * Could use some Google Maps integration or something
 */
// line 63 "model.ump"
// line 110 "model.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String city;
  private String postalCode;
  private String street;
  private int streetNumber;
  private int apartmentNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aCity, String aPostalCode, String aStreet, int aStreetNumber, int aApartmentNumber)
  {
    city = aCity;
    postalCode = aPostalCode;
    street = aStreet;
    streetNumber = aStreetNumber;
    apartmentNumber = aApartmentNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreet(String aStreet)
  {
    boolean wasSet = false;
    street = aStreet;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetNumber(int aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setApartmentNumber(int aApartmentNumber)
  {
    boolean wasSet = false;
    apartmentNumber = aApartmentNumber;
    wasSet = true;
    return wasSet;
  }

  public String getCity()
  {
    return city;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getStreet()
  {
    return street;
  }

  public int getStreetNumber()
  {
    return streetNumber;
  }

  public int getApartmentNumber()
  {
    return apartmentNumber;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "city" + ":" + getCity()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "street" + ":" + getStreet()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "apartmentNumber" + ":" + getApartmentNumber()+ "]";
  }
}



//%% NEW FILE Listing BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 36 "model.ump"
// line 104 "model.ump"
public class Listing
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PropertyType { Studio, Dorm, Condo, Apartment, House }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Listing Attributes
  private String title;
  private String description;
  private Address address;
  private int monthlyPrice;
  private float propertyRating;
  private int bedrooms;
  private int bathrooms;
  private PropertyType propertyType;
  private int squareFootage;
  private Bool wheelchairAccessible;
  private Bool hidden;

  //Listing Associations
  private List<Image> propertyImages;
  private Landlord poster;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Bool aWheelchairAccessible, Bool aHidden, Landlord aPoster)
  {
    title = aTitle;
    description = aDescription;
    address = aAddress;
    monthlyPrice = aMonthlyPrice;
    propertyRating = aPropertyRating;
    bedrooms = aBedrooms;
    bathrooms = aBathrooms;
    propertyType = aPropertyType;
    squareFootage = aSquareFootage;
    wheelchairAccessible = aWheelchairAccessible;
    hidden = aHidden;
    propertyImages = new ArrayList<Image>();
    boolean didAddPoster = setPoster(aPoster);
    if (!didAddPoster)
    {
      throw new RuntimeException("Unable to create property due to poster. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(Address aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setMonthlyPrice(int aMonthlyPrice)
  {
    boolean wasSet = false;
    monthlyPrice = aMonthlyPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setPropertyRating(float aPropertyRating)
  {
    boolean wasSet = false;
    propertyRating = aPropertyRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setBedrooms(int aBedrooms)
  {
    boolean wasSet = false;
    bedrooms = aBedrooms;
    wasSet = true;
    return wasSet;
  }

  public boolean setBathrooms(int aBathrooms)
  {
    boolean wasSet = false;
    bathrooms = aBathrooms;
    wasSet = true;
    return wasSet;
  }

  public boolean setPropertyType(PropertyType aPropertyType)
  {
    boolean wasSet = false;
    propertyType = aPropertyType;
    wasSet = true;
    return wasSet;
  }

  public boolean setSquareFootage(int aSquareFootage)
  {
    boolean wasSet = false;
    squareFootage = aSquareFootage;
    wasSet = true;
    return wasSet;
  }

  public boolean setWheelchairAccessible(Bool aWheelchairAccessible)
  {
    boolean wasSet = false;
    wheelchairAccessible = aWheelchairAccessible;
    wasSet = true;
    return wasSet;
  }

  public boolean setHidden(Bool aHidden)
  {
    boolean wasSet = false;
    hidden = aHidden;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public Address getAddress()
  {
    return address;
  }

  /**
   * If Landlords want a different payment plan, they can specify the specifics in the description
   */
  public int getMonthlyPrice()
  {
    return monthlyPrice;
  }

  public float getPropertyRating()
  {
    return propertyRating;
  }

  public int getBedrooms()
  {
    return bedrooms;
  }

  public int getBathrooms()
  {
    return bathrooms;
  }

  public PropertyType getPropertyType()
  {
    return propertyType;
  }

  public int getSquareFootage()
  {
    return squareFootage;
  }

  public Bool getWheelchairAccessible()
  {
    return wheelchairAccessible;
  }

  public Bool getHidden()
  {
    return hidden;
  }
  /* Code from template association_GetMany */
  public Image getPropertyImage(int index)
  {
    Image aPropertyImage = propertyImages.get(index);
    return aPropertyImage;
  }

  public List<Image> getPropertyImages()
  {
    List<Image> newPropertyImages = Collections.unmodifiableList(propertyImages);
    return newPropertyImages;
  }

  public int numberOfPropertyImages()
  {
    int number = propertyImages.size();
    return number;
  }

  public boolean hasPropertyImages()
  {
    boolean has = propertyImages.size() > 0;
    return has;
  }

  public int indexOfPropertyImage(Image aPropertyImage)
  {
    int index = propertyImages.indexOf(aPropertyImage);
    return index;
  }
  /* Code from template association_GetOne */
  public Landlord getPoster()
  {
    return poster;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPropertyImages()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPropertyImages()
  {
    return 10;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addPropertyImage(Image aPropertyImage)
  {
    boolean wasAdded = false;
    if (propertyImages.contains(aPropertyImage)) { return false; }
    if (numberOfPropertyImages() < maximumNumberOfPropertyImages())
    {
      propertyImages.add(aPropertyImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removePropertyImage(Image aPropertyImage)
  {
    boolean wasRemoved = false;
    if (propertyImages.contains(aPropertyImage))
    {
      propertyImages.remove(aPropertyImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setPropertyImages(Image... newPropertyImages)
  {
    boolean wasSet = false;
    ArrayList<Image> verifiedPropertyImages = new ArrayList<Image>();
    for (Image aPropertyImage : newPropertyImages)
    {
      if (verifiedPropertyImages.contains(aPropertyImage))
      {
        continue;
      }
      verifiedPropertyImages.add(aPropertyImage);
    }

    if (verifiedPropertyImages.size() != newPropertyImages.length || verifiedPropertyImages.size() > maximumNumberOfPropertyImages())
    {
      return wasSet;
    }

    propertyImages.clear();
    propertyImages.addAll(verifiedPropertyImages);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPropertyImageAt(Image aPropertyImage, int index)
  {  
    boolean wasAdded = false;
    if(addPropertyImage(aPropertyImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPropertyImages()) { index = numberOfPropertyImages() - 1; }
      propertyImages.remove(aPropertyImage);
      propertyImages.add(index, aPropertyImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePropertyImageAt(Image aPropertyImage, int index)
  {
    boolean wasAdded = false;
    if(propertyImages.contains(aPropertyImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPropertyImages()) { index = numberOfPropertyImages() - 1; }
      propertyImages.remove(aPropertyImage);
      propertyImages.add(index, aPropertyImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPropertyImageAt(aPropertyImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPoster(Landlord aPoster)
  {
    boolean wasSet = false;
    if (aPoster == null)
    {
      return wasSet;
    }

    Landlord existingPoster = poster;
    poster = aPoster;
    if (existingPoster != null && !existingPoster.equals(aPoster))
    {
      existingPoster.removeProperty(this);
    }
    poster.addProperty(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    propertyImages.clear();
    Landlord placeholderPoster = poster;
    this.poster = null;
    if(placeholderPoster != null)
    {
      placeholderPoster.removeProperty(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "," +
            "monthlyPrice" + ":" + getMonthlyPrice()+ "," +
            "propertyRating" + ":" + getPropertyRating()+ "," +
            "bedrooms" + ":" + getBedrooms()+ "," +
            "bathrooms" + ":" + getBathrooms()+ "," +
            "squareFootage" + ":" + getSquareFootage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "address" + "=" + (getAddress() != null ? !getAddress().equals(this)  ? getAddress().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "propertyType" + "=" + (getPropertyType() != null ? !getPropertyType().equals(this)  ? getPropertyType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wheelchairAccessible" + "=" + (getWheelchairAccessible() != null ? !getWheelchairAccessible().equals(this)  ? getWheelchairAccessible().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "hidden" + "=" + (getHidden() != null ? !getHidden().equals(this)  ? getHidden().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "poster = "+(getPoster()!=null?Integer.toHexString(System.identityHashCode(getPoster())):"null");
  }
}



//%% NEW FILE Sublet BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 53 "model.ump"
// line 120 "model.ump"
public class Sublet extends Listing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Sublet Associations
  private Student subletter;
  private List<Image> subletImages;
  private Landlord supervisingLandlord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Sublet(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Bool aWheelchairAccessible, Bool aHidden, Landlord aPoster, Student aSubletter, Landlord aSupervisingLandlord)
  {
    super(aTitle, aDescription, aAddress, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, aPoster);
    boolean didAddSubletter = setSubletter(aSubletter);
    if (!didAddSubletter)
    {
      throw new RuntimeException("Unable to create housing due to subletter. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    subletImages = new ArrayList<Image>();
    boolean didAddSupervisingLandlord = setSupervisingLandlord(aSupervisingLandlord);
    if (!didAddSupervisingLandlord)
    {
      throw new RuntimeException("Unable to create propertySublet due to supervisingLandlord. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Student getSubletter()
  {
    return subletter;
  }
  /* Code from template association_GetMany */
  public Image getSubletImage(int index)
  {
    Image aSubletImage = subletImages.get(index);
    return aSubletImage;
  }

  public List<Image> getSubletImages()
  {
    List<Image> newSubletImages = Collections.unmodifiableList(subletImages);
    return newSubletImages;
  }

  public int numberOfSubletImages()
  {
    int number = subletImages.size();
    return number;
  }

  public boolean hasSubletImages()
  {
    boolean has = subletImages.size() > 0;
    return has;
  }

  public int indexOfSubletImage(Image aSubletImage)
  {
    int index = subletImages.indexOf(aSubletImage);
    return index;
  }
  /* Code from template association_GetOne */
  public Landlord getSupervisingLandlord()
  {
    return supervisingLandlord;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSubletter(Student aSubletter)
  {
    boolean wasSet = false;
    if (aSubletter == null)
    {
      return wasSet;
    }

    Student existingSubletter = subletter;
    subletter = aSubletter;
    if (existingSubletter != null && !existingSubletter.equals(aSubletter))
    {
      existingSubletter.removeHousing(this);
    }
    subletter.addHousing(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSubletImages()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfSubletImages()
  {
    return 10;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addSubletImage(Image aSubletImage)
  {
    boolean wasAdded = false;
    if (subletImages.contains(aSubletImage)) { return false; }
    if (numberOfSubletImages() < maximumNumberOfSubletImages())
    {
      subletImages.add(aSubletImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeSubletImage(Image aSubletImage)
  {
    boolean wasRemoved = false;
    if (subletImages.contains(aSubletImage))
    {
      subletImages.remove(aSubletImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setSubletImages(Image... newSubletImages)
  {
    boolean wasSet = false;
    ArrayList<Image> verifiedSubletImages = new ArrayList<Image>();
    for (Image aSubletImage : newSubletImages)
    {
      if (verifiedSubletImages.contains(aSubletImage))
      {
        continue;
      }
      verifiedSubletImages.add(aSubletImage);
    }

    if (verifiedSubletImages.size() != newSubletImages.length || verifiedSubletImages.size() > maximumNumberOfSubletImages())
    {
      return wasSet;
    }

    subletImages.clear();
    subletImages.addAll(verifiedSubletImages);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubletImageAt(Image aSubletImage, int index)
  {  
    boolean wasAdded = false;
    if(addSubletImage(aSubletImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletImages()) { index = numberOfSubletImages() - 1; }
      subletImages.remove(aSubletImage);
      subletImages.add(index, aSubletImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubletImageAt(Image aSubletImage, int index)
  {
    boolean wasAdded = false;
    if(subletImages.contains(aSubletImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletImages()) { index = numberOfSubletImages() - 1; }
      subletImages.remove(aSubletImage);
      subletImages.add(index, aSubletImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubletImageAt(aSubletImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSupervisingLandlord(Landlord aSupervisingLandlord)
  {
    boolean wasSet = false;
    if (aSupervisingLandlord == null)
    {
      return wasSet;
    }

    Landlord existingSupervisingLandlord = supervisingLandlord;
    supervisingLandlord = aSupervisingLandlord;
    if (existingSupervisingLandlord != null && !existingSupervisingLandlord.equals(aSupervisingLandlord))
    {
      existingSupervisingLandlord.removePropertySublet(this);
    }
    supervisingLandlord.addPropertySublet(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Student placeholderSubletter = subletter;
    this.subletter = null;
    if(placeholderSubletter != null)
    {
      placeholderSubletter.removeHousing(this);
    }
    subletImages.clear();
    Landlord placeholderSupervisingLandlord = supervisingLandlord;
    this.supervisingLandlord = null;
    if(placeholderSupervisingLandlord != null)
    {
      placeholderSupervisingLandlord.removePropertySublet(this);
    }
    super.delete();
  }

}



//%% NEW FILE Student BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 16 "model.ump"
// line 87 "model.ump"
public class Student extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Associations
  private List<Listing> favoritedListings;
  private List<Sublet> housing;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating)
  {
    super(aId, aUsername, aEmail, aPassword, aStatus, aRating);
    favoritedListings = new ArrayList<Listing>();
    housing = new ArrayList<Sublet>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Listing getFavoritedListing(int index)
  {
    Listing aFavoritedListing = favoritedListings.get(index);
    return aFavoritedListing;
  }

  public List<Listing> getFavoritedListings()
  {
    List<Listing> newFavoritedListings = Collections.unmodifiableList(favoritedListings);
    return newFavoritedListings;
  }

  public int numberOfFavoritedListings()
  {
    int number = favoritedListings.size();
    return number;
  }

  public boolean hasFavoritedListings()
  {
    boolean has = favoritedListings.size() > 0;
    return has;
  }

  public int indexOfFavoritedListing(Listing aFavoritedListing)
  {
    int index = favoritedListings.indexOf(aFavoritedListing);
    return index;
  }
  /* Code from template association_GetMany */
  public Sublet getHousing(int index)
  {
    Sublet aHousing = housing.get(index);
    return aHousing;
  }

  public List<Sublet> getHousing()
  {
    List<Sublet> newHousing = Collections.unmodifiableList(housing);
    return newHousing;
  }

  public int numberOfHousing()
  {
    int number = housing.size();
    return number;
  }

  public boolean hasHousing()
  {
    boolean has = housing.size() > 0;
    return has;
  }

  public int indexOfHousing(Sublet aHousing)
  {
    int index = housing.indexOf(aHousing);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFavoritedListings()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addFavoritedListing(Listing aFavoritedListing)
  {
    boolean wasAdded = false;
    if (favoritedListings.contains(aFavoritedListing)) { return false; }
    favoritedListings.add(aFavoritedListing);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFavoritedListing(Listing aFavoritedListing)
  {
    boolean wasRemoved = false;
    if (favoritedListings.contains(aFavoritedListing))
    {
      favoritedListings.remove(aFavoritedListing);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFavoritedListingAt(Listing aFavoritedListing, int index)
  {  
    boolean wasAdded = false;
    if(addFavoritedListing(aFavoritedListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFavoritedListings()) { index = numberOfFavoritedListings() - 1; }
      favoritedListings.remove(aFavoritedListing);
      favoritedListings.add(index, aFavoritedListing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFavoritedListingAt(Listing aFavoritedListing, int index)
  {
    boolean wasAdded = false;
    if(favoritedListings.contains(aFavoritedListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFavoritedListings()) { index = numberOfFavoritedListings() - 1; }
      favoritedListings.remove(aFavoritedListing);
      favoritedListings.add(index, aFavoritedListing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFavoritedListingAt(aFavoritedListing, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHousing()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Sublet addHousing(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Bool aWheelchairAccessible, Bool aHidden, Landlord aPoster, Landlord aSupervisingLandlord)
  {
    return new Sublet(aTitle, aDescription, aAddress, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, aPoster, this, aSupervisingLandlord);
  }

  public boolean addHousing(Sublet aHousing)
  {
    boolean wasAdded = false;
    if (housing.contains(aHousing)) { return false; }
    Student existingSubletter = aHousing.getSubletter();
    boolean isNewSubletter = existingSubletter != null && !this.equals(existingSubletter);
    if (isNewSubletter)
    {
      aHousing.setSubletter(this);
    }
    else
    {
      housing.add(aHousing);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHousing(Sublet aHousing)
  {
    boolean wasRemoved = false;
    //Unable to remove aHousing, as it must always have a subletter
    if (!this.equals(aHousing.getSubletter()))
    {
      housing.remove(aHousing);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHousingAt(Sublet aHousing, int index)
  {  
    boolean wasAdded = false;
    if(addHousing(aHousing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHousing()) { index = numberOfHousing() - 1; }
      housing.remove(aHousing);
      housing.add(index, aHousing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHousingAt(Sublet aHousing, int index)
  {
    boolean wasAdded = false;
    if(housing.contains(aHousing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHousing()) { index = numberOfHousing() - 1; }
      housing.remove(aHousing);
      housing.add(index, aHousing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHousingAt(aHousing, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    favoritedListings.clear();
    for(int i=housing.size(); i > 0; i--)
    {
      Sublet aHousing = housing.get(i - 1);
      aHousing.delete();
    }
    super.delete();
  }

}



//%% NEW FILE Image BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



/**
 * Don't implement image uploads
 */
// line 73 "model.ump"
// line 115 "model.ump"
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Attributes
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Image(String aUrl)
  {
    url = aUrl;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
  }

  public String getUrl()
  {
    return url;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "url" + ":" + getUrl()+ "]";
  }
}



//%% NEW FILE Landlord BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 24 "model.ump"
// line 93 "model.ump"
public class Landlord extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Landlord Attributes
  private int phoneNumber;

  //Landlord Associations
  private List<Listing> properties;
  private List<Sublet> propertySublet;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Landlord(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating, int aPhoneNumber)
  {
    super(aId, aUsername, aEmail, aPassword, aStatus, aRating);
    phoneNumber = aPhoneNumber;
    properties = new ArrayList<Listing>();
    propertySublet = new ArrayList<Sublet>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public Listing getProperty(int index)
  {
    Listing aProperty = properties.get(index);
    return aProperty;
  }

  public List<Listing> getProperties()
  {
    List<Listing> newProperties = Collections.unmodifiableList(properties);
    return newProperties;
  }

  public int numberOfProperties()
  {
    int number = properties.size();
    return number;
  }

  public boolean hasProperties()
  {
    boolean has = properties.size() > 0;
    return has;
  }

  public int indexOfProperty(Listing aProperty)
  {
    int index = properties.indexOf(aProperty);
    return index;
  }
  /* Code from template association_GetMany */
  public Sublet getPropertySublet(int index)
  {
    Sublet aPropertySublet = propertySublet.get(index);
    return aPropertySublet;
  }

  public List<Sublet> getPropertySublet()
  {
    List<Sublet> newPropertySublet = Collections.unmodifiableList(propertySublet);
    return newPropertySublet;
  }

  public int numberOfPropertySublet()
  {
    int number = propertySublet.size();
    return number;
  }

  public boolean hasPropertySublet()
  {
    boolean has = propertySublet.size() > 0;
    return has;
  }

  public int indexOfPropertySublet(Sublet aPropertySublet)
  {
    int index = propertySublet.indexOf(aPropertySublet);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProperties()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Listing addProperty(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, Listing.PropertyType aPropertyType, int aSquareFootage, Bool aWheelchairAccessible, Bool aHidden)
  {
    return new Listing(aTitle, aDescription, aAddress, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, this);
  }

  public boolean addProperty(Listing aProperty)
  {
    boolean wasAdded = false;
    if (properties.contains(aProperty)) { return false; }
    Landlord existingPoster = aProperty.getPoster();
    boolean isNewPoster = existingPoster != null && !this.equals(existingPoster);
    if (isNewPoster)
    {
      aProperty.setPoster(this);
    }
    else
    {
      properties.add(aProperty);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProperty(Listing aProperty)
  {
    boolean wasRemoved = false;
    //Unable to remove aProperty, as it must always have a poster
    if (!this.equals(aProperty.getPoster()))
    {
      properties.remove(aProperty);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPropertyAt(Listing aProperty, int index)
  {  
    boolean wasAdded = false;
    if(addProperty(aProperty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProperties()) { index = numberOfProperties() - 1; }
      properties.remove(aProperty);
      properties.add(index, aProperty);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePropertyAt(Listing aProperty, int index)
  {
    boolean wasAdded = false;
    if(properties.contains(aProperty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProperties()) { index = numberOfProperties() - 1; }
      properties.remove(aProperty);
      properties.add(index, aProperty);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPropertyAt(aProperty, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPropertySublet()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Sublet addPropertySublet(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Bool aWheelchairAccessible, Bool aHidden, Landlord aPoster, Student aSubletter)
  {
    return new Sublet(aTitle, aDescription, aAddress, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, aPoster, aSubletter, this);
  }

  public boolean addPropertySublet(Sublet aPropertySublet)
  {
    boolean wasAdded = false;
    if (propertySublet.contains(aPropertySublet)) { return false; }
    Landlord existingSupervisingLandlord = aPropertySublet.getSupervisingLandlord();
    boolean isNewSupervisingLandlord = existingSupervisingLandlord != null && !this.equals(existingSupervisingLandlord);
    if (isNewSupervisingLandlord)
    {
      aPropertySublet.setSupervisingLandlord(this);
    }
    else
    {
      propertySublet.add(aPropertySublet);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePropertySublet(Sublet aPropertySublet)
  {
    boolean wasRemoved = false;
    //Unable to remove aPropertySublet, as it must always have a supervisingLandlord
    if (!this.equals(aPropertySublet.getSupervisingLandlord()))
    {
      propertySublet.remove(aPropertySublet);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPropertySubletAt(Sublet aPropertySublet, int index)
  {  
    boolean wasAdded = false;
    if(addPropertySublet(aPropertySublet))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPropertySublet()) { index = numberOfPropertySublet() - 1; }
      propertySublet.remove(aPropertySublet);
      propertySublet.add(index, aPropertySublet);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePropertySubletAt(Sublet aPropertySublet, int index)
  {
    boolean wasAdded = false;
    if(propertySublet.contains(aPropertySublet))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPropertySublet()) { index = numberOfPropertySublet() - 1; }
      propertySublet.remove(aPropertySublet);
      propertySublet.add(index, aPropertySublet);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPropertySubletAt(aPropertySublet, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=properties.size(); i > 0; i--)
    {
      Listing aProperty = properties.get(i - 1);
      aProperty.delete();
    }
    for(int i=propertySublet.size(); i > 0; i--)
    {
      Sublet aPropertySublet = propertySublet.get(i - 1);
      aPropertySublet.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}
