package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.*;

// line 15 "Umplesrc"
@Entity
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

  @OneToMany()
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

  @OneToMany(mappedBy = "subletter")
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
  public Sublet addHousing(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, Listing.PropertyType aPropertyType, int aSquareFootage, Boolean aWheelchairAccessible, Boolean aHidden, Landlord aPoster, Landlord aSupervisingLandlord)
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