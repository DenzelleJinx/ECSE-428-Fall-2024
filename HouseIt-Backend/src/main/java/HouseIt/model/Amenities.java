package HouseIt.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



/**
 * class Comment, would have associations many to 1 with students and landlords and listings and sublets
 */
// line 78 "model.ump"
// line 142 "model.ump"
@Entity
public class Amenities
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Amenities Attributes
  @Id
  @GeneratedValue
  private int id;
  private boolean gym;
  private boolean laundry;
  private boolean petsAllowed;
  private boolean parking;
  private boolean internetIncluded;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Amenities() {}

  public Amenities(boolean aGym, boolean aLaundry, boolean aPetsAllowed, boolean aParking, boolean aInternetIncluded)
  {
    gym = aGym;
    laundry = aLaundry;
    petsAllowed = aPetsAllowed;
    parking = aParking;
    internetIncluded = aInternetIncluded;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGym(boolean aGym)
  {
    boolean wasSet = false;
    gym = aGym;
    wasSet = true;
    return wasSet;
  }

  public boolean setLaundry(boolean aLaundry)
  {
    boolean wasSet = false;
    laundry = aLaundry;
    wasSet = true;
    return wasSet;
  }

  public boolean setPetsAllowed(boolean aPetsAllowed)
  {
    boolean wasSet = false;
    petsAllowed = aPetsAllowed;
    wasSet = true;
    return wasSet;
  }

  public boolean setParking(boolean aParking)
  {
    boolean wasSet = false;
    parking = aParking;
    wasSet = true;
    return wasSet;
  }

  public boolean setInternetIncluded(boolean aInternetIncluded)
  {
    boolean wasSet = false;
    internetIncluded = aInternetIncluded;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public boolean getGym()
  {
    return gym;
  }

  public boolean getLaundry()
  {
    return laundry;
  }

  public boolean getPetsAllowed()
  {
    return petsAllowed;
  }

  public boolean getParking()
  {
    return parking;
  }

  public boolean getInternetIncluded()
  {
    return internetIncluded;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "gym" + ":" + getGym()+ "," +
            "laundry" + ":" + getLaundry()+ "," +
            "petsAllowed" + ":" + getPetsAllowed()+ "," +
            "parking" + ":" + getParking()+ "," +
            "internetIncluded" + ":" + getInternetIncluded()+ "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Amenities)) return false;
    Amenities amenities = (Amenities) o;
    return gym == amenities.gym &&
            laundry == amenities.laundry &&
            petsAllowed == amenities.petsAllowed &&
            parking == amenities.parking &&
            internetIncluded == amenities.internetIncluded &&
            id == amenities.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, gym, laundry, petsAllowed, parking, internetIncluded);
  }
}