package by.salov.repository;

import by.salov.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//SpringBoot DataJPA already contains interface JpaRepository
//with CRUD metods
//we can easy extend this Interface : JpaRepository<ObjectWithWillWork,IDThisObject>

public interface UserRepository extends JpaRepository<UserCustom, Long> {

    List<UserCustom> getByLogin(String login);
    List<UserCustom> getAllByIsActiveIsTrue();
    Optional<UserCustom> findByLoginAndPassword(String login, String password);
}
