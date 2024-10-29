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

        /**
 * Creates a student
 * @param id
 * @param username
 * @param email
 * @param password
 * @param status
 * @param rating
 * @return created student
 */
@Transactional 
public Student createStudent(int id, String username, String email, String password, AccountStatus status, float rating) {
    if (username == null || username.trim().length() == 0) {
        throw new IllegalArgumentException("Username cannot be empty.");
    }
    
    if (email == null || email.trim().length() == 0) {
        throw new IllegalArgumentException("Email cannot be empty.");
    }
    
    if (!isValidMcGillEmail(email)) {
        throw new IllegalArgumentException("Email must be a valid McGill email (format: lower.lower@mail.mcgill.ca).");
    }
    
    if (password == null || password.trim().length() == 0) {
        throw new IllegalArgumentException("Password cannot be empty.");
    }
    
    if (findUserByEmail(email) != null) {
        throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
    }

    Student student = new Student(id, username, email, password, status, rating);
    
    studentRepository.save(student);
    
    return student;
}
    

    Boolean isValidMcGillEmail(String email){
        //checks if the email is of the form (lower cases).(lower cases)@mail.mcgill.ca
        String accountEmail = "^[a-z]+\\.[a-z]+@mail\\.mcgill\\.ca$";

        return email != null && email.matches(accountEmail);
    }
}
