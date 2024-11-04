package HouseIt.dao;

import HouseIt.model.User;
import HouseIt.model.User.AccountStatus;

import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {

    User findUserById(int id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByPassword(String password);

    User findUserByStatus(AccountStatus status);

    User findUserByRating(float rating);

    void deleteById(int id);
}