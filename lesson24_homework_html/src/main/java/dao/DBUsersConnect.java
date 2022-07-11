package dao;

import db.DBStoreUsersSingleton;
import entity.User;

public class DBUsersConnect {
    private DBStoreUsersSingleton dbStoreUsersSingleton = DBStoreUsersSingleton.getDbStoreUsersSingleton();
    public User addUser(User newUser) {
        dbStoreUsersSingleton.getUsers().put(newUser.getLogin(),newUser);
        return dbStoreUsersSingleton.getUsers().get(newUser.getLogin());
    }
}
