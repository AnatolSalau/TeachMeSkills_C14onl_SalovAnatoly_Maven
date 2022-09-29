package by.salov.repository;

import by.salov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DBUserRepository extends JpaRepository<User,Long> {

}
