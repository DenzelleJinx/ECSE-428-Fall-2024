package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import java.util.List;
import HouseIt.model.Listing;

import HouseIt.dto.users.LandlordDTO;
import HouseIt.model.Landlord;
import HouseIt.model.User.AccountStatus;
import HouseIt.service.LandlordService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/landlord")
public class LandlordController {
    
    @Autowired
    LandlordService landlordService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<LandlordDTO> createLandlord(
        @RequestParam String username,
        @RequestParam String password, 
        @RequestParam String email, 
        @RequestParam String phoneNumber) {
        
        Landlord landlord = landlordService.createLandlord(username, password, email, phoneNumber);
        LandlordDTO dto = landlordService.convertToDTO(landlord);
        return ResponseEntity.created(null).body(dto);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<LandlordDTO> updateLandlord(
        @PathVariable int id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String phoneNumber,
        @RequestParam(required = false) AccountStatus status,
        @RequestParam(required = false) Float rating) {

        Landlord updatedLandlord = landlordService.updateLandlord(id, username, password, email, phoneNumber, status, rating);
        LandlordDTO updatedDto = landlordService.convertToDTO(updatedLandlord);

        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("{landlordId}/listings")
    public ResponseEntity<List<Listing>> getListingsByLandlordId(@PathVariable int landlordId) {
        try {
            List<Listing> listings = landlordService.getListingsByLandlordId(landlordId);
            return ResponseEntity.ok(listings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{landlordId}")
    public ResponseEntity<?> getLandlord(@PathVariable int landlordId) {
        try {
            Landlord landlord = landlordService.getLandlord(landlordId);
            return ResponseEntity.ok(landlordService.convertToDTO(landlord));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

    @PutMapping("/{landlordId}/rate/{rating}")
    public ResponseEntity<?> getLandlordRating(@PathVariable int landlordId, @PathVariable float rating) {
        try {
            Landlord landlord = landlordService.rateLandlord(landlordId, rating);
            return ResponseEntity.ok(landlord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
