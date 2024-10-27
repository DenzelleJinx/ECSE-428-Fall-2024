package HouseIt.app.dao;

import HouseIt.app.model.Landlord;
import HouseIt.app.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LandlordRepository extends CrudRepository<Landlord, Integer> {

    Landlord findLandlordByID(String id);

    Landlord findLandlordByUsername(String username);

    Landlord findLandlordByEmail(String email);

    List<Landlord> findLandlordByPassword(String password);

    List<Landlord> findLandlordByStatus(AccountStatus status);

    List<Landlord> findLandlordByRating(float rating);

    Landlord findLandlordByPhoneNumber(String phoneNumber);
}
