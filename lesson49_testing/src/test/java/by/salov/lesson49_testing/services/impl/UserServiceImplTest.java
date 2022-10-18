package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTE! By tests we can control changes in our code, because if we change cod so
 * it doesn't match, then the tests will show it
 * .mock - return default
 */
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

    /*Testing throws errors by Assertions.assertThrows*/
    @Test
    void saveNotValidUser() throws UserAllreadyExistExeption, UserNotExist {
        //given
        /*User for save*/
        User userWithId = User.builder()
                .id(1L)
                .login("first")
                .password("123456")
                .build();
        /*User from DB*/
        User userFromDB = User.builder()
                .id(1L)
                .login("first")
                .password("123456")
                .build();
        /*DAO UserRepository by Mockito*/
        UserRepository mockitoUserRepository = Mockito.mock(UserRepository.class);
        /*UserValidationImpl by Mockito*/
/*        UserValidationImpl mockitoUserValidationImpl = Mockito.mock(UserValidationImpl.class);*/
        UserValidationImpl mockitoUserValidationImpl = Mockito.spy(UserValidationImpl.class);
        /*Service for save*/
        UserServiceImpl userServiceImpl = new UserServiceImpl(
                mockitoUserRepository,mockitoUserValidationImpl);

        /*Describe behavior*/
        Mockito.when(
                mockitoUserValidationImpl.isValidUserForSave(userWithId)).thenReturn(false);
        /*Also we can call real method by Mockito.thenCallRealMethod*/
        Mockito.when(
                mockitoUserValidationImpl.isValidUserForSave(userWithId)).thenCallRealMethod();
        Mockito.when(mockitoUserRepository.getById(3L)).thenReturn(userFromDB);

        //when and then
        Assertions.assertThrows(UserNotExist.class,()-> {
            userServiceImpl.saveUser(userWithId);
        } );
        /*Check quantity of method call*/
        Mockito.verify(mockitoUserRepository,Mockito.times(0))
                .save(userWithId);
    }
}