package HouseIt.app.model;

import javax.persistence.Entity;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



/**
 * Optionally address is just a string
 * Could use some Google Maps integration or something
 */
// line 62 "Umplesrc"
@Entity
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String city;
  private String postalCode;
  private String street;
  private int streetNumber;
  private int apartmentNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aCity, String aPostalCode, String aStreet, int aStreetNumber, int aApartmentNumber)
  {
    city = aCity;
    postalCode = aPostalCode;
    street = aStreet;
    streetNumber = aStreetNumber;
    apartmentNumber = aApartmentNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setStreetNumber(int aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setApartmentNumber(int aApartmentNumber)
  {
    boolean wasSet = false;
    apartmentNumber = aApartmentNumber;
    wasSet = true;
    return wasSet;
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

  public int getStreetNumber()
  {
    return streetNumber;
  }

  public int getApartmentNumber()
  {
    return apartmentNumber;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "city" + ":" + getCity()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "street" + ":" + getStreet()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "apartmentNumber" + ":" + getApartmentNumber()+ "]";
  }
}