package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import HouseIt.dto.ListingDTO;
import HouseIt.model.Listing;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Landlord;
import HouseIt.model.Listing.PropertyType;
import HouseIt.service.ListingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    ListingService listingService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ListingDTO> createListing(
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam int monthlyPrice,
        @RequestParam float propertyRating,
        @RequestParam int bedrooms,
        @RequestParam int bathrooms,
        @RequestParam PropertyType propertyType,
        @RequestParam int squareFootage,
        @RequestParam boolean wheelchairAccessible,
        @RequestParam boolean hidden,
        @RequestParam boolean smokingAllowed,
        @RequestParam Address address,
        @RequestParam Landlord poster,
        @RequestParam Amenities amenitiesOffered) {

        Listing listing = listingService.createListing(title, description, monthlyPrice, propertyRating, bedrooms, bathrooms, propertyType, squareFootage, wheelchairAccessible, hidden, smokingAllowed, address, amenitiesOffered);
        ListingDTO dto = listingService.convertToDTO(listing);
        return ResponseEntity.created(null).body(dto);
    }
    
}
