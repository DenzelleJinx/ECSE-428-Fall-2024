package HouseIt.app.dao;

import HouseIt.app.model.Address;
import HouseIt.app.model.Amenities;
import HouseIt.app.model.Image;
import HouseIt.app.model.Landlord;
import HouseIt.app.model.Listing;
import HouseIt.app.model.Listing.PropertyType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Integer> {

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

    List<Listing> findListingByPoster(Landlord poster);

    List<Listing> findListingByAmenitiesOffered(Amenities amenitiesOffered);
}
