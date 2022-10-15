package by.salov.lesson49_testing.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class MainService {

    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    @Autowired
    private UserService userService;

    @PostConstruct
    public void startup() {
        logger.info("MainService.class logger");
        userService.setUserList(Arrays.asList(new User("One"), new User("Two"), new User("Three")));
        userService.printUsers();
    }
}
