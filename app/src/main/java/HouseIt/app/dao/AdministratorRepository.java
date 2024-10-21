package HouseIt.app.dao;

import HouseIt.app.model.Administrator;
import HouseIt.app.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

    Administrator findAdministratorByID(String Id);

    Administrator findAdministratorByUsername(String Username);

    Administrator findAdministratorByEmail(String Email);
    
    Administrator findAdministratorByPassword(String password);

    List<Administrator> findAdministratorByStatus(AccountStatus Status);

    List<Administrator> findAdministratorByRating(float Rating);

}