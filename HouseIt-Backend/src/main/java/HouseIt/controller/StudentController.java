package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import HouseIt.dto.ListingDTO;
import HouseIt.dto.users.StudentDTO;
import HouseIt.model.Listing;
import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;
import HouseIt.service.ListingService;
import HouseIt.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    ListingService listingservice;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<StudentDTO> createStudent(
        @RequestParam String username,
        @RequestParam String password, 
        @RequestParam String email) {

        Student student = studentService.createStudent(username, password, email);
        StudentDTO dto = studentService.convertToDTO(student);
        return ResponseEntity.created(null).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
        @PathVariable int id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) AccountStatus status,
        @RequestParam(required = false) Float rating) {

        // Call the service to update student details
        Student updatedStudent = studentService.updateStudent(id, username, password, email, status, rating);
        StudentDTO updatedDto = studentService.convertToDTO(updatedStudent);

        return ResponseEntity.ok(updatedDto);
    }

    @PutMapping(value = "/{id}/favorites")
    public ResponseEntity<?> addFavoriteListing(
        @PathVariable int id,
        @RequestParam int listingId) {
        // Call the service to add the listing to the student's favorites
        try {
            Student updatedStudent = studentService.addFavoritedListing(id, listingId);
            StudentDTO updatedDto = studentService.convertToDTO(updatedStudent);
            return ResponseEntity.ok(updatedDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}/favorites")
    public ResponseEntity<?> removeFavoriteListing(
        @PathVariable int id,
        @RequestParam int listingId) {

        // Call the service to remove the listing from the student's favorites
        try {
            Student updatedStudent = studentService.removeFavoritedListing(id, listingId);
            StudentDTO updatedDto = studentService.convertToDTO(updatedStudent);
            return ResponseEntity.ok(updatedDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}/favorites")
    public ResponseEntity<List<ListingDTO>> getFavoriteListings(
        @PathVariable int id) {

        // Call the service to get the student's favorite listings
        List<Listing> favoritedListings = studentService.getFavoritedListings(id);
        List<ListingDTO> favoritedListingsDTO = new ArrayList<>();
        for (Listing listing : favoritedListings) {
            favoritedListingsDTO.add(listingservice.convertToDTO(listing));
        }
        return ResponseEntity.ok(favoritedListingsDTO);
    }




}
