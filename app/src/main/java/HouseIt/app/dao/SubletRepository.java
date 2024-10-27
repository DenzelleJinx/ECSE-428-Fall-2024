package HouseIt.app.dao;

import HouseIt.app.model.Address;
import HouseIt.app.model.Amenities;
import HouseIt.app.model.Image;
import HouseIt.app.model.Landlord;
import HouseIt.app.model.Student;
import HouseIt.app.model.Listing.PropertyType;
import HouseIt.app.model.Sublet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SubletRepository extends CrudRepository<Sublet, Integer> {
    
    Sublet findSubletByTitle(String title);

    Sublet findSubletByDescription(String description);

    Sublet findSubletByAddress(Address address);

    List<Sublet> findSubletByMonthlyPrice(int monthlyPrice);

    List<Sublet> findSubletByPropertyRating(float propertyRating);
    
    List<Sublet> findSubletByBedrooms(int bedrooms);

    List<Sublet> findSubletByBathrooms(int bathrooms);

    Sublet findSubletByPropertyType(PropertyType propertyType);

    Sublet findSubletBySquareFootage(int squareFootage);

    Sublet findSubletByWheelchairAccessible(Boolean wheelchairAccessible);

    Sublet findSubletByHidden(Boolean hidden);

    List<Sublet> findSubletbySmokingAllowed(Boolean smokingAllowed);

    List<Sublet> findSubletbyPoster(Landlord poster);

    List<Sublet> findSubletbyAmenitiesOffered(Amenities amenitiesOffered);

    List<Sublet> findSubletBySubletter(Student subletter);

    Sublet findSubletBySupervisingLandlord(Landlord supervisingLandlord);
}
