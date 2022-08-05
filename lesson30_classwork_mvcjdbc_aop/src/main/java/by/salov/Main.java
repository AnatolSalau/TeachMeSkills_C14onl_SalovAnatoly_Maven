package by.salov;

import by.salov.models.User;
import by.salov.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("by.salov");
        UserRepository bean = applicationContext.getBean(UserRepository.class);
        List<User> allUsers = null;
        try {
            bean.save(new User(UUID.randomUUID(),"FirstName","FirstPassword"));
            allUsers = bean.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
