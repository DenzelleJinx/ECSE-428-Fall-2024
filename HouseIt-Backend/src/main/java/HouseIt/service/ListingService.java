package HouseIt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.ListingDAO;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Listing;
import HouseIt.dto.ListingDTO;

@Service
public class ListingService {
    
    @Autowired
    private ListingDAO listingDAO;

    @Transactional
    public Listing createListing(String title, String description, int monthlyPrice, float propertyRating, int bedrooms, int bathrooms, 
                                 Listing.PropertyType propertyType, int squareFootage, boolean wheelchairAccessible, boolean hidden, 
                                 boolean smokingAllowed, Address address,  Amenities amenitiesOffered) {

        // Validation of individual parameters
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        
        if (monthlyPrice <= 0) {
            throw new IllegalArgumentException("Monthly price must be greater than zero.");
        }

        // Creating a new Listing object
        Listing newListing = new Listing();
        newListing.setTitle(title);
        newListing.setDescription(description);
        newListing.setMonthlyPrice(monthlyPrice);
        newListing.setPropertyRating(propertyRating);
        newListing.setBedrooms(bedrooms);
        newListing.setBathrooms(bathrooms);
        newListing.setPropertyType(propertyType);
        newListing.setSquareFootage(squareFootage);
        newListing.setWheelchairAccessible(wheelchairAccessible);
        newListing.setHidden(hidden);
        newListing.setSmokingAllowed(smokingAllowed);
        newListing.setAddress(address);
        newListing.setAmenitiesOffered(amenitiesOffered);

        return listingDAO.save(newListing);
    }

    public ListingDTO convertToDTO(Listing listing) {
        ListingDTO dto = new ListingDTO();
        dto.setId(listing.getId());
        dto.setTitle(listing.getTitle());
        dto.setDescription(listing.getDescription());
        dto.setMonthlyPrice(listing.getMonthlyPrice());
        dto.setPropertyRating(listing.getPropertyRating());
        dto.setBedrooms(listing.getBedrooms());
        dto.setBathrooms(listing.getBathrooms());
        dto.setPropertyType(listing.getPropertyType());
        dto.setSquareFootage(listing.getSquareFootage());
        dto.setWheelchairAccessible(listing.getWheelchairAccessible());
        dto.setHidden(listing.getHidden());
        dto.setSmokingAllowed(listing.getSmokingAllowed());
        dto.setAddress(listing.getAddress());
        dto.setAmenitiesOffered(listing.getAmenitiesOffered());

        return dto;
    }
}
