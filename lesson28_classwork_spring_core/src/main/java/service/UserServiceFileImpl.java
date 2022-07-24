package service;

import model.DataBase;
import model.User;

public class UserServiceFileImpl implements UserService{
    private DataBase dataBase;

    public UserServiceFileImpl(DataBase store) {
        this.dataBase = store;
    }

    @Override
    public void save(User user) {
        System.out.println("Using file storage");
        dataBase.add(user);
    }

    @Override
    public boolean isAuth(String username, String password) {
        return false;
    }

    @Override
    public void logout(String username) {

    }
}
