package by.salov.repository;

import by.salov.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*Default Spring JpaRepository*/
public interface UserRepository extends JpaRepository<User,Long> {
     User findByLogin(String login);
}
