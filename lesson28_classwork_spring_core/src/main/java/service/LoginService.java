package service;

import model.User;

public interface LoginService {
    void save (User user);
    boolean isAuth(String username, String password);
    void logout(String username);
    default void init() {
        System.out.println("Init metod LoginService");
    }
    default void destroy() {
        System.out.println("Destroy metod LoginService");
    }
}
