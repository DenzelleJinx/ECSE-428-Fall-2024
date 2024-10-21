package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.*;

// line 52 "Umplesrc"
@Entity
public class Sublet extends Listing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Sublet Associations
  private Student subletter;
  private List<Image> subletImages;
  private Landlord supervisingLandlord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Sublet(String aTitle, String aDescription, Address aAddress, int aMonthlyPrice, float aPropertyRating, int aBedrooms, int aBathrooms, PropertyType aPropertyType, int aSquareFootage, Boolean aWheelchairAccessible, Boolean aHidden, Landlord aPoster, Student aSubletter, Landlord aSupervisingLandlord)
  {
    super(aTitle, aDescription, aAddress, aMonthlyPrice, aPropertyRating, aBedrooms, aBathrooms, aPropertyType, aSquareFootage, aWheelchairAccessible, aHidden, aPoster);
    boolean didAddSubletter = setSubletter(aSubletter);
    if (!didAddSubletter)
    {
      throw new RuntimeException("Unable to create housing due to subletter. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    subletImages = new ArrayList<Image>();
    boolean didAddSupervisingLandlord = setSupervisingLandlord(aSupervisingLandlord);
    if (!didAddSupervisingLandlord)
    {
      throw new RuntimeException("Unable to create propertySublet due to supervisingLandlord. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public Student getSubletter()
  {
    return subletter;
  }
  /* Code from template association_GetMany */
  public Image getSubletImage(int index)
  {
    Image aSubletImage = subletImages.get(index);
    return aSubletImage;
  }

  @OneToMany()
  public List<Image> getSubletImages()
  {
    List<Image> newSubletImages = Collections.unmodifiableList(subletImages);
    return newSubletImages;
  }

  public int numberOfSubletImages()
  {
    int number = subletImages.size();
    return number;
  }

  public boolean hasSubletImages()
  {
    boolean has = subletImages.size() > 0;
    return has;
  }

  public int indexOfSubletImage(Image aSubletImage)
  {
    int index = subletImages.indexOf(aSubletImage);
    return index;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public Landlord getSupervisingLandlord()
  {
    return supervisingLandlord;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSubletter(Student aSubletter)
  {
    boolean wasSet = false;
    if (aSubletter == null)
    {
      return wasSet;
    }

    Student existingSubletter = subletter;
    subletter = aSubletter;
    if (existingSubletter != null && !existingSubletter.equals(aSubletter))
    {
      existingSubletter.removeHousing(this);
    }
    subletter.addHousing(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSubletImages()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfSubletImages()
  {
    return 10;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addSubletImage(Image aSubletImage)
  {
    boolean wasAdded = false;
    if (subletImages.contains(aSubletImage)) { return false; }
    if (numberOfSubletImages() < maximumNumberOfSubletImages())
    {
      subletImages.add(aSubletImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeSubletImage(Image aSubletImage)
  {
    boolean wasRemoved = false;
    if (subletImages.contains(aSubletImage))
    {
      subletImages.remove(aSubletImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setSubletImages(Image... newSubletImages)
  {
    boolean wasSet = false;
    ArrayList<Image> verifiedSubletImages = new ArrayList<Image>();
    for (Image aSubletImage : newSubletImages)
    {
      if (verifiedSubletImages.contains(aSubletImage))
      {
        continue;
      }
      verifiedSubletImages.add(aSubletImage);
    }

    if (verifiedSubletImages.size() != newSubletImages.length || verifiedSubletImages.size() > maximumNumberOfSubletImages())
    {
      return wasSet;
    }

    subletImages.clear();
    subletImages.addAll(verifiedSubletImages);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubletImageAt(Image aSubletImage, int index)
  {  
    boolean wasAdded = false;
    if(addSubletImage(aSubletImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletImages()) { index = numberOfSubletImages() - 1; }
      subletImages.remove(aSubletImage);
      subletImages.add(index, aSubletImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubletImageAt(Image aSubletImage, int index)
  {
    boolean wasAdded = false;
    if(subletImages.contains(aSubletImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubletImages()) { index = numberOfSubletImages() - 1; }
      subletImages.remove(aSubletImage);
      subletImages.add(index, aSubletImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubletImageAt(aSubletImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSupervisingLandlord(Landlord aSupervisingLandlord)
  {
    boolean wasSet = false;
    if (aSupervisingLandlord == null)
    {
      return wasSet;
    }

    Landlord existingSupervisingLandlord = supervisingLandlord;
    supervisingLandlord = aSupervisingLandlord;
    if (existingSupervisingLandlord != null && !existingSupervisingLandlord.equals(aSupervisingLandlord))
    {
      existingSupervisingLandlord.removePropertySublet(this);
    }
    supervisingLandlord.addPropertySublet(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Student placeholderSubletter = subletter;
    this.subletter = null;
    if(placeholderSubletter != null)
    {
      placeholderSubletter.removeHousing(this);
    }
    subletImages.clear();
    Landlord placeholderSupervisingLandlord = supervisingLandlord;
    this.supervisingLandlord = null;
    if(placeholderSupervisingLandlord != null)
    {
      placeholderSupervisingLandlord.removePropertySublet(this);
    }
    super.delete();
  }

}