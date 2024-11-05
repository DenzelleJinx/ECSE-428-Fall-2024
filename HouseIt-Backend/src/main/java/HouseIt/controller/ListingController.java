package HouseIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import HouseIt.dto.ListingDTO;
import HouseIt.model.Listing;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Image;
import HouseIt.model.Listing.PropertyType;
import HouseIt.model.Utilities;
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
        @RequestParam Amenities amenitiesOffered,
        @RequestParam Utilities utilitiesCosts,
        @RequestParam List<Image> propertyImages )
         {

        Listing listing = listingService.createListing(title, description, monthlyPrice, propertyRating, bedrooms, bathrooms, propertyType, squareFootage, wheelchairAccessible, hidden, smokingAllowed, address, amenitiesOffered, null, utilitiesCosts);
        ListingDTO dto = listingService.convertToDTO(listing);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping(value = {"", "/{id}"})
    public ResponseEntity<ListingDTO> updateListing(
        @PathVariable int id,
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
        @RequestParam Amenities amenitiesOffered,
        @RequestParam Utilities utilitiesCosts,
        @RequestParam List<Image> propertyImages )
         {

        Listing listing = listingService.updateListing(id, title, description, monthlyPrice, propertyRating, bedrooms, bathrooms, propertyType, squareFootage, wheelchairAccessible, hidden, smokingAllowed, address, amenitiesOffered, null, utilitiesCosts);
        ListingDTO dto = listingService.convertToDTO(listing);
        return ResponseEntity.ok(dto);
    }
    
}
