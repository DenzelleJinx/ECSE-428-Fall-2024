package HouseIt.dao;

import HouseIt.model.Amenities;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AmenitiesDAO extends CrudRepository<Amenities, Integer> {

    Amenities findAmenitiesById(int id);
    
    List<Amenities> findAmenitiessByGym(Boolean gym);

    List<Amenities> findAmenitiessByLaundry(Boolean laundry);

    List<Amenities> findAmenitiessByPetsAllowed(Boolean petsAllowed);

    List<Amenities> findAmenitiessByParking(Boolean parking);

    List<Amenities> findAmenitiessByInternetInclued(Boolean internetInclued);

}
