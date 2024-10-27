package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.util.*;

// line 29 "model.ump"
// line 94 "model.ump"
@Entity
public class Landlord extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Landlord Attributes
  private String landlordAccountStatus;

  //Landlord Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Posting> postings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Landlord() {}

  public Landlord(int aId, String aAccountStatus, String aLandlordAccountStatus)
  {
    super(aId, aAccountStatus);
    landlordAccountStatus = aLandlordAccountStatus;
    postings = new ArrayList<Posting>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLandlordAccountStatus(String aLandlordAccountStatus)
  {
    boolean wasSet = false;
    landlordAccountStatus = aLandlordAccountStatus;
    wasSet = true;
    return wasSet;
  }

  public String getLandlordAccountStatus()
  {
    return landlordAccountStatus;
  }
  /* Code from template association_GetMany */
  public Posting getPosting(int index)
  {
    Posting aPosting = postings.get(index);
    return aPosting;
  }

  public List<Posting> getPostings()
  {
    List<Posting> newPostings = Collections.unmodifiableList(postings);
    return newPostings;
  }

  public int numberOfPostings()
  {
    int number = postings.size();
    return number;
  }

  public boolean hasPostings()
  {
    boolean has = postings.size() > 0;
    return has;
  }

  public int indexOfPosting(Posting aPosting)
  {
    int index = postings.indexOf(aPosting);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPostings()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPosting(Posting aPosting)
  {
    boolean wasAdded = false;
    if (postings.contains(aPosting)) { return false; }
    postings.add(aPosting);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePosting(Posting aPosting)
  {
    boolean wasRemoved = false;
    if (postings.contains(aPosting))
    {
      postings.remove(aPosting);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPostingAt(Posting aPosting, int index)
  {  
    boolean wasAdded = false;
    if(addPosting(aPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostings()) { index = numberOfPostings() - 1; }
      postings.remove(aPosting);
      postings.add(index, aPosting);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePostingAt(Posting aPosting, int index)
  {
    boolean wasAdded = false;
    if(postings.contains(aPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostings()) { index = numberOfPostings() - 1; }
      postings.remove(aPosting);
      postings.add(index, aPosting);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPostingAt(aPosting, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    postings.clear();
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "landlordAccountStatus" + ":" + getLandlordAccountStatus()+ "]";
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(super.hashCode(), landlordAccountStatus, postings);
  }
}