package by.salov.services;

import by.salov.model.UserCustom;
import by.salov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartupService {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        //Create User by lombok.builder
        UserCustom userCustom = UserCustom.builder()
                .login("login1")
                .password("User password 2")
                .isActive(true)
                .birthday(new Date())
                .build();
        UserCustom userCustom2 = UserCustom.builder()
                .login("login2")
                .password("User password 2")
                .isActive(true)
                .birthday(new Date())
                .build();
        userRepository.save(userCustom);
        userRepository.save(userCustom2);
/*        Optional<UserCustom> byId = userRepository.findById(2L);
        System.out.println(byId.get());*/
        List<UserCustom> login2 = userRepository.getByLogin("login2");
        System.out.println(login2);
        /*List<UserCustom> byActiveIsTrue = userRepository.getByActiveIsTrue();
        System.out.println(byActiveIsTrue);*/
        List<UserCustom> allByIsActiveIsTrue = userRepository.getAllByIsActiveIsTrue();
        System.out.println(allByIsActiveIsTrue);
        //userRepository.deleteById(1L);
        Optional<UserCustom> login21 = userRepository.findByLoginAndPassword("login2", "User password 2");
        System.out.println(login21.get());
    }
}
