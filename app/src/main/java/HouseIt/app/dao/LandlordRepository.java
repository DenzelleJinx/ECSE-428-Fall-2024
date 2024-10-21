package HouseIt.app.dao;

import HouseIt.app.model.Landlord;
import HouseIt.app.model.User.AccountStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LandlordRepository extends CrudRepository<Landlord, Integer> {

    Landlord findLandlordByPhoneNumber(String phoneNumber);

    Landlord findLandlordByID(String Id);

    Landlord findLandlordByUsername(String Username);

    Landlord findLandlordByEmail(String Email);

    List<Landlord> findLandlordByPassword(String password);

    List<Landlord> findLandlordByStatus(AccountStatus Status);

    List<Landlord> findLandlordByRating(float Rating);

}
