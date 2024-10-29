package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.model.Student;
import HouseIt.dao.StudentDAO;
import HouseIt.model.User.AccountStatus;

@SpringBootTest
public class StudentDAOTests {

    @Autowired
    private StudentDAO studentDAO;

    private String username = "testStudent";
    private String email = "test@mail.mcgill.ca";
    private String password = "password";
    private AccountStatus status = AccountStatus.ACTIVE;
    private float rating = 5.0f;
    private Student student;

    @AfterEach
    public void tearDown() {
        studentDAO.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setUsername(username);
        student.setEmail(email);
        student.setPassword(password);
        student.setStatus(status);
        student.setRating(rating);

        studentDAO.save(student);
    }

    @Test
    public void testFindStudentByUsername() {
        Student foundStudent = studentDAO.findStudentByUsername(username);

        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindStudentByEmail() {
        Student foundStudent = studentDAO.findStudentByEmail(email);

        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindStudentsByStatus() {
        Student foundStudent = studentDAO.findStudentByStatus(status).get(0);

        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindStudentsByRating() {
        Student foundStudent = studentDAO.findStudentByRating(rating).get(0);

        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindStudentById() {
        Student foundStudent = studentDAO.findStudentById(student.getId());

        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }
}
