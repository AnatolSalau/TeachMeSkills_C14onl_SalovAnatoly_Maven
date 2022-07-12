package services;

import dao.DBUsersConnect;
import entity.User;
import enums.Role;

public class ValidationUser {
    private DBUsersConnect dbUsersConnect = new DBUsersConnect();

    public boolean isCorrectLoginPasswordRole (String login, String password, Role role) {
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

}
