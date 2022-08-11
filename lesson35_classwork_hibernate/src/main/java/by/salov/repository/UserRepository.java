package by.salov.repository;

import by.salov.models.User;

public interface UserRepository {
    User getById(String login);
}
