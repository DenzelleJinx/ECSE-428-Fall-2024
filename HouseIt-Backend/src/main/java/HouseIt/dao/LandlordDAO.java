package HouseIt.dao;

import HouseIt.model.Landlord;
import HouseIt.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LandlordDAO extends CrudRepository<Landlord, Integer> {

    Landlord findLandlordById(int id);

    Landlord findLandlordByUsername(String username);

    Landlord findLandlordByEmail(String email);

    List<Landlord> findLandlordByPassword(String password);

    List<Landlord> findLandlordByStatus(AccountStatus status);

    List<Landlord> findLandlordByRating(float rating);

    Landlord findLandlordByPhoneNumber(String phoneNumber);
}
