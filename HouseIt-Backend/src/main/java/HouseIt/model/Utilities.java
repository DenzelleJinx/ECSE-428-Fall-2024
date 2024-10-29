package HouseIt.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

// line 89 "model.ump"
// line 148 "model.ump"
@Entity
public class Utilities
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Utilities Attributes
  @Id
  @GeneratedValue
  private int id;
  private float waterCost;
  private float electricityCost;
  private float heatingCost;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Utilities() {}

  public Utilities(int aId, float aWaterCost, float aElectricityCost, float aHeatingCost)
  {
    id = aId;
    waterCost = aWaterCost;
    electricityCost = aElectricityCost;
    heatingCost = aHeatingCost;
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

  public int getId()
  {
    return id;
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
            "id" + ":" + getId()+ "," +
            "waterCost" + ":" + getWaterCost()+ "," +
            "electricityCost" + ":" + getElectricityCost()+ "," +
            "heatingCost" + ":" + getHeatingCost()+ "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Utilities)) return false;
    Utilities utilities = (Utilities) o;
    return id == utilities.id &&
            Float.compare(utilities.waterCost, waterCost) == 0 &&
            Float.compare(utilities.electricityCost, electricityCost) == 0 &&
            Float.compare(utilities.heatingCost, heatingCost) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, waterCost, electricityCost, heatingCost);
  }
}