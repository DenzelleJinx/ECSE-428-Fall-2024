package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.*;

// line 35 "Umplesrc"
@Entity
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
  @Enumerated(EnumType.STRING)
  private PropertyType propertyType;
  private int squareFootage;
  private Boolean wheelchairAccessible;
  private Boolean hidden;
  private Boolean smokingAllowed;

  //Listing Associations
  private List<Image> propertyImages;
  private Landlord poster;
  private Amenities amenitiesOffered;
  private Utilities utilitiesCosts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Boolean aWheelchairAccessible, Boolean aHidden, Boolean aSmokingAllowed, Landlord aPoster, Amenities aAmenitiesOffered)
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
    smokingAllowed = aSmokingAllowed;
    propertyImages = new ArrayList<Image>();
    boolean didAddPoster = setPoster(aPoster);
    if (!didAddPoster)
    {
      throw new RuntimeException("Unable to create property due to poster. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setAmenitiesOffered(aAmenitiesOffered))
    {
      throw new RuntimeException("Unable to create Listing due to aAmenitiesOffered. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setWheelchairAccessible(Boolean aWheelchairAccessible)
  {
    boolean wasSet = false;
    wheelchairAccessible = aWheelchairAccessible;
    wasSet = true;
    return wasSet;
  }

  public boolean setHidden(Boolean aHidden)
  {
    boolean wasSet = false;
    hidden = aHidden;
    wasSet = true;
    return wasSet;
  }

  public boolean setSmokingAllowed(Boolean aSmokingAllowed)
  {
    boolean wasSet = false;
    smokingAllowed = aSmokingAllowed;
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

  @OneToOne(optional = true)
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

  public Boolean getWheelchairAccessible()
  {
    return wheelchairAccessible;
  }

  public Boolean getHidden()
  {
    return hidden;
  }

  public Boolean getSmokingAllowed()
  {
    return smokingAllowed;
  }
  /* Code from template association_GetMany */
  public Image getPropertyImage(int index)
  {
    Image aPropertyImage = propertyImages.get(index);
    return aPropertyImage;
  }

  @OneToMany()
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
  @ManyToOne(optional = false)
  public Landlord getPoster()
  {
    return poster;
  }
  /* Code from template association_GetOne */
  @OneToOne(optional = true)
  public Amenities getAmenitiesOffered()
  {
    return amenitiesOffered;
  }
  /* Code from template association_GetOne */
  @OneToOne(optional = true)
  public Utilities getUtilitiesCosts()
  {
    return utilitiesCosts;
  }

  public boolean hasUtilitiesCosts()
  {
    boolean has = utilitiesCosts != null;
    return has;
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setAmenitiesOffered(Amenities aNewAmenitiesOffered)
  {
    boolean wasSet = false;
    if (aNewAmenitiesOffered != null)
    {
      amenitiesOffered = aNewAmenitiesOffered;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setUtilitiesCosts(Utilities aNewUtilitiesCosts)
  {
    boolean wasSet = false;
    utilitiesCosts = aNewUtilitiesCosts;
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
    amenitiesOffered = null;
    utilitiesCosts = null;
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
            "  " + "smokingAllowed" + "=" + (getSmokingAllowed() != null ? !getSmokingAllowed().equals(this)  ? getSmokingAllowed().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "poster = "+(getPoster()!=null?Integer.toHexString(System.identityHashCode(getPoster())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "amenitiesOffered = "+(getAmenitiesOffered()!=null?Integer.toHexString(System.identityHashCode(getAmenitiesOffered())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "utilitiesCosts = "+(getUtilitiesCosts()!=null?Integer.toHexString(System.identityHashCode(getUtilitiesCosts())):"null");
  }
}