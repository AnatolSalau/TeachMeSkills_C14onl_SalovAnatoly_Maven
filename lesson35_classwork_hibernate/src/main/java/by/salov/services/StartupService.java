package by.salov.services;

import by.salov.models.Adress;
import by.salov.models.Gender;
import by.salov.models.User;
import by.salov.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StartupService {
    private UserRepository userRepository;

    public StartupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User(
                "user_login_2", "user_password_2",
                Gender.MAN,new Adress("Minsk", 12)
        );
        userRepository.save(user);
        userRepository.getById(1);
        userRepository.updateName(1, "NewName");
        System.out.println();
    }
}
