package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import HouseIt.dto.authentication.LoginRequestDTO;
import HouseIt.dto.authentication.SignupRequestDTO;
import HouseIt.model.Landlord;
import HouseIt.model.Student;
import HouseIt.dto.authentication.ResetPasswordRequestDTO;
import HouseIt.service.LandlordService;
import HouseIt.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    StudentService studentService;

    @Autowired
    LandlordService landlordService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
        } catch (AuthenticationException exception) {
            if (exception.getMessage().equals("User does not exist.")) {
                return new ResponseEntity<>("User with email " + loginRequest.getEmail() + " does not exist.",
                        HttpStatus.UNAUTHORIZED);
            } else if (exception.getMessage().equals("Bad credentials")) {
                return new ResponseEntity<>("Incorrect password, please try again.", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Authentication error.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDTO signUpDto) {
        if (signUpDto.getAccountType().equals("student")) {
            try {
                studentService.createStudent(signUpDto.getUsername(), signUpDto.getPassword(), signUpDto.getEmail());
            } catch (Exception e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
        } else {
            try {
                landlordService.createLandlord(signUpDto.getUsername(), signUpDto.getPassword(), signUpDto.getEmail(),
                        signUpDto.getPhoneNumber());
            } catch (Exception e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordDto) {
        String email = resetPasswordDto.getEmail();
        String username = resetPasswordDto.getUsername();
        String newPassword = resetPasswordDto.getNewPassword();
        String accountType = resetPasswordDto.getAccountType();

        try {
            if ("student".equalsIgnoreCase(accountType)) {
                if (isStudentAndValidUser(email, username)) {
                    System.out.println("Password Reset for Student");
                    studentService.resetPassword(email, newPassword);
                } else {
                    System.out.println("No matching student found: " + email);
                    return ResponseEntity.badRequest().body("No student found with the provided email and username.");
                }
            } else if ("landlord".equalsIgnoreCase(accountType)) {
                if (isLandlordAndValidUser(email, username)) {
                    System.out.println("Password Reset for Landlord");
                    landlordService.resetPassword(email, newPassword);
                } else {
                    System.out.println("No matching landlord found: " + email);
                    return ResponseEntity.badRequest().body("No landlord found with the provided email and username.");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid account type provided.");
            }

            return ResponseEntity.ok("Password has been reset successfully.");
        } catch (Exception e) {
            System.out.println("The error occurred: " + e.getMessage());
            e.printStackTrace(); // More detailed exception logging
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    private boolean isStudentAndValidUser(String email, String username) {
        Student student = studentService.existsByEmail(email);
        if (student != null) {
            System.out.println("Student found: " + student.getUsername());
            return student.getUsername().equals(username);
        }
        System.out.println("No matching student found for email: " + email);
        return false;
    }

    private boolean isLandlordAndValidUser(String email, String username) {
        Landlord landlord = landlordService.existsByEmail(email);
        if (landlord != null) {
            return landlord.getUsername().equals(username);
        }
        System.out.println("No matching landlord found for email: " + email);
        return false;
    }

}