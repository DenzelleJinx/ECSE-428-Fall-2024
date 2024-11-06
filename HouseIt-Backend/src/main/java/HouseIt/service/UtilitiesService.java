package HouseIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.UtilitiesDAO;
import HouseIt.model.Utilities;

@Service
public class UtilitiesService {
    
    @Autowired
    private UtilitiesDAO utilitiesDAO;

    @Transactional
    public Utilities createUtilities(Utilities utilities) {
        return utilitiesDAO.save(utilities);
    }

    @Transactional
    public Utilities updateUtilities(Utilities utilities) {
        return utilitiesDAO.save(utilities);
    }
}
