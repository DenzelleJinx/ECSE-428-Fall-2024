package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.dao.UtilitiesDAO;
import HouseIt.model.Utilities;

@SpringBootTest
public class UtilitiesDAOTests {

    @Autowired
    private UtilitiesDAO utilitiesDAO;

    private float waterCost = 10.0f;
    private float electricityCost = 20.0f;
    private float heatingCost = 30.0f;
    private Utilities utilities;
    
    @BeforeEach
    @AfterEach
    public void tearDown() {
        utilitiesDAO.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        utilities = new Utilities();
        utilities.setWaterCost(waterCost);
        utilities.setElectricityCost(electricityCost);
        utilities.setHeatingCost(heatingCost);

        utilitiesDAO.save(utilities);
    }

    @Test
    public void testFindUtilitiesByWaterCost() {
        Utilities foundUtilities = utilitiesDAO.findUtilitiesByWaterCost(waterCost).get(0);

        assertNotNull(foundUtilities);
        assertEquals(utilities, foundUtilities);
    }

    @Test
    public void testFindUtilitiesByElectricityCost() {
        Utilities foundUtilities = utilitiesDAO.findUtilitiesByElectricityCost(electricityCost).get(0);

        assertNotNull(foundUtilities);
        assertEquals(utilities, foundUtilities);
    }

    @Test
    public void testFindUtilitiesByHeatingCost() {
        Utilities foundUtilities = utilitiesDAO.findUtilitiesByHeatingCost(heatingCost).get(0);

        assertNotNull(foundUtilities);
        assertEquals(utilities, foundUtilities);
    }
    
    @Test
    public void testFindUtilitiesById() {
        Utilities foundUtilities = utilitiesDAO.findUtilitiesById(utilities.getId());

        assertNotNull(foundUtilities);
        assertEquals(utilities, foundUtilities);
    }

    @Test
    public void testDeleteUtilitiesById() {
        utilitiesDAO.deleteById(utilities.getId());

        Utilities foundUtilities = utilitiesDAO.findUtilitiesById(utilities.getId());

        assertNull(foundUtilities);
    }
}