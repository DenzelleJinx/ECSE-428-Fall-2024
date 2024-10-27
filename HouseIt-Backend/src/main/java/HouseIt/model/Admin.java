package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;

// line 25 "model.ump"
// line 72 "model.ump"
// line 79 "model.ump"
@Entity
public class Admin extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin() {}

  public Admin(int aId, String aAccountStatus)
  {
    super(aId, aAccountStatus);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  @Override
  public int hashCode()
  {
    return super.hashCode();
  }
}