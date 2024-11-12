package HouseIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import HouseIt.model.User;
import HouseIt.utils.Helper;
import HouseIt.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Transactional
    public List<User> getAllUsers() {
        return Helper.toList(userDAO.findAll());
    }
    
    /**
     * 
     * takes the password and encrypts it
     * 
     * steps: takes the ASCII value (between 32 and 126) and adds 50 while keeping it between 32 and 126, then reconverts it into a string to store
     */
    public static String passwordHash(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int asciiValue = (int) currentChar;

            // Add 50 to the ASCII value
            asciiValue += 50;

            // Ensure the ASCII value stays within the range [32, 126]
            if (asciiValue > 126) {
                // If it exceeds 126, wrap around from 32
                asciiValue = 32 + (asciiValue - 127);
            }

            // Convert the new ASCII value back to a character
            char newChar = (char) asciiValue;
            result.append(newChar);
        }

        return result.toString();
    }

    // Converts the encrypted password back into its original string
    public static String passwordDecrypt(String transformedInput) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < transformedInput.length(); i++) {
            char currentChar = transformedInput.charAt(i);
            int asciiValue = (int) currentChar;

            // Subtract 50 from the ASCII value
            asciiValue -= 50;

            // Ensure the ASCII value stays within the range [32, 126]
            if (asciiValue < 32) {
                // If it goes below 32, wrap around to 126
                asciiValue = 126 - (32 - asciiValue - 1);
            }

            // Convert the new ASCII value back to a character
            char newChar = (char) asciiValue;
            result.append(newChar);
        }

        return result.toString();
    }
}
