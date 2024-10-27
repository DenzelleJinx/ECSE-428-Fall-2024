package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.util.*;

// line 42 "model.ump"
// line 104 "model.ump"
@Entity
public class Posting
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Posting Attributes
  @Id
  @GeneratedValue
  private int id;
  private String address;
  private int bedrooms;
  private int bathrooms;
  private int floors;

  //Posting Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Picture> pictures;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Posting() {}

  public Posting(int aId, String aAddress, int aBedrooms, int aBathrooms, int aFloors)
  {
    id = aId;
    address = aAddress;
    bedrooms = aBedrooms;
    bathrooms = aBathrooms;
    floors = aFloors;
    pictures = new ArrayList<Picture>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
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

  public boolean setFloors(int aFloors)
  {
    boolean wasSet = false;
    floors = aFloors;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getAddress()
  {
    return address;
  }

  public int getBedrooms()
  {
    return bedrooms;
  }

  public int getBathrooms()
  {
    return bathrooms;
  }

  public int getFloors()
  {
    return floors;
  }
  /* Code from template association_GetMany */
  public Picture getPicture(int index)
  {
    Picture aPicture = pictures.get(index);
    return aPicture;
  }

  public List<Picture> getPictures()
  {
    List<Picture> newPictures = Collections.unmodifiableList(pictures);
    return newPictures;
  }

  public int numberOfPictures()
  {
    int number = pictures.size();
    return number;
  }

  public boolean hasPictures()
  {
    boolean has = pictures.size() > 0;
    return has;
  }

  public int indexOfPicture(Picture aPicture)
  {
    int index = pictures.indexOf(aPicture);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPictures()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPicture(Picture aPicture)
  {
    boolean wasAdded = false;
    if (pictures.contains(aPicture)) { return false; }
    pictures.add(aPicture);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePicture(Picture aPicture)
  {
    boolean wasRemoved = false;
    if (pictures.contains(aPicture))
    {
      pictures.remove(aPicture);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPictureAt(Picture aPicture, int index)
  {  
    boolean wasAdded = false;
    if(addPicture(aPicture))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPictures()) { index = numberOfPictures() - 1; }
      pictures.remove(aPicture);
      pictures.add(index, aPicture);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePictureAt(Picture aPicture, int index)
  {
    boolean wasAdded = false;
    if(pictures.contains(aPicture))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPictures()) { index = numberOfPictures() - 1; }
      pictures.remove(aPicture);
      pictures.add(index, aPicture);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPictureAt(aPicture, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    pictures.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "address" + ":" + getAddress()+ "," +
            "bedrooms" + ":" + getBedrooms()+ "," +
            "bathrooms" + ":" + getBathrooms()+ "," +
            "floors" + ":" + getFloors()+ "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, bedrooms, bathrooms, floors, pictures);
  }
}