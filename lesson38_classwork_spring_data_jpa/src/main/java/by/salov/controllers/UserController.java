package by.salov.controllers;

import by.salov.entity.User;
import by.salov.entity.UserDTO;
import by.salov.entity.projections.UserProjection;
import by.salov.services.DBService;
import by.salov.services.DTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    DTOService dtoService;

    @Autowired
    DBService dbService;

    @GetMapping
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView("user.html");
        User user1 = new User("FirstUser", 30, "10-Jun-2013");
        User user2 = new User("SecondUser", 30, "20-Jun-2013");
        User user3 = new User("ThirdUser", 30, "30-Jun-2013");
        User user4 = new User("User4", 30, "10-Jun-2013");
        User user5 = new User("User5", 30, "20-Jun-2013");
        User user6 = new User("User6", 30, "30-Jun-2013");
        User user7 = new User("User7", 30, "10-Jun-2013");
        User user8 = new User("User8", 30, "20-Jun-2013");
        User user9 = new User("User9", 30, "30-Jun-2013");
        User user10 = new User("User10", 30, "10-Jun-2013");
        User user11 = new User("User11", 30, "20-Jun-2013");
        User user12 = new User("User12", 30, "30-Jun-2013");
        boolean isSave = dbService.saveUsersInDB(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12 );
        List<User> usersByName = dbService.getUsersByName(20);
        System.out.println(usersByName);
        User userFromDB = dbService.getUserFromDBByID(1L);
        UserDTO userDTO = dtoService.getUserDTO(userFromDB);
        System.out.println("userDTO: " + userDTO);
        System.out.println("isSave: " + isSave);

        User thirdUser = dbService.getActiveUserByName("ThirdUser", null);
        System.out.println("getActiveUserByName "+thirdUser);

        List<User> users = dbService.getUsersByAgeNative(30);
        System.out.println("-------------getUsersByAgeNative--------------------");
        System.out.println(users);
        System.out.println("----------------------------");

        int countUsers = dbService.usersCount();
        System.out.println(countUsers);

        List<User> usersIsNull =  dbService.getOnlyActiveNull();
        System.out.println(usersIsNull);

        dbService.updateIsActiveByID(true, 1L);

        /*We have all 12 users, we can get all page with 6 pages with two user on each*/
        /*For that we must create sorting*/
        /*Sort request
         * asc - first to last
         * desc - last to first
         * */
        /*Sorting by id*/
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Page<User> userPage1 =  dbService.getPageUsers(0, 2,sort);
        List<User> contentPage1 = userPage1.getContent();
        System.out.println("-----------contentPage1--------------");
        contentPage1.forEach(System.out::println);
        System.out.println("-------------------------------------");

        Page<User> userPage2 =  dbService.getPageUsers(1, 2, sort);
        List<User> contentPage2 = userPage2.getContent();
        System.out.println("-----------contentPage2--------------");
        contentPage2.forEach(System.out::println);
        System.out.println("-------------------------------------");

        Page<User> userPage5 =  dbService.getPageUsers(5, 2, sort);
        List<User> contentPage5 = userPage5.getContent();
        System.out.println("-----------contentPage5--------------");
        contentPage5.forEach(System.out::println);
        System.out.println("-------------------------------------");

        modelAndView.addObject("userFromDB",userDTO);
/*        System.out.println(dbService.deleteFromBDByID(3L));*/

        /*Get UserProjection*/
        List<UserProjection> allByIsActive = dbService.findAllByIsActive(null);
        modelAndView.addObject("listUserProjection",allByIsActive);
        return modelAndView;
    }
}
