package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;

// line 32 "model.ump"
// line 120 "model.ump"
@Entity
public class Administrator extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator()
  {
    super();
  }

  public Administrator(String aUsername, String aEmail, String aPassword, AccountStatus aStatus, float aRating, List<Notification> aNotifications)
  {
    super(aUsername, aEmail, aPassword, aStatus, aRating, aNotifications);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  @Override
  public boolean equals(Object o)
  {
    return super.equals(o);
  }

  @Override
  public int hashCode()
  {
    return super.hashCode();
  }
}