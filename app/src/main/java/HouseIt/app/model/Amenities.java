package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;

/**
 * class Comment, would have associations many to 1 with students and landlords and listings and sublets
 */
// line 80 "Umplesrc"
@Entity
public class Amenities
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Amenities Attributes
  private Boolean gym;
  private Boolean laundry;
  private Boolean petsAllowed;
  private Boolean parking;
  private Boolean internetInclued;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Amenities(Boolean aGym, Boolean aLaundry, Boolean aPetsAllowed, Boolean aParking, Boolean aInternetInclued)
  {
    gym = aGym;
    laundry = aLaundry;
    petsAllowed = aPetsAllowed;
    parking = aParking;
    internetInclued = aInternetInclued;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGym(Boolean aGym)
  {
    boolean wasSet = false;
    gym = aGym;
    wasSet = true;
    return wasSet;
  }

  public boolean setLaundry(Boolean aLaundry)
  {
    boolean wasSet = false;
    laundry = aLaundry;
    wasSet = true;
    return wasSet;
  }

  public boolean setPetsAllowed(Boolean aPetsAllowed)
  {
    boolean wasSet = false;
    petsAllowed = aPetsAllowed;
    wasSet = true;
    return wasSet;
  }

  public boolean setParking(Boolean aParking)
  {
    boolean wasSet = false;
    parking = aParking;
    wasSet = true;
    return wasSet;
  }

  public boolean setInternetInclued(Boolean aInternetInclued)
  {
    boolean wasSet = false;
    internetInclued = aInternetInclued;
    wasSet = true;
    return wasSet;
  }

  public Boolean getGym()
  {
    return gym;
  }

  public Boolean getLaundry()
  {
    return laundry;
  }

  public Boolean getPetsAllowed()
  {
    return petsAllowed;
  }

  public Boolean getParking()
  {
    return parking;
  }

  public Boolean getInternetInclued()
  {
    return internetInclued;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gym" + "=" + (getGym() != null ? !getGym().equals(this)  ? getGym().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "laundry" + "=" + (getLaundry() != null ? !getLaundry().equals(this)  ? getLaundry().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "petsAllowed" + "=" + (getPetsAllowed() != null ? !getPetsAllowed().equals(this)  ? getPetsAllowed().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parking" + "=" + (getParking() != null ? !getParking().equals(this)  ? getParking().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "internetInclued" + "=" + (getInternetInclued() != null ? !getInternetInclued().equals(this)  ? getInternetInclued().toString().replaceAll("  ","    ") : "this" : "null");
  }
}