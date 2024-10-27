package HouseIt.dao;

import HouseIt.model.Utilities;

import org.springframework.data.repository.CrudRepository;

public interface UtilitiesDAO extends CrudRepository<Utilities, Integer> {

    Utilities findUtilitiesById(int id);
    
    Utilities findUtilitiesByWaterCost(float waterCost);

    Utilities findUtilitiesByElectricityCost(float electricityCost);

    Utilities findUtilitiesByHeatingCost(float heatingCost);
}
