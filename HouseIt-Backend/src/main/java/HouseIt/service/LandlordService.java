package HouseIt.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.List; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.LandlordDAO;
import HouseIt.dto.users.LandlordDTO;
import HouseIt.model.Landlord;
import HouseIt.model.Listing;
import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;
import HouseIt.utils.ValidationUtils;

@Service
public class LandlordService {

    @Autowired
    LandlordDAO landlordDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            throw new IllegalArgumentException(
                    "Phone number already exists in the system. Please enter another phone number.");
        }

        Landlord newLandlord = new Landlord();
        newLandlord.setUsername(username);
        newLandlord.setPassword(passwordEncoder.encode(password));
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

    @Transactional
    public Landlord updateLandlord(int id, String username, String password, String email, String phoneNumber,
            AccountStatus status, float rating) {
        // Retrieve existing landlord
        Landlord landlord = landlordDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Landlord not found"));

        // Update fields if they are provided
        if (username != null && !username.trim().isEmpty()) {
            landlord.setUsername(username);
        }

        if (password != null && password.trim().length() >= 6) {
            landlord.setPassword(passwordEncoder.encode(password));
        } else if (password != null) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        if (email != null && !email.trim().isEmpty()) {
            if (!email.equals(landlord.getEmail()) && landlordDAO.findLandlordByEmail(email) != null) {
                throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
            }
            landlord.setEmail(email);
        }

        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            landlord.setPhoneNumber(phoneNumber);
        }

        if (status != null) {
            landlord.setStatus(status);
        }

        if (rating >= 0.0f) {
            landlord.setRating(rating);
        }

        return landlordDAO.save(landlord);
    }

    @Transactional
    public void resetPassword(String email, String newPassword) {
        Landlord landlord = landlordDAO.findLandlordByEmail(email);
        if (landlord == null) {
            throw new IllegalArgumentException("No landlord found with the provided email.");
        }

        ValidationUtils.validatePassword(newPassword);
        landlord.setPassword(passwordEncoder.encode(newPassword)); // Ideally hash the password
        landlordDAO.save(landlord);
    }

    @Transactional
    public void deleteListing(int listingId) {

        // Find the landlord that owns the listing
        Landlord landlord = StreamSupport.stream(landlordDAO.findAll().spliterator(), false)
                .filter(l -> l.getProperties().stream().anyMatch(listing -> listing.getId() == listingId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No landlord found for listing ID: " + listingId));

        // Find the listing in the landlord's properties
        Listing listingToRemove = landlord.getProperties().stream()
                .filter(listing -> listing.getId() == listingId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Listing not found with ID: " + listingId));

        // Remove the listing from the landlord's properties
        if (landlord.removeProperty(listingToRemove)) {
            landlordDAO.save(landlord); // Persist the changes
        } else {
            throw new IllegalStateException("Failed to remove listing with ID: " + listingId);
        }
    }

    @Transactional
    public Landlord getLandlord(int landlordId) {
        Landlord landlord = landlordDAO.findLandlordById(landlordId);
        if (landlord == null) {
            throw new IllegalArgumentException("No landlord found with ID: " + landlordId);
        }
        return landlord;
    }

    @Transactional
    public Landlord rateLandlord(int landlordId, float rating) {

        Landlord landlord = landlordDAO.findLandlordById(landlordId);
        if (landlord == null) {
            throw new IllegalArgumentException("No such landlord with id: " + landlordId);
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 inclusive.");
        }

        float avgRating = landlord.getRating();
        int ratingCount = landlord.getRatingCount();
        avgRating = (avgRating * ratingCount + rating) / (ratingCount + 1);
        ratingCount++;

        landlord.setRating(avgRating);
        landlord.setRatingCount(ratingCount);
        return landlordDAO.save(landlord);
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

    public Landlord existsByEmail(String email) {
        Landlord l = landlordDAO.findLandlordByEmail(email);

        return l;
    }

    @Transactional(readOnly = true)
    public List<Listing> getListingsByLandlordId(int landlordId) {
    return landlordDAO.findById(landlordId)
                      .map(Landlord::getProperties)
                      .orElseThrow(() -> new IllegalArgumentException("No landlord found with ID: " + landlordId));
    }
}
