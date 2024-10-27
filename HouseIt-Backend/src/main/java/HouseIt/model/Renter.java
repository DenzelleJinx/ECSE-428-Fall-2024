package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import java.util.*;

// line 38 "model.ump"
// line 99 "model.ump"
@Entity
public class Renter extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Renter Associations
  @OneToMany(fetch = FetchType.EAGER)
  private List<Posting> savedPostings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Renter() {}

  public Renter(int aId, String aAccountStatus)
  {
    super(aId, aAccountStatus);
    savedPostings = new ArrayList<Posting>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Posting getSavedPosting(int index)
  {
    Posting aSavedPosting = savedPostings.get(index);
    return aSavedPosting;
  }

  public List<Posting> getSavedPostings()
  {
    List<Posting> newSavedPostings = Collections.unmodifiableList(savedPostings);
    return newSavedPostings;
  }

  public int numberOfSavedPostings()
  {
    int number = savedPostings.size();
    return number;
  }

  public boolean hasSavedPostings()
  {
    boolean has = savedPostings.size() > 0;
    return has;
  }

  public int indexOfSavedPosting(Posting aSavedPosting)
  {
    int index = savedPostings.indexOf(aSavedPosting);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSavedPostings()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addSavedPosting(Posting aSavedPosting)
  {
    boolean wasAdded = false;
    if (savedPostings.contains(aSavedPosting)) { return false; }
    savedPostings.add(aSavedPosting);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSavedPosting(Posting aSavedPosting)
  {
    boolean wasRemoved = false;
    if (savedPostings.contains(aSavedPosting))
    {
      savedPostings.remove(aSavedPosting);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSavedPostingAt(Posting aSavedPosting, int index)
  {  
    boolean wasAdded = false;
    if(addSavedPosting(aSavedPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSavedPostings()) { index = numberOfSavedPostings() - 1; }
      savedPostings.remove(aSavedPosting);
      savedPostings.add(index, aSavedPosting);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSavedPostingAt(Posting aSavedPosting, int index)
  {
    boolean wasAdded = false;
    if(savedPostings.contains(aSavedPosting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSavedPostings()) { index = numberOfSavedPostings() - 1; }
      savedPostings.remove(aSavedPosting);
      savedPostings.add(index, aSavedPosting);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSavedPostingAt(aSavedPosting, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    savedPostings.clear();
    super.delete();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), savedPostings);
  }

}