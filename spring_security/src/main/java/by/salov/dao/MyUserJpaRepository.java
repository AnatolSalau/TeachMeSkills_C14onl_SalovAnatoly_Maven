package by.salov.dao;

import by.salov.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserJpaRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findMyUserByLogin(String login);
}
