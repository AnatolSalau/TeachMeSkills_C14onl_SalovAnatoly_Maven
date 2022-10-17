package by.salov.lesson49_testing.services;

import by.salov.lesson49_testing.domain.User;

public interface UserValidation {
    public boolean isValidUserForSave(User user);
    public boolean isValidUserForUpdate(User user);
    public boolean isValidUserForDelete(User user);
}
