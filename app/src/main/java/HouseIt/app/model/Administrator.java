package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;

// line 30 "Umplesrc"

@Entity
public class Administrator extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(int aId, String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating)
  {
    super(aId, aUsername, aEmail, aPassword, aStatus, aRating);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}