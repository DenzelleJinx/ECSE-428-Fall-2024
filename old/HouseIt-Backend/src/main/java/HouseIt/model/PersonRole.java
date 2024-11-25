/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 2 "model.ump"
// line 81 "model.ump"
public abstract class PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonRole Attributes
  private int id;
  private String accountStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonRole(int aId, String aAccountStatus)
  {
    id = aId;
    accountStatus = aAccountStatus;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

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
}