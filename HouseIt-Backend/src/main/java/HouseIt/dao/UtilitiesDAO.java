package HouseIt.dao;

import HouseIt.model.Utilities;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UtilitiesDAO extends CrudRepository<Utilities, Integer> {

    Utilities findUtilitiesById(int id);
    
    List<Utilities> findUtilitiesByWaterCost(float waterCost);

    List<Utilities> findUtilitiesByElectricityCost(float electricityCost);

    List<Utilities> findUtilitiesByHeatingCost(float heatingCost);

    void deleteById(int id);
}
