package by.salov.services;

import by.salov.entity.User;
import by.salov.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public User getActiveUserByName (String name, Boolean isActive) {
        return dbUserRepository.findUserByNameAndIsActive(name, isActive).orElse(null);
    }

    public List<User> getUsersByAgeNative(Integer age) {
        List<User> users = dbUserRepository.getUsersByAgeNative(age);
        return users;
    }

    public int usersCount() {
        int count = dbUserRepository.countUsers();
        return count;
    }

    public List<User> getOnlyActiveNull() {
        List<User> users = dbUserRepository.getOnlyActiveNull();
        return users;
    }

    public void updateIsActiveByID(Boolean isActive, Long id) {
        dbUserRepository.updateIsActiveByID(isActive,id);
    }

    public Page<User> getPageUsers(int page, int size) {
        Page<User> all = dbUserRepository.findAll(getPageOf(page, size));
        return all;
    }

    private PageRequest getPageOf(int page, int size) {
        /*Sort request
        * asc - first to last
        * desc - last to first
        * */
        Sort sort = Sort.by(Sort.Order.asc("id"));
        PageRequest of = PageRequest.of(page,size,sort);
        System.out.println(of);
        return of;
    }
}
