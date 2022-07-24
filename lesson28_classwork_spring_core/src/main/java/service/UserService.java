package service;

import model.User;

public interface UserService {
    void save(User user);
    boolean isAuth(String username, String password);
    void logout(String username);
}
