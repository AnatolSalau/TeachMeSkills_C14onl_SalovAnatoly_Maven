package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void saveValidUser() throws UserAllreadyExistExeption, UserNotExist {
        //given
        User user = User.builder()
                .login("first")
                .password("123456")
                .build();
        User userFromDB = User.builder()
                .id(1L)
                .login("first")
                .password("123456")
                .build();
        /*Generate user repository with Mockito*/
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        UserValidationImpl mockUserValidation = Mockito.mock(UserValidationImpl.class);
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository, mockUserValidation);

        /*describing behavior UserValidationImpl by mockito*/
        Mockito.when(mockUserValidation.isValidUserForSave(user)).thenReturn(true);

        /*describe behavior mockUserRepository by mockito*/
        Mockito.when(mockUserRepository.save(user)).thenReturn(userFromDB);
        //when
        User savedUser = userService.saveUser(user);

        //then
        Assertions.assertNotNull(savedUser);

    }
}