package by.salov.lesson49_testing.repository;

import by.salov.lesson49_testing.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Spring JPA repository
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    /*Load User repository*/
    @Autowired
    private UserRepository userRepository;

    private List<User> userList = new ArrayList<>();

    User user1 = User.builder()
            .login("Login1")
            .password("Password1")
            .build();
    User user2 = User.builder()
            .login("Login2")
            .password("Password2")
            .build();

    /*Delete all from database*/
    @BeforeEach
    private void before() {
        userRepository.deleteAll();

        userRepository.save(user1);
        userRepository.save(user2);

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void testFindUserByLogin() {
        //given

        //when
        User userOne = userRepository.save(user1);
        User userTwo = userRepository.save(user2);

        User userByLogin1 = userRepository.findUserByLogin("Login1");
        User userByLogin2 = userRepository.findUserByLogin("Login2");

        //then
        Assertions.assertThat(userOne).isEqualTo(user1);
        Assertions.assertThat(userTwo).isEqualTo(user2);

        Assertions.assertThat(userByLogin1).isEqualTo(user1);
        Assertions.assertThat(userByLogin2).isEqualTo(user2);
    }

    @Test
    void testFindAll() {
        List<User> users = userRepository.saveAll(userList);

        List<User> all = userRepository.findAll();

        Assertions.assertThat(all).isEqualTo(users);
        Assertions.assertThat(all).hasSize(2);
    }
}