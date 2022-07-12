package dao;

import db.DBStoreUsersSingleton;
import entity.User;



public class DBUsersConnect {
    private final DBStoreUsersSingleton dbStoreUsersSingleton = DBStoreUsersSingleton.getDbStoreUsersSingleton();
    public User addUser(User newUser) {
        dbStoreUsersSingleton.getUsers().put(newUser.getLogin(),newUser);
        return dbStoreUsersSingleton.getUsers().get(newUser.getLogin());
    }
    public User getUser(User user) {
        return dbStoreUsersSingleton.getUsers().getOrDefault(user.getLogin(), null);
    }
    public User getUser(String login) {
        return  dbStoreUsersSingleton.getUsers().getOrDefault(login,null);
    }
}
