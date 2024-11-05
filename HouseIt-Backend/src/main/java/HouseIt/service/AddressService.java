package HouseIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.AddressDAO;
import HouseIt.model.Address;

@Service
public class AddressService {
    
    @Autowired
    private AddressDAO addressDAO;

    @Transactional
    public Address createAddress(Address address) {
        return addressDAO.save(address);
    }

    @Transactional
    public Address updateAddress(Address address) {
        return addressDAO.save(address);
    }
}
