package by.salov.repository;

import by.salov.entity.User;
import by.salov.entity.projections.UserProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public interface DBUserRepository extends JpaRepository<User,Long> {
    List<User> getUsersByAge(Integer age);
    Optional<User> findUserByNameAndIsActive (String name, Boolean isActive);

    /*Native query select list users by age*/
    @Query(value="select * from users u where u.age= :age", nativeQuery=true)
    List<User> getUsersByAgeNative(Integer age);

    /*Native query select count users from table*/
    @Query(value = "select  count(*) from users", nativeQuery = true)
    int countUsers();

    /*Named query get all users isActive = null*/
    List<User> getOnlyActiveNull();

    /*Update isActive*/
    /*Have to Turn on modifying for update method*/
    /*Have to Open Transaction by @Transactional for update method*/
    @Transactional
    @Modifying
    @Query("update User u set u.isActive = :isActive where u.id = :id")
    void updateIsActiveByID(Boolean isActive, Long id);

    /*Get list UserProjection*/
    List<UserProjection> findAllByIsActive(Boolean isActive);
}
