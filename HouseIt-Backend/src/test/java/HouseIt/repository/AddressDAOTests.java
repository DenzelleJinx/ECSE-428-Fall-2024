package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.dao.AddressDAO;
import HouseIt.model.Address;

@SpringBootTest
public class AddressDAOTests {
    @Autowired
    private AddressDAO addressDAO;

    private String city = "testCity";
    private String postal = "testPostalCode";
    private String street = "testStreet";
    private String streetNumber = "testStreetNumber";
    private String apartmentNumber = "testApartmentNumber";
    private Address address;

    @BeforeEach
    public void setUp() {
        address = new Address();
        address.setCity(city);
        address.setPostalCode(postal);
        address.setStreet(street);
        address.setStreetNumber(streetNumber);
        address.setApartmentNumber(apartmentNumber);

        addressDAO.save(address);
    }

    @AfterEach
    public void tearDown() {
        addressDAO.deleteAll();
    }

    @Test
    public void testFindAddressByCity() {
        Address foundAddress = addressDAO.findAddressByCity(city);

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testFindAddressByPostalCode() {
        Address foundAddress = addressDAO.findAddressByPostalCode(postal);

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testFindAddressByStreet() {
        Address foundAddress = addressDAO.findAddressByStreet(street);

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testFindAddressByStreetNumber() {
        Address foundAddress = addressDAO.findAddressByStreetNumber(streetNumber);

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testFindAddressByApartmentNumber() {
        Address foundAddress = addressDAO.findAddressByApartmentNumber(apartmentNumber);

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testFindAddressById() {
        Address foundAddress = addressDAO.findAddressById(address.getId());

        assertNotNull(foundAddress);
        assertEquals(address, foundAddress);
    }

    @Test
    public void testDeleteAddressById() {
        addressDAO.deleteById(address.getId());

        Address foundAddress = addressDAO.findAddressById(address.getId());

        assertNull(foundAddress);
    }
}
