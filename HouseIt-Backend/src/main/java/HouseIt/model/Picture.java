package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

// line 50 "model.ump"
// line 109 "model.ump"
@Entity
public class Picture
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Picture Attributes
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Picture() {}

  public Picture(int aId, String aTitle, String aUrl)
  {
    id = aId;
    title = aTitle;
    url = aUrl;
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

  public String getTitle()
  {
    return title;
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
            "title" + ":" + getTitle()+ "," +
            "url" + ":" + getUrl()+ "]";
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, title, url);
  }
}