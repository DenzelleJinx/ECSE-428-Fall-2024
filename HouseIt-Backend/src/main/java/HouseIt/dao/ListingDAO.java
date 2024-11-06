package HouseIt.dao;

import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Listing;
import HouseIt.model.Listing.PropertyType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ListingDAO extends CrudRepository<Listing, Integer> {

    List<Listing> findAll();

    Listing findListingById(int id);

    Listing findListingByTitle(String title);

    Listing findListingByDescription(String description);

    Listing findListingByAddress(Address address);

    List<Listing> findListingByMonthlyPrice(int monthlyPrice);

    List<Listing> findListingByPropertyRating(float propertyRating);
    
    List<Listing> findListingByBedrooms(int bedrooms);

    List<Listing> findListingByBathrooms(int bathrooms);

    List<Listing> findListingByPropertyType(PropertyType propertyType);

    List<Listing> findListingBySquareFootage(int squareFootage);

    List<Listing> findListingByWheelchairAccessible(Boolean wheelchairAccessible);

    List<Listing> findListingByHidden(Boolean hidden);

    List<Listing> findListingBySmokingAllowed(Boolean smokingAllowed);

    List<Listing> findListingByAmenitiesOffered(Amenities amenitiesOffered);

    void deleteById(int id);
}
