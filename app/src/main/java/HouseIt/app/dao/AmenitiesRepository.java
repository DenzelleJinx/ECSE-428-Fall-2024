package HouseIt.app.dao;

import HouseIt.app.model.Amenities;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AmenitiesRepository extends CrudRepository<Amenities, Integer> {

    List<Amenities> findAmenitiessByGym(Boolean gym);

    List<Amenities> findAmenitiessByLaundry(Boolean laundry);

    List<Amenities> findAmenitiessByPetsAllowed(Boolean petsAllowed);

    List<Amenities> findAmenitiessByParking(Boolean parking);

    List<Amenities> findAmenitiessByInternetInclued(Boolean internetInclued);

}
