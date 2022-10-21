package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.UserValidation;
import org.springframework.stereotype.Service;

@Service
public class UserValidationImpl implements UserValidation {
    @Override
    public boolean isValidUserForSave(User user) {
        Long id = user.getId();
        if (id != null) return false;

        String login = user.getLogin();
        if (login == null || login.isBlank()) return false;

        String password = user.getPassword();
        if (password == null || password.isBlank()) return false;

        return true;
    }

    @Override
    public boolean isValidUserForUpdate(User user) {
        Long id = user.getId();
        if (id != null) return false;

        String login = user.getLogin();
        if (login == null || login.isBlank()) return false;

        String password = user.getPassword();
        if (password == null || password.isBlank()) return false;

        return true;
    }

    @Override
    public boolean isValidUserForDelete(User user) {
        Long id = user.getId();
        if (id != null) return false;

        String login = user.getLogin();
        if (login == null || login.isBlank()) return false;

        String password = user.getPassword();
        if (password == null || password.isBlank()) return false;

        return true;
    }

    @Override
    public boolean isValidParams(String login, String password) {
        if(login == null || login.isBlank() || password.isBlank()) {
            return false;
        }
        return true;
    }
}
