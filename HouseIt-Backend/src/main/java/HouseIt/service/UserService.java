package HouseIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import HouseIt.model.User;
import HouseIt.utils.Helper;
import HouseIt.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Transactional
    public List<User> getAllUsers() {
        return Helper.toList(userDAO.findAll());
    }
}
