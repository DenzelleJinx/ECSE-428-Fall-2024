package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 35 "Umplesrc"
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
  private Boolean wheelchairAccessible;
  private Boolean hidden;

  //Listing Associations
  private List<Image> propertyImages;
  private Landlord poster;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Boolean aWheelchairAccessible, Boolean aHidden, Landlord aPoster)
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
    Boolean didAddPoster = setPoster(aPoster);
    if (!didAddPoster)
    {
      throw new RuntimeException("Unable to create property due to poster. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Boolean setTitle(String aTitle)
  {
    Boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public Boolean setDescription(String aDescription)
  {
    Boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public Boolean setAddress(Address aAddress)
  {
    Boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public Boolean setMonthlyPrice(int aMonthlyPrice)
  {
    Boolean wasSet = false;
    monthlyPrice = aMonthlyPrice;
    wasSet = true;
    return wasSet;
  }

  public Boolean setPropertyRating(float aPropertyRating)
  {
    Boolean wasSet = false;
    propertyRating = aPropertyRating;
    wasSet = true;
    return wasSet;
  }

  public Boolean setBedrooms(int aBedrooms)
  {
    Boolean wasSet = false;
    bedrooms = aBedrooms;
    wasSet = true;
    return wasSet;
  }

  public Boolean setBathrooms(int aBathrooms)
  {
    Boolean wasSet = false;
    bathrooms = aBathrooms;
    wasSet = true;
    return wasSet;
  }

  public Boolean setPropertyType(PropertyType aPropertyType)
  {
    Boolean wasSet = false;
    propertyType = aPropertyType;
    wasSet = true;
    return wasSet;
  }

  public Boolean setSquareFootage(int aSquareFootage)
  {
    Boolean wasSet = false;
    squareFootage = aSquareFootage;
    wasSet = true;
    return wasSet;
  }

  public Boolean setWheelchairAccessible(Boolean aWheelchairAccessible)
  {
    Boolean wasSet = false;
    wheelchairAccessible = aWheelchairAccessible;
    wasSet = true;
    return wasSet;
  }

  public Boolean setHidden(Boolean aHidden)
  {
    Boolean wasSet = false;
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

  public Boolean getWheelchairAccessible()
  {
    return wheelchairAccessible;
  }

  public Boolean getHidden()
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

  public Boolean hasPropertyImages()
  {
    Boolean has = propertyImages.size() > 0;
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
  public Boolean addPropertyImage(Image aPropertyImage)
  {
    Boolean wasAdded = false;
    if (propertyImages.contains(aPropertyImage)) { return false; }
    if (numberOfPropertyImages() < maximumNumberOfPropertyImages())
    {
      propertyImages.add(aPropertyImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public Boolean removePropertyImage(Image aPropertyImage)
  {
    Boolean wasRemoved = false;
    if (propertyImages.contains(aPropertyImage))
    {
      propertyImages.remove(aPropertyImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public Boolean setPropertyImages(Image... newPropertyImages)
  {
    Boolean wasSet = false;
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
  public Boolean addPropertyImageAt(Image aPropertyImage, int index)
  {  
    Boolean wasAdded = false;
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

  public Boolean addOrMovePropertyImageAt(Image aPropertyImage, int index)
  {
    Boolean wasAdded = false;
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
  public Boolean setPoster(Landlord aPoster)
  {
    Boolean wasSet = false;
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