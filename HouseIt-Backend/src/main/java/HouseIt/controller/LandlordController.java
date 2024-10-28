package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import HouseIt.dto.LandlordDTO;
import HouseIt.model.Landlord;
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
        return ResponseEntity.ok(dto);
    }
    
    
}
