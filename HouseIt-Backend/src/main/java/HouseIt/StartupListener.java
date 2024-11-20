package HouseIt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import HouseIt.service.UserService;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private UserService userService;

    public StartupListener(UserService userService) {
        this.userService = userService;
        System.out.println("StartupListener initialized.");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            userService.createAdmin();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
