package HouseIt.utils;

public class ValidationUtils {

    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        if (phoneNumber.trim().length() != 10) {
            throw new IllegalArgumentException("Phone number must be 10 digits long.");
        }
    }

    public static void validateUniqueField(Object existingValue, String fieldName) {
        if (existingValue != null) {
            throw new IllegalArgumentException(fieldName + " already exists in the system. Please enter another " + fieldName.toLowerCase() + ".");
        }
    }

    public static void validateMcGillEmail(String email) {
        String mcgillEmailPattern = "^[a-z]+\\.[a-z]+@mail\\.mcgill\\.ca$";
        if (email == null || !email.matches(mcgillEmailPattern)) {
            throw new IllegalArgumentException("Email must be a valid McGill email (format: lower.lower@mail.mcgill.ca).");
        }
    }
    
}