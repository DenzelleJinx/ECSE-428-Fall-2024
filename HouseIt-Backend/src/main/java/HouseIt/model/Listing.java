package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

// line 37 "model.ump"
// line 135 "model.ump"
@Entity
public class Listing
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PropertyType { STUDIO, DORM, CONDO, APARTMENT, HOUSE }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Listing Attributes
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String description;
  private int monthlyPrice;
  private float propertyRating;
  private int bedrooms;
  private int bathrooms;
  private PropertyType propertyType;
  private int squareFootage;
  private boolean wheelchairAccessible;
  private boolean hidden;
  private boolean smokingAllowed;

  //Listing Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Image> propertyImages;
  @OneToOne
  private Address address;
  @OneToOne(fetch = FetchType.EAGER)
  private Amenities amenitiesOffered;
  @OneToOne(fetch = FetchType.EAGER)
  private Utilities utilitiesCosts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing()
  {
    propertyImages = new ArrayList<Image>();
  }

  public Listing(String aTitle, String aDescription, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, boolean aWheelchairAccessible, boolean aHidden, boolean aSmokingAllowed, Address aAddress, Landlord aPoster, Amenities aAmenitiesOffered)
  {
    title = aTitle;
    description = aDescription;
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
    if (!setAddress(aAddress))
    {
      throw new RuntimeException("Unable to create Listing due to aAddress. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setWheelchairAccessible(boolean aWheelchairAccessible)
  {
    boolean wasSet = false;
    wheelchairAccessible = aWheelchairAccessible;
    wasSet = true;
    return wasSet;
  }

  public boolean setHidden(boolean aHidden)
  {
    boolean wasSet = false;
    hidden = aHidden;
    wasSet = true;
    return wasSet;
  }

  public boolean setSmokingAllowed(boolean aSmokingAllowed)
  {
    boolean wasSet = false;
    smokingAllowed = aSmokingAllowed;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  /**
   * Address address;
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

  public boolean getWheelchairAccessible()
  {
    return wheelchairAccessible;
  }

  public boolean getHidden()
  {
    return hidden;
  }

  public boolean getSmokingAllowed()
  {
    return smokingAllowed;
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
  public Address getAddress()
  {
    return address;
  }
  
  /* Code from template association_GetOne */
  public Amenities getAmenitiesOffered()
  {
    return amenitiesOffered;
  }
  /* Code from template association_GetOne */
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setAddress(Address aNewAddress)
  {
    boolean wasSet = false;
    if (aNewAddress != null)
    {
      address = aNewAddress;
      wasSet = true;
    }
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
    address = null;
    amenitiesOffered = null;
    utilitiesCosts = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "," +
            "monthlyPrice" + ":" + getMonthlyPrice()+ "," +
            "propertyRating" + ":" + getPropertyRating()+ "," +
            "bedrooms" + ":" + getBedrooms()+ "," +
            "bathrooms" + ":" + getBathrooms()+ "," +
            "squareFootage" + ":" + getSquareFootage()+ "," +
            "wheelchairAccessible" + ":" + getWheelchairAccessible()+ "," +
            "hidden" + ":" + getHidden()+ "," +
            "smokingAllowed" + ":" + getSmokingAllowed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "propertyType" + "=" + (getPropertyType() != null ? !getPropertyType().equals(this)  ? getPropertyType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "address = "+(getAddress()!=null?Integer.toHexString(System.identityHashCode(getAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "amenitiesOffered = "+(getAmenitiesOffered()!=null?Integer.toHexString(System.identityHashCode(getAmenitiesOffered())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "utilitiesCosts = "+(getUtilitiesCosts()!=null?Integer.toHexString(System.identityHashCode(getUtilitiesCosts())):"null");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Listing)) return false;
    Listing listing = (Listing) o;
    if (propertyImages.size() != listing.propertyImages.size()) {
      return false;
    }
    for (int i = 0; i < propertyImages.size(); i++) {
      if (!propertyImages.get(i).equals(listing.propertyImages.get(i))) {
        return false;
      }
    }
    if (hasUtilitiesCosts() != listing.hasUtilitiesCosts()) {
      return false;
    }
    if (hasUtilitiesCosts() && !getUtilitiesCosts().equals(listing.getUtilitiesCosts())) {
      return false;
    }
    return id == listing.id &&
            monthlyPrice == listing.monthlyPrice &&
            Float.compare(listing.propertyRating, propertyRating) == 0 &&
            bedrooms == listing.bedrooms &&
            bathrooms == listing.bathrooms &&
            squareFootage == listing.squareFootage &&
            wheelchairAccessible == listing.wheelchairAccessible &&
            hidden == listing.hidden &&
            smokingAllowed == listing.smokingAllowed &&
            Objects.equals(title, listing.title) &&
            Objects.equals(description, listing.description) &&
            propertyType == listing.propertyType &&
            Objects.equals(address, listing.address) &&
            Objects.equals(amenitiesOffered, listing.amenitiesOffered);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, monthlyPrice, propertyRating, bedrooms, bathrooms, propertyType, squareFootage, wheelchairAccessible, hidden, smokingAllowed, propertyImages.hashCode(), address.hashCode(), amenitiesOffered.hashCode(), utilitiesCosts.hashCode());
  }
}