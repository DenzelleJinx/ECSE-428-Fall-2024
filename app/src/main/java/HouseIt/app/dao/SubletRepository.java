package HouseIt.app.dao;

import HouseIt.app.model.Address;
import HouseIt.app.model.Image;
import HouseIt.app.model.Landlord;
import HouseIt.app.model.Student;
import HouseIt.app.model.Listing;
import HouseIt.app.model.Listing.PropertyType;
import HouseIt.app.model.Sublet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SubletRepository extends CrudRepository<Sublet, Integer> {
    List<Sublet> findSubletbyPoster(Landlord poster)

    List<Sublet> findSubletBySubletter(Student subletter);

    Sublet findAddressBySubletImages(List<Image> subletImages);

    Sublet findSubletBySupervisingLandlord(Landlord supervisingLandlord);

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
}
