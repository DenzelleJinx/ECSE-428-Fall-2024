package HouseIt.model;

import java.util.Objects;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

// line 2 "model.ump"
// line 84 "model.ump"
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonRole Attributes
  @Id
  @GeneratedValue
  private int id;
  private String accountStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonRole() {}

  public PersonRole(int aId, String aAccountStatus)
  {
    id = aId;
    accountStatus = aAccountStatus;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountStatus(String aAccountStatus)
  {
    boolean wasSet = false;
    accountStatus = aAccountStatus;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getAccountStatus()
  {
    return accountStatus;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "accountStatus" + ":" + getAccountStatus()+ "]";
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, accountStatus);
  }
}