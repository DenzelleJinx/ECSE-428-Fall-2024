package HouseIt.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.LandlordDAO;
import HouseIt.dto.users.LandlordDTO;
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

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        if (phoneNumber.trim().length() != 10) {
            throw new IllegalArgumentException("Phone number must be 10 digits long.");
        }

        if (landlordDAO.findLandlordByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists in the system. Please enter another username.");
        }
        
        if (landlordDAO.findLandlordByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
        }
        
        if (landlordDAO.findLandlordByPhoneNumber(phoneNumber) != null) {
            throw new IllegalArgumentException("Phone number already exists in the system. Please enter another phone number.");
        }

        Landlord newLandlord = new Landlord();
        newLandlord.setUsername(username);
        newLandlord.setPassword(password);
        newLandlord.setEmail(email);
        newLandlord.setPhoneNumber(phoneNumber);
        newLandlord.setStatus(AccountStatus.PENDING);
        newLandlord.setRating(0.0f);
        try {
            return landlordDAO.save(newLandlord);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("An account with the same username or email exists already.");
        }
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
