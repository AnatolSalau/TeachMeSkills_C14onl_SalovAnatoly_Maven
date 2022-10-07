package by.salov.repository;

import by.salov.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findMyUserByLogin(String login);
}
