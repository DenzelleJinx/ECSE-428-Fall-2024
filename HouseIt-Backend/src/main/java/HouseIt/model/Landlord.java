package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

// line 25 "model.ump"
// line 102 "model.ump"
@Entity
public class Landlord extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Landlord Attributes
  private String phoneNumber;

  //Landlord Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Listing> properties;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Landlord()
  {
    super();
    properties = new ArrayList<Listing>();
  }
  public Landlord(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating, String aPhoneNumber)
  {
    super(aUsername, aEmail, aPassword, aStatus, aRating);
    phoneNumber = aPhoneNumber;
    properties = new ArrayList<Listing>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getPhoneNumber()
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProperties()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  // do not use
  public Listing addProperty(String aTitle, String aDescription, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, Listing.PropertyType aPropertyType, int aSquareFootage, boolean aWheelchairAccessible, boolean aHidden, boolean aSmokingAllowed, Address aAddress, Amenities aAmenitiesOffered)
  {
    return new Listing(aTitle, aDescription, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, aSmokingAllowed, aAddress, this, aAmenitiesOffered);
  }

  public boolean addProperty(Listing aProperty)
  {
    boolean wasAdded = false;
    if (properties.contains(aProperty)) { return false; }
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
    properties.remove(aProperty);
    wasRemoved = true;
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

  public void delete()
  {
    for(int i=properties.size(); i > 0; i--)
    {
      Listing aProperty = properties.get(i - 1);
      aProperty.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Landlord)) return false;
    Landlord landlord = (Landlord) o;
    if (properties.size() != landlord.properties.size()) {
      return false;
    }
    for (int i = 0; i < properties.size(); i++) {
      if (!properties.get(i).equals(landlord.properties.get(i))) {
        return false;
      }
    }
    return phoneNumber.equals(landlord.phoneNumber) && 
            super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), phoneNumber, properties);
  }
}