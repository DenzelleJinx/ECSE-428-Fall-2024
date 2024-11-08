package HouseIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.AmenitiesDAO;
import HouseIt.model.Amenities;

@Service
public class AmenitiesService {
    
    @Autowired
    private AmenitiesDAO amenitiesDAO;

    @Transactional
    public Amenities createAmenities(Amenities amenities) {
        return amenitiesDAO.save(amenities);
    }

    @Transactional
    public Amenities updateAmenities(Amenities amenities) {
        return amenitiesDAO.save(amenities);
    }
}
