package HouseIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import HouseIt.dto.ListingCreateDTO;
import HouseIt.dto.ListingDTO;
import HouseIt.model.Listing;
import HouseIt.service.ListingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    ListingService listingService;

    @PostMapping(value = { "", "/" })
    public ResponseEntity<?> createListing(@RequestBody ListingCreateDTO body) {
        try {
            Listing listing = listingService.createListing(
                    body.getLandlordId(),
                    body.getTitle(),
                    body.getDescription(),
                    body.getMonthlyPrice(),
                    body.getBedrooms(),
                    body.getBathrooms(),
                    body.getPropertyType(),
                    body.getSquareFootage(),
                    body.isWheelchairAccessible(),
                    body.isSmokingAllowed(),
                    body.getAddress(),
                    body.getAmenitiesOffered(),
                    body.getPropertyImages(),
                    body.getUtilitiesCosts());
            ListingDTO dto = listingService.convertToDTO(listing);
            return new ResponseEntity<ListingDTO>(dto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = { "{id}", "/{id}" })
    public ResponseEntity<?> updateListing(@PathVariable int id, @RequestBody ListingDTO body) {
        try {
            Listing listing = listingService.updateListing(
                    id,
                    body.getTitle(),
                    body.getDescription(),
                    body.getMonthlyPrice(),
                    body.getPropertyRating(),
                    body.getBedrooms(),
                    body.getBathrooms(),
                    body.getPropertyType(),
                    body.getSquareFootage(),
                    body.isWheelchairAccessible(),
                    body.isHidden(),
                    body.isSmokingAllowed(),
                    body.getAddress(),
                    body.getAmenitiesOffered(),
                    body.getPropertyImages(),
                    body.getUtilitiesCosts());
            ListingDTO dto = listingService.convertToDTO(listing);
            return new ResponseEntity<ListingDTO>(dto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = { "{id}", "/{id}" })
    public ResponseEntity<?> getListing(@PathVariable int id) {
        try {
            Listing listing = listingService.getListing(id);
            ListingDTO dto = listingService.convertToDTO(listing);
            return new ResponseEntity<ListingDTO>(dto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = { "", "/" })
    public ResponseEntity<?> getAllListings() {
        List<Listing> listings = listingService.getAllListings();
        List<ListingDTO> listingsDTO = listings.stream().map(listingService::convertToDTO).toList();
        return new ResponseEntity<List<ListingDTO>>(listingsDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = { "{id}", "/{id}" })
    public ResponseEntity<?> deleteListing(@PathVariable int id) {
        try {
            listingService.deleteListing(id); // Call the service method to delete the listing
            return new ResponseEntity<String>("Listing deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeListing(@PathVariable int id) {
        try {
            ListingDTO dto = listingService.convertToDTO(listingService.completeListing(id));
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
