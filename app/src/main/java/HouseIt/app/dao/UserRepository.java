package HouseIt.app.dao;

import HouseIt.app.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByID(int Id);

    User findUserByUsername(String Username);

    User findUserByEmail(String Email);

    User findUserByPassword(String password);

    User findUserByStatus(String Status);

    User findUserByRating(float Rating);
}