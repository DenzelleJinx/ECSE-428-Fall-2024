package HouseIt.app.dao;

import HouseIt.app.model.Utilities;

import org.springframework.data.repository.CrudRepository;

public interface UtilitiesRepository extends CrudRepository<Utilities, Integer> {

    Utilities findUtilitiesByWaterCost(String waterCost);

    Utilities findUtilitiesByElectricityCost(String electricityCost);

    Utilities findUtilitiesByHeatingCost(String heatingCost);
}
