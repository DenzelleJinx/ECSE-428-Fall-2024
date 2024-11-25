/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 48 "model.ump"
// line 106 "model.ump"
public class Picture
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Picture Attributes
  private String title;
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Picture(String aTitle, String aUrl)
  {
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
            "title" + ":" + getTitle()+ "," +
            "url" + ":" + getUrl()+ "]";
  }
}