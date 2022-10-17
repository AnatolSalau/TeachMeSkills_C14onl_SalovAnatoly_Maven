package by.salov.lesson49_testing.services;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.impl.UserValidationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserValidationImplTest {

    @DisplayName("Valid User validation all  ")
    @Test
    public void testValidUserForSave() {
        //given
        UserValidationImpl userValidation = new UserValidationImpl();
        User userWithLogin = User.builder().login("firstUser").build();
        User userWithLoginPassword = User.builder().login("firstUser").password("firstPassword").build();
        User userWithLoginPasswordId = User.builder().login("firstUser").password("firstPassword").id(1L).build();
        //when
        boolean isValidLogin = userValidation.isValidUserForSave(userWithLogin);
        boolean isValidLoginPassword = userValidation.isValidUserForSave(userWithLoginPassword);
        boolean isValidLoginPasswordId = userValidation.isValidUserForSave(userWithLoginPasswordId);
        //then
        Assertions.assertFalse(isValidLogin);
        Assertions.assertTrue(isValidLoginPassword);
        Assertions.assertFalse(isValidLoginPasswordId);
    }

    @DisplayName("validate save with empty login  ")
    @Test
    public void givenNoValidUser_whenLoginIsempty_thenFalse() {
        //given
        User userEmptyLogin = User.builder().login(" ").build();
        UserValidationImpl userValidation = new UserValidationImpl();
        //when
        boolean isValidLogin = userValidation.isValidUserForSave(userEmptyLogin);
        //then
        Assertions.assertFalse(isValidLogin);
    }
}
