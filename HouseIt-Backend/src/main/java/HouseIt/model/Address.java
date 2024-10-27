package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

/**
 * Optionally address is just a string
 * Could use some Google Maps integration or something
 */
// line 59 "model.ump"
// line 125 "model.ump"
@Entity
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  @Id
  @GeneratedValue
  private int id;
  private String city;
  private String postalCode;
  private String street;
  private String streetNumber;
  private String apartmentNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address() {}
  public Address(int aId, String aCity, String aPostalCode, String aStreet, String aStreetNumber, String aApartmentNumber)
  {
    id = aId;
    city = aCity;
    postalCode = aPostalCode;
    street = aStreet;
    streetNumber = aStreetNumber;
    apartmentNumber = aApartmentNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreet(String aStreet)
  {
    boolean wasSet = false;
    street = aStreet;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetNumber(String aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setApartmentNumber(String aApartmentNumber)
  {
    boolean wasSet = false;
    apartmentNumber = aApartmentNumber;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getCity()
  {
    return city;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getStreet()
  {
    return street;
  }

  public String getStreetNumber()
  {
    return streetNumber;
  }

  public String getApartmentNumber()
  {
    return apartmentNumber;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "city" + ":" + getCity()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "street" + ":" + getStreet()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "apartmentNumber" + ":" + getApartmentNumber()+ "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Address)) return false;
    Address address = (Address) o;
    return id == address.id &&
            Objects.equals(city, address.city) &&
            Objects.equals(postalCode, address.postalCode) &&
            Objects.equals(street, address.street) &&
            Objects.equals(streetNumber, address.streetNumber) &&
            Objects.equals(apartmentNumber, address.apartmentNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, city, postalCode, street, streetNumber, apartmentNumber);
  }
}