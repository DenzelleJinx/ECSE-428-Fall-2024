package HouseIt.app.dao;

import HouseIt.app.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    Address findAddressByCity(String city);

    Address findAddressByStreet(String street);

    Address findAddressByPostalCode(String postalCode);

    Address findAddressByStreetNumber(int streetNumber);

    Address findAddressByApartmentNumber(int apartmentNumber);

}
