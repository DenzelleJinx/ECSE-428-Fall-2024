package HouseIt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.StudentDAO;
import HouseIt.dto.users.StudentDTO;
import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;
import HouseIt.utils.ValidationUtils;

@Service
public class StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Transactional
    public Student createStudent(String username, String password, String email) {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        
        if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        
        if (!isValidMcGillEmail(email)) {
            throw new IllegalArgumentException("Email must be a valid McGill email (format: lower.lower@mail.mcgill.ca).");
        }
        
        if (password == null || password.trim().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        if (studentDAO.findStudentByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists in the system. Please enter another username.");
        }
        
        if (studentDAO.findStudentByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
        }

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

    Boolean isValidMcGillEmail(String email){
        //checks if the email is of the form (lower cases).(lower cases)@mail.mcgill.ca
        String accountEmail = "^[a-z]+\\.[a-z]+@mail\\.mcgill\\.ca$";

        return email != null && email.matches(accountEmail);
    }

    @Transactional
    public Student updateStudent(int id, String username, String password, String email, AccountStatus status, Float rating) {
        // Retrieve existing student
        Student student = studentDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Update fields if they are provided
        if (username != null && !username.trim().isEmpty()) {
            student.setUsername(username);
        }

        if (password != null && password.trim().length() >= 6) {
            student.setPassword(password);
        } else if (password != null) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        if (email != null && !email.trim().isEmpty()) {
            if (!isValidMcGillEmail(email)) {
                throw new IllegalArgumentException("Email must be a valid McGill email (format: lower.lower@mail.mcgill.ca).");
            }
            if (!email.equals(student.getEmail()) && studentDAO.findStudentByEmail(email) != null) {
                throw new IllegalArgumentException("Email already exists in the system. Please enter another email.");
            }
            student.setEmail(email);
        }

        if (status != null) {
            student.setStatus(status);
        }

        if (rating != null && rating >= 0.0f) {
            student.setRating(rating);
        }

        return studentDAO.save(student);
    }
    
    @Transactional
    public Student resetPassword(String email, String newPassword) {
        System.out.println("resetPassword, Email: " + email);
        Student student = studentDAO.findStudentByEmail(email);
        if (student == null) {
            throw new IllegalArgumentException("No student found with the provided email.");
        }

        ValidationUtils.validatePassword(newPassword);
        student.setPassword(newPassword); // Ideally hash the password here
        System.out.println("resetPassword, New Password: " + newPassword);
        return studentDAO.save(student);
    }

    public Student existsByEmail(String email) {
        System.out.println("existsByEmail, Email: " + email);
        Student student = studentDAO.findStudentByEmail(email);
        if (student == null) {
            System.out.println("existsByEmail, Student: null");
        }
        return student;
    }

}
