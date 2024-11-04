package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.dto.users.UserListDTO;
import HouseIt.dto.users.UserDTO;
import HouseIt.service.UserService;
import HouseIt.model.User;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/users", "/users/"})
    public UserListDTO getAllUsers() {
        System.out.println("Getting all users");
        List<UserDTO> userResponseDTOs = new ArrayList<>();
        UserDTO userDTO;
        for (User user : userService.getAllUsers()) {
            userDTO = new UserDTO(user);
            userResponseDTOs.add(userDTO);
            System.out.println(userDTO);
        }
        return new UserListDTO(userResponseDTOs);
    }
}
