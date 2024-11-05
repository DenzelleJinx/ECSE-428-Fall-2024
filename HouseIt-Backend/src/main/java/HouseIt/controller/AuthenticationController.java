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
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
        } catch (AuthenticationException exception) {
            if (exception.getMessage().equals("User does not exist.")) {
                return new ResponseEntity<>("User with email " + loginRequest.getEmail() + " does not exist.", HttpStatus.UNAUTHORIZED);
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
            return new ResponseEntity<>("User is registered successfully!", HttpStatus.BAD_REQUEST);
        } else {
            try {
                landlordService.createLandlord(signUpDto.getUsername(), signUpDto.getPassword(), signUpDto.getEmail(), signUpDto.getPhoneNumber());
            } catch (Exception e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
        }
    }
}