package HouseIt.app.dao;

import HouseIt.app.model.Student;
import HouseIt.app.model.User.AccountStatus;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentByID(String Id);

    Student findStudentByUsername(String Username);

    Student findStudentByEmail(String Email);

    List<Student> findStudentByPassword(String password);

    List<Student> findStudentByStatus(AccountStatus Status);

    List<Student> findStudentByRating(float Rating);

}