package by.salov.repository;

import by.salov.models.User;

public interface UserRepository {
    User getById(int id);
    void save(User user);
    void updateName(int id, String newName);
}
