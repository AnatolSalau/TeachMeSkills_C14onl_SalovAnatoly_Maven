package db;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class DBStoreUsersSingleton {
    Map<String, User> users = new HashMap<String, User>();
    private static DBStoreUsersSingleton dbStoreUsersSingleton;

    private DBStoreUsersSingleton() {
    }

    public static DBStoreUsersSingleton getDbStoreUsersSingleton() {
        if (dbStoreUsersSingleton != null) {
            return dbStoreUsersSingleton;
        } else {
            synchronized (DBStoreUsersSingleton.class) {
                if (dbStoreUsersSingleton == null) {
                    dbStoreUsersSingleton = new DBStoreUsersSingleton();
                }
                return dbStoreUsersSingleton;
            }
        }
    }
    public Map<String, User> getUsers() {
        return users;
    }
}
