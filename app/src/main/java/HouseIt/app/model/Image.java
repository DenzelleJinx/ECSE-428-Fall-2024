package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



/**
 * Don't implement image uploads
 */
// line 72 "Umplesrc"
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Attributes
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Image(String aUrl)
  {
    url = aUrl;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
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
            "url" + ":" + getUrl()+ "]";
  }
}