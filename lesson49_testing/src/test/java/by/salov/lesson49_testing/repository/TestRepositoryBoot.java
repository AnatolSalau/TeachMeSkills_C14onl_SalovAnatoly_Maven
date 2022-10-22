package by.salov.lesson49_testing.repository;

import by.salov.lesson49_testing.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring boot test repository using Test DB
 * SQL script schema.sql and data.sql will run automatically
 * */
@SpringBootTest
public class TestRepositoryBoot {
    @Autowired
    private UserRepository userRepository;
    @Test
    void getUserTest() {
        User login1 = userRepository.findUserByLogin("login1");
        System.out.println("SpringBootTest");
    }
}
