package by.salov.services;

import by.salov.entity.User;
import by.salov.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {

    @Autowired
    DBUserRepository dbUserRepository;

    public boolean saveUsersInDB(User... users) {
        for (User user : users) {
            dbUserRepository.save(user);
                if (!dbUserRepository.existsById(user.getId())) {
                    return false;
                }
        }
        return true;
    }

    public User getUserFromDBByID(Long id) {
        Optional<User> byId = dbUserRepository.findById(id);
        User user = byId.orElse(null);
        return user;
    }
    
    public User deleteFromBDByID(Long id)  {
        User userFromDBByID = getUserFromDBByID(id);
        User cloneUserFromDBByID = null;
        try {
            if (dbUserRepository.existsById(id)) {
                cloneUserFromDBByID = (User) userFromDBByID.clone();
                dbUserRepository.deleteById(id);
                return cloneUserFromDBByID;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneUserFromDBByID;
    }

    public List<User> getUsersByName (Integer age) {
        List<User> users = dbUserRepository.getUsersByAge(age);
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }
}
