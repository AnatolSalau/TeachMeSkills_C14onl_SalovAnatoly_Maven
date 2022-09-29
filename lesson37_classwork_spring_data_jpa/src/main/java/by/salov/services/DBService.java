package by.salov.services;

import by.salov.entity.User;
import by.salov.entity.UserDTO;
import by.salov.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBService {

    @Autowired
    DBUserRepository dbUserRepository;

    public boolean saveUserInDB(User user) {
        dbUserRepository.save(user);
        boolean isExist = dbUserRepository.existsById(user.getId());
        return isExist;
    }

    public User getUserFromDB(Long id) {
        Optional<User> byId = dbUserRepository.findById(id);
        User user = byId.orElse(null);
        return user;
    }
}
