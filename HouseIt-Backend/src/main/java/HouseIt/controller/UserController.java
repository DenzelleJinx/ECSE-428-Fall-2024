package HouseIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import HouseIt.dto.users.UserListDTO;
import HouseIt.dto.users.UserDTO;
import HouseIt.service.UserService;
import HouseIt.model.User;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/users", "/users/"})
    public UserListDTO getAllUsers() {
        List<UserDTO> userResponseDTOs = new ArrayList<>();
        UserDTO userDTO;
        for (User user : userService.getAllUsers()) {
            userDTO = new UserDTO(user);
            userResponseDTOs.add(userDTO);
        }
        return new UserListDTO(userResponseDTOs);
    }
    
    @GetMapping(value = {"/users/{email}", "/users/{email}/"})
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return new UserDTO(user);
    }
}
