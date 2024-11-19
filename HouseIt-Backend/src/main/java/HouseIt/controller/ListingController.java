package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.jdbc.core.JdbcTemplate;

import HouseIt.dto.ListingCreateDTO;
import HouseIt.dto.ListingDTO;
import HouseIt.dto.users.UserDTO;
import HouseIt.service.UserService;
import HouseIt.model.Listing;
import HouseIt.model.User;
import HouseIt.service.ListingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    ListingService listingService;

    @PostMapping(value = {"", "/"})
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

    @PutMapping(value = {"{id}", "/{id}"})
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

    @GetMapping(value = {"{id}", "/{id}"})
    public ResponseEntity<?> getListing(@PathVariable int id) {
        try {
            Listing listing = listingService.getListing(id);
            ListingDTO dto = listingService.convertToDTO(listing);
            return new ResponseEntity<ListingDTO>(dto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> getAllListings() {
        List<Listing> listings = listingService.getAllListings();
            List<ListingDTO> listingsDTO = listings.stream().map(listingService::convertToDTO).toList();
            return new ResponseEntity<List<ListingDTO>>(listingsDTO, HttpStatus.OK);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;  // For executing raw SQL queries

    @Autowired
    private UserService userService;

    // This is super scuffed and I hate it but I spent hours trying to work this out.
    @GetMapping("{id}/lister")
    public ResponseEntity<UserDTO> getListerByListing(@PathVariable("id") int id) {
        // Retrieve the listing by ID
        Listing listing = listingService.getListing(id);
        if (listing == null) {
            throw new IllegalArgumentException("Listing with id " + id + " does not exist.");
        }

        // Query the house_it_user_properties table
        // This is ugly but I have no other choice at the moment
        String propertyQuery = "SELECT landlord_id FROM house_it_user_properties WHERE properties_id = ?";
        Map<String, Object> propertyResult = jdbcTemplate.queryForMap(propertyQuery, id);

        UserDTO userDTO;
        if (propertyResult != null && !propertyResult.isEmpty()) {
            // If a match is found in house_it_user_properties, get the landlord ID
            int landlordId = (int) propertyResult.get("landlord_id");
            User landlord = userService.getUserById(landlordId);
            if (landlord == null) {
                throw new IllegalArgumentException("Landlord with id " + landlordId + " does not exist.");
            }
            userDTO = new UserDTO(landlord);

        } else { // If not found, query the house_it_user_sublet_listings table
            String subletQuery = "SELECT student_id FROM house_it_user_sublet_listings WHERE sublet_listings_id = ?";
            Map<String, Object> subletResult = jdbcTemplate.queryForMap(subletQuery, id);

            if (subletResult != null && !subletResult.isEmpty()) {
                // If a match is found in house_it_user_sublet_listings, get the student ID
                int studentId = (int) subletResult.get("student_id");
                User student = userService.getUserById(studentId);
                if (student == null) {
                    throw new IllegalArgumentException("Student with id " + studentId + " does not exist.");
                }
                userDTO = new UserDTO(student);

            } else {
                throw new IllegalArgumentException("No property or sublet listing found for listing id " + id);
            }
        }
        
        return ResponseEntity.ok(userDTO);
    }
}
