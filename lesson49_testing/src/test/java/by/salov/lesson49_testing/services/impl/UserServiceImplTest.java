package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTE! By tests we can control changes in our code, because if we change cod so
 * it doesn't match, then the tests will show it
 * .mock - return default value for primitives
 * Data Type	Default Value (for fields)
 * byte	                    0
 * short	                0
 * int	                    0
 * long	                    0L
 * float	                0.0f
 * double	                0.0d
 * char	                    '\u0000'
 * String (or any object)  	null
 * boolean	                false
 * .spy - inherited from our object
 */

/*Add Mockito in JUnit by Spring annotations*/
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    /*@Mock*/
    @Spy
    private UserRepository mockUserRepository;
    /*@Mock*/
    @Spy
    private UserValidationImpl mockUserValidation;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    /**
     * Put often used classes in separate fields
     * initialize that fields with @BeforeEach
     */
    @BeforeEach
    public void beforeEach() {
/*        mockUserRepository = Mockito.mock(UserRepository.class);
        mockUserValidation = Mockito.mock(UserValidationImpl.class);*/
/*        userServiceImpl = new UserServiceImpl(
                mockUserRepository, mockUserValidation);*/
    }

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
/*        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        UserValidationImpl mockUserValidation = Mockito.mock(UserValidationImpl.class);*/
/*        UserServiceImpl userService = new UserServiceImpl(
                mockUserRepository, mockUserValidation);*/

        /*describing behavior UserValidationImpl by mockito*/
        Mockito.when(mockUserValidation.isValidUserForSave(user)).thenReturn(true);

        /*describe behavior mockUserRepository by mockito*/
        Mockito.when(mockUserRepository.save(user)).thenReturn(userFromDB);
        //when
        User savedUser = userServiceImpl.saveUser(user);

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
/*        UserRepository mockitoUserRepository = Mockito.mock(UserRepository.class);*/
        /*UserValidationImpl by Mockito*/
/*        UserValidationImpl mockitoUserValidationImpl = Mockito.mock(UserValidationImpl.class);*/
/*
        UserValidationImpl mockitoUserValidationImpl = Mockito.spy(UserValidationImpl.class);
*/
        /*Service for save*/
/*        UserServiceImpl userServiceImpl = new UserServiceImpl(
                mockUserRepository,mockUserValidation);*/

        /*Describe behavior*/
        Mockito.when(
                mockUserValidation.isValidUserForSave(userWithId)).thenReturn(false);
        /*Also we can call real method by Mockito.thenCallRealMethod*/
        Mockito.when(
                mockUserValidation.isValidUserForSave(userWithId)).thenCallRealMethod();
        Mockito.when(mockUserRepository.getById(3L)).thenReturn(userFromDB);

        //when and then
        Assertions.assertThrows(UserNotExist.class,()-> {
            userServiceImpl.saveUser(userWithId);
        } );
        /*Check quantity of method call*/
        Mockito.verify(mockUserRepository,Mockito.times(0))
                .save(userWithId);
    }

    @Test
    void saveDefaultUserWithLogin() {
        //given
        String login = "Vasya";
    /*    UserRepository mockUserRepository = Mockito.spy(UserRepository.class);
        UserValidationImpl mockUserValidationImpl = Mockito.spy(UserValidationImpl.class);*/
/*        UserServiceImpl mockitoUserServiceImpl = new UserServiceImpl(
                mockUserRepository,mockUserValidation);*/

        /*Create ArgumentCaptore that will make photo our arguments in methods*/
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        //when
        userServiceImpl.saveDefaultUserWithLogin(login);

        //Check with ArgumentCaptore
        Mockito.verify(mockUserRepository).save(argumentCaptor.capture());

        //then

        /*Check that UserRepository.class was run*/
        Mockito.verify(mockUserRepository,Mockito.times(1))
                .save(Mockito.any());

        /*Get argument (User) by ArgumentCaptor*/
        User userFromCaptor = argumentCaptor.getValue();

        /*Match user.login and user from argument capture */
        Assertions.assertEquals(login, userFromCaptor.getLogin());
        /*match user.password from argumentCapture*/
        Assertions.assertEquals("default", userFromCaptor.getPassword());

        /**
         * We can use library assertj for comparing results, instead of standard Assertions
         */

        org.assertj.core.api.Assertions.assertThat(
                userFromCaptor.getLogin()).isEqualTo("Vasya");

        List<User> userList = List.of(new User(), new User());

        /*comparing size userList*/
        Assertions.assertEquals(2,userList.size());
        /*the same comparing by assertj*/
        org.assertj.core.api.Assertions.assertThat(userList).hasSize(2);

    }


    @Test
    void saveUserWithTwoParams() {
        //given
        String login = "LoginUser";
/*        UserRepository mockUserRepository = Mockito.spy(UserRepository.class);
        UserValidationImpl mockUserValidation = Mockito.spy(UserValidationImpl.class);*/

        //when
        /** .isValidParams(login, Mockito.anyString())) - doesn't work because When using matchers, all arguments have to be provided by matchers.
        *   .isValidParams(Mockito.anyString(), Mockito.anyString())) = it is work
        *    so use Mockito.eq(parameter)
        */
/*        Mockito.when(mockUserValidation
                .isValidParams(login, Mockito.anyString())).thenReturn(true);*/

        Mockito.when(mockUserValidation
                .isValidParams(Mockito.eq(login), Mockito.anyString())).thenReturn(true);

/*
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository, mockUserValidation);
*/

        //then
        userServiceImpl.saveUserWithTwoParams(login);
    }
}