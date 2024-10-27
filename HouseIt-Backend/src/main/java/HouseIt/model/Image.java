package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Don't implement image uploads
 */
// line 70 "model.ump"
// line 130 "model.ump"
@Entity
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Attributes
  @Id
  @GeneratedValue
  private int id;
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Image() {}
  // do not use this constructor
  public Image(int aId, String aUrl)
  {
    id = aId;
    url = aUrl;
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

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getUrl()
  {
    return url;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "url" + ":" + getUrl()+ "]";
  }
}