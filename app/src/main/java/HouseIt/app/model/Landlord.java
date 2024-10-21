package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 23 "Umplesrc"
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
  public Listing addProperty(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, Listing.PropertyType aPropertyType, int aSquareFootage, boolean aWheelchairAccessible, boolean aHidden)
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
  public Sublet addPropertySublet(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, Listing.PropertyType aPropertyType, int aSquareFootage, boolean aWheelchairAccessible, boolean aHidden, Landlord aPoster, Student aSubletter)
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