package HouseIt.dao;

import HouseIt.model.Administrator;
import HouseIt.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdministratorDAO extends CrudRepository<Administrator, Integer> {

    Administrator findAdministratorById(int id);

    Administrator findAdministratorByUsername(String username);

    Administrator findAdministratorByEmail(String email);
    
    Administrator findAdministratorByPassword(String password);

    List<Administrator> findAdministratorByStatus(AccountStatus status);

    List<Administrator> findAdministratorByRating(float rating);

}