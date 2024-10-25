package HouseIt.app.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import javax.persistence.Entity;

// line 90 "Umplesrc"
@Entity
public class Utilities
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Utilities Attributes
  private float waterCost;
  private float electricityCost;
  private float heatingCost;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Utilities(float aWaterCost, float aElectricityCost, float aHeatingCost)
  {
    waterCost = aWaterCost;
    electricityCost = aElectricityCost;
    heatingCost = aHeatingCost;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWaterCost(float aWaterCost)
  {
    boolean wasSet = false;
    waterCost = aWaterCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setElectricityCost(float aElectricityCost)
  {
    boolean wasSet = false;
    electricityCost = aElectricityCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeatingCost(float aHeatingCost)
  {
    boolean wasSet = false;
    heatingCost = aHeatingCost;
    wasSet = true;
    return wasSet;
  }

  public float getWaterCost()
  {
    return waterCost;
  }

  public float getElectricityCost()
  {
    return electricityCost;
  }

  public float getHeatingCost()
  {
    return heatingCost;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "waterCost" + ":" + getWaterCost()+ "," +
            "electricityCost" + ":" + getElectricityCost()+ "," +
            "heatingCost" + ":" + getHeatingCost()+ "]";
  }
}