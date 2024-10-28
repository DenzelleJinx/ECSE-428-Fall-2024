package HouseIt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.StudentDAO;
import HouseIt.dto.StudentDTO;
import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;

@Service
public class StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Transactional
    public Student createStudent(String username, String password, String email) {
        Student newStudent = new Student();
        newStudent.setUsername(username);
        newStudent.setEmail(email);
        newStudent.setPassword(password);
        newStudent.setStatus(AccountStatus.ACTIVE);
        newStudent.setRating(0.0f);
        return studentDAO.save(newStudent);
    }

    public StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setStatus(student.getStatus());
        studentDTO.setRating(student.getRating());
        return studentDTO;
    }
    
}
