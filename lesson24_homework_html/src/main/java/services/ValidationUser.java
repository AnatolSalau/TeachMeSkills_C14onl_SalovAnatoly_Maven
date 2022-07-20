package services;

import dao.DBUsersConnect;
import entity.User;
import enums.Role;

public class ValidationUser {
    private DBUsersConnect dbUsersConnect = new DBUsersConnect();

    public boolean isCorrect(String login, String password, Role role) {
        User userInDB = null;
        userInDB = dbUsersConnect.getUser(login);

        if (userInDB == null) {
            return false;
        }

        if (!userInDB.getLogin().equals(login) || !userInDB.getPassword().equals(password)) {
            return false;
        }

        if (userInDB.getRole() != role) {
            return false;
        }
        return true;
    }
    public boolean hasUser(String login) {
        if (dbUsersConnect.getUser(login) != null){
            return true;
        } else return false;
    }
}
