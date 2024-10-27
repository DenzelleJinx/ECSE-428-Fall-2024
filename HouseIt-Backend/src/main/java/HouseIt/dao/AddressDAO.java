package HouseIt.dao;

import HouseIt.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressDAO extends CrudRepository<Address, Integer> {

    Address findAddressById(int id);

    Address findAddressByCity(String city);

    Address findAddressByStreet(String street);

    Address findAddressByPostalCode(String postalCode);

    Address findAddressByStreetNumber(String streetNumber);

    Address findAddressByApartmentNumber(String apartmentNumber);

}
