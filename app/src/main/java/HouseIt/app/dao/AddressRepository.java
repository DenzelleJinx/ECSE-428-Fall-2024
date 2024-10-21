package HouseIt.app.dao;

import HouseIt.app.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
