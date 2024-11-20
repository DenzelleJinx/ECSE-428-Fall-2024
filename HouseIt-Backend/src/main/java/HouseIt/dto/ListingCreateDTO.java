package HouseIt.dto;

import java.util.*;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Image;
import HouseIt.model.Utilities;
import HouseIt.model.Listing.PropertyType;

/**
 * DTO for creating a listing
 */
public class ListingCreateDTO {
   
    private int landlordId;
    private String title;
    private String description;
    private int monthlyPrice;
    private int bedrooms;
    private int bathrooms;
    private PropertyType propertyType;
    private int squareFootage;
    private boolean wheelchairAccessible;
    private boolean smokingAllowed;
    private Address address ;
    private Amenities amenitiesOffered ;
    private Utilities utilitiesCosts ;
    private List<Image> propertyImages = new ArrayList<Image>();

    // Landlord ID
    public int getLandlordId() {
        return landlordId;
    }
    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    // Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Monthly Price
    public int getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    // Bedrooms
    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    // Bathrooms
    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    // Property Type
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    // Square Footage
    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    // Wheelchair Accessible
    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    // Smoking Allowed
    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    // Address
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    // Amenities Offered
    public Amenities getAmenitiesOffered() {
        return amenitiesOffered;
    }

    public void setAmenitiesOffered(Amenities amenitiesOffered) {
        this.amenitiesOffered = amenitiesOffered;
    }

    // Utilites Offered
    public Utilities getUtilitiesCosts()
    {
      return utilitiesCosts;
    }
  
    public boolean setUtilitiesCosts(Utilities aNewUtilitiesCosts)
    {
      boolean wasSet = false;
      utilitiesCosts = aNewUtilitiesCosts;
      wasSet = true;
      return wasSet;
    }

     /* Code from template association_GetMany */
 

     public List<Image> getPropertyImages() {
        List<Image> newPropertyImages = Collections.unmodifiableList(propertyImages);
        return newPropertyImages;
    }
    


  public static int maximumNumberOfPropertyImages()
  {
    return 10;
  }
 

  public boolean setPropertyImages( Image... newPropertyImages)
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

}

