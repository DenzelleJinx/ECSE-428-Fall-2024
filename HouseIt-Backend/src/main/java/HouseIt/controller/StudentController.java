package HouseIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import HouseIt.dto.StudentDTO;
import HouseIt.model.Student;
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
    
}
