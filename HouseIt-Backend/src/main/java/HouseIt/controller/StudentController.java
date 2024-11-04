package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import HouseIt.dto.StudentDTO;
import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;
import HouseIt.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

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
}
