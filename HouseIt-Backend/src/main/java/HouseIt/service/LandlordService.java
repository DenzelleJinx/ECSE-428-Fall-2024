package HouseIt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.LandlordDAO;
import HouseIt.dto.LandlordDTO;
import HouseIt.model.Landlord;
import HouseIt.model.User.AccountStatus;

@Service
public class LandlordService {
    
    @Autowired
    LandlordDAO landlordDAO;

    @Transactional
    public Landlord createLandlord(String username, String password, String email, String phoneNumber) {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        
        if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        
        if (password == null || password.trim().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        
        if (landlordDAO.findLandlordByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
        }

        Landlord newLandlord = new Landlord();
        newLandlord.setUsername(username);
        newLandlord.setPassword(password);
        newLandlord.setEmail(email);
        newLandlord.setPhoneNumber(phoneNumber);
        newLandlord.setStatus(AccountStatus.PENDING);
        newLandlord.setRating(0.0f);
        return landlordDAO.save(newLandlord);
    }

    public LandlordDTO convertToDTO(Landlord landlord) {
        LandlordDTO landlordDTO = new LandlordDTO();
        landlordDTO.setId(landlord.getId());
        landlordDTO.setUsername(landlord.getUsername());
        landlordDTO.setEmail(landlord.getEmail());
        landlordDTO.setStatus(landlord.getStatus());
        landlordDTO.setRating(landlord.getRating());
        landlordDTO.setPhoneNumber(landlord.getPhoneNumber());
        return landlordDTO;
    }
}
