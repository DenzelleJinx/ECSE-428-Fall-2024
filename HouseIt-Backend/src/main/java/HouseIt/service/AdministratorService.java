package HouseIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.LandlordDAO;
import HouseIt.model.Landlord;
import HouseIt.model.User.AccountStatus;

@Service
public class AdministratorService {

    @Autowired
    private LandlordDAO landlordDAO;

    @Transactional
    public void verifyLandlord(int landlordId) {
        Landlord landlord = landlordDAO.findLandlordById(landlordId);
        if (landlord == null) {
            throw new IllegalArgumentException("No such landlord with id: " + landlordId);
        }
        if (landlord.getStatus() == AccountStatus.ACTIVE) {
            throw new IllegalArgumentException("This account has already been approved");
        }

        landlord.setStatus(AccountStatus.ACTIVE);
        landlordDAO.save(landlord);

        // TODO: Notify the landlord
    }

}
