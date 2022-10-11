package by.salov.lesson45_spring_security_roles.dao;

import by.salov.lesson45_spring_security_roles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByLogin (String login);
}
