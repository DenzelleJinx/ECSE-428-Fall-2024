package HouseIt.app.dao;

import HouseIt.app.model.Adminitrator;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Adminitrator, Integer> {

    Address findAddressByaID(String aId);

    Address findAddressByaUsername(String aUsername);

    Address findAddressByaEmail(String aEmail);

}