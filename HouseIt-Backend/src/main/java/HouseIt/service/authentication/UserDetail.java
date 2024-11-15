package HouseIt.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import HouseIt.dao.UserDAO;
import HouseIt.model.User;

@Service
public class UserDetail implements UserDetailsService {
    @Autowired  
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userDAO.findUserByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User does not exist.");
        }
        List<GrantedAuthority> authorities  = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getClass().getName()));
    
        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
        
    }
}