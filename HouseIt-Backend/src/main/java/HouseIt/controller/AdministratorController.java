package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.service.AdministratorService;

import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdministratorController {
    
    @Autowired
    private AdministratorService administratorService;

    @PutMapping(value = {"/approve-landlord/{landlordId}", "/approve-landlord/{landlordId}/"})
    public ResponseEntity<?> approveLandlord(@PathVariable int landlordId) {
        // TODO: Authenticate administrator
        try {
            administratorService.approveLandlord(landlordId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = {"/reject-landlord/{landlordId}", "/reject-landlord/{landlordId}/"})
    public ResponseEntity<?> rejectLandlord(@PathVariable int landlordId) {
        // TODO: Authenticate administrator
        try {
            administratorService.rejectLandlord(landlordId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
