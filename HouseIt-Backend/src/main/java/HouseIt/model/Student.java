package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

// line 16 "model.ump"
// line 113 "model.ump"
@Entity
public class Student extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Listing> favoritedListings;
  @OneToMany(fetch = FetchType.EAGER)
  private List<Listing> subletListings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student()
  {
    favoritedListings = new ArrayList<Listing>();
    subletListings = new ArrayList<Listing>();
  }

  public Student(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating, List<Notification> aNotifications)
  {
    super(aUsername, aEmail, aPassword, aStatus, aRating, aNotifications);
    favoritedListings = new ArrayList<Listing>();
    subletListings = new ArrayList<Listing>();
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
  public Listing getSubletListing(int index)
  {
    Listing aSubletListing = subletListings.get(index);
    return aSubletListing;
  }

  public List<Listing> getSubletListings()
  {
    List<Listing> newSubletListings = Collections.unmodifiableList(subletListings);
    return newSubletListings;
  }

  public int numberOfSubletListings()
  {
    int number = subletListings.size();
    return number;
  }

  public boolean hasSubletListings()
  {
    boolean has = subletListings.size() > 0;
    return has;
  }

  public int indexOfSubletListing(Listing aSubletListing)
  {
    int index = subletListings.indexOf(aSubletListing);
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
  public static int minimumNumberOfSubletListings()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addSubletListing(Listing aSubletListing)
  {
    boolean wasAdded = false;
    if (subletListings.contains(aSubletListing)) { return false; }
    subletListings.add(aSubletListing);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubletListing(Listing aSubletListing)
  {
    boolean wasRemoved = false;
    if (subletListings.contains(aSubletListing))
    {
      subletListings.remove(aSubletListing);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubletListingAt(Listing aSubletListing, int index)
  {  
    boolean wasAdded = false;
    if(addSubletListing(aSubletListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletListings()) { index = numberOfSubletListings() - 1; }
      subletListings.remove(aSubletListing);
      subletListings.add(index, aSubletListing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubletListingAt(Listing aSubletListing, int index)
  {
    boolean wasAdded = false;
    if(subletListings.contains(aSubletListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletListings()) { index = numberOfSubletListings() - 1; }
      subletListings.remove(aSubletListing);
      subletListings.add(index, aSubletListing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubletListingAt(aSubletListing, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    favoritedListings.clear();
    subletListings.clear();
    super.delete();
  }

  @Override 
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    if (favoritedListings.size() != student.favoritedListings.size()) {
      return false;
    }
    for (int i = 0; i < favoritedListings.size(); i++) {
      if (!favoritedListings.get(i).equals(student.favoritedListings.get(i))) {
        return false;
      }
    }
    if (subletListings.size() != student.subletListings.size()) {
      return false;
    }
    for (int i = 0; i < subletListings.size(); i++) {
      if (!subletListings.get(i).equals(student.subletListings.get(i))) {
        return false;
      }
    }
    return super.equals(o);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(super.hashCode(), favoritedListings, subletListings);
  }

}