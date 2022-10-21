package by.salov.lesson49_testing.repository;

import by.salov.lesson49_testing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByLogin(String login);
}
