package by.salov.lesson29_spring_aop.services;

import by.salov.lesson29_spring_aop.dao.UserRepository;
import by.salov.lesson29_spring_aop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Service
public class SturtupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public SturtupService() {
    }

    @PostConstruct
    public void start() throws SQLException {
        User user = new User("anatoly2", "Anatoly");
        //User savedUser = userRepository.saveUser(user);
        //Boolean anatolyIsExist = userRepository.deleteUserByLogin("anatoly2");
        Boolean anatoly2 = userRepository.userIsExistByLogin("anatoly2");
        System.out.println(anatoly2);
    }
}
