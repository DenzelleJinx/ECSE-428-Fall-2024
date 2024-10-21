package HouseIt.app.dao;

import HouseIt.app.model.Student;
import HouseIt.app.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentByID(String id);

    Student findStudentByUsername(String username);

    Student findStudentByEmail(String email);

    List<Student> findStudentByPassword(String password);

    List<Student> findStudentByStatus(AccountStatus status);

    List<Student> findStudentByRating(float rating);

}