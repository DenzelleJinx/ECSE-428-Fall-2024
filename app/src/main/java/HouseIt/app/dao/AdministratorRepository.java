package HouseIt.app.dao;

import HouseIt.app.model.Administrator;
import HouseIt.app.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

    Administrator findAdministratorByID(String id);

    Administrator findAdministratorByUsername(String username);

    Administrator findAdministratorByEmail(String email);
    
    Administrator findAdministratorByPassword(String password);

    List<Administrator> findAdministratorByStatus(AccountStatus status);

    List<Administrator> findAdministratorByRating(float rating);

}