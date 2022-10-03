package by.salov.repository;

import by.salov.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
     User findByLogin(String login);
}
