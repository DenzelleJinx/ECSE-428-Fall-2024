package HouseIt.dao;

import HouseIt.model.Student;
import HouseIt.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentDAO extends CrudRepository<Student, Integer> {

    Student findStudentById(int id);

    Student findStudentByUsername(String username);

    Student findStudentByEmail(String email);

    List<Student> findStudentByPassword(String password);

    List<Student> findStudentByStatus(AccountStatus status);

    List<Student> findStudentByRating(float rating);
}