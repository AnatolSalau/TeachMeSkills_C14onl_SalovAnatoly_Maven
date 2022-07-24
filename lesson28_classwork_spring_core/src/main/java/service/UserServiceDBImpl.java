package service;

import model.DataBase;
import model.User;

public class UserServiceDBImpl implements UserService{
    private DataBase dataBase;

    public UserServiceDBImpl(DataBase store) {
        this.dataBase = store;
    }

    @Override
    public void save(User user) {
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
