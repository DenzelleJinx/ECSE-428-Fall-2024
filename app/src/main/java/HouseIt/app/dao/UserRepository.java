package HouseIt.app.dao;

import HouseIt.app.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByID(int id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByPassword(String password);

    User findUserByStatus(String status);

    User findUserByRating(float rating);
}