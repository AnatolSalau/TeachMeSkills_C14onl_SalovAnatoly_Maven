package by.salov.lesson29_spring_aop.dao;

import by.salov.lesson29_spring_aop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;

/**JDBC Service CRUD operations */
@Service
public class UserRepository {

    private final String CREATE_TABLE_USERS =
            "create  table if not exists users (\n" +
            "    id bigint primary key,\n" +
            "    login varchar unique ,\n" +
            "    first_name varchar)";
    private final String CREATE_USERS_ID_SEQUENCE =
            "create sequence if not exists user_id_sequence\n" +
            "start 1\n" +
            "increment 1";

    private final String SAVE_USER = "" +
            "insert into users (id, login, first_name)\n" +
            "values (nextval('user_id_sequence'), ?, ?)";

    private final String GET_USER = "select * from users where login = ?";

    private final String DELETE_USER = "delete from users where login = ?";

    private final String USER_EXIST = "SELECT COUNT(*) FROM users WHERE login = ?";

    @Autowired
    Connection connection;

    @Autowired
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User saveUser(User user) throws SQLException {
        /*Save user*/
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.execute();
        preparedStatement.close();
        /*Get saved user from db*/
        PreparedStatement getUserPreparedStatement = connection.prepareStatement(GET_USER);
        getUserPreparedStatement.setString(1, user.getLogin());
        ResultSet resultSet = getUserPreparedStatement.executeQuery();
        User userFomDB = new User();
        /*Iterate through resultSet because result set is collection*/
        while (resultSet.next()) {
            userFomDB.setId(resultSet.getLong(1));
            userFomDB.setLogin(resultSet.getString(2));
            userFomDB.setFirstName(resultSet.getString(3));
        }
        getUserPreparedStatement.close();
        return userFomDB;
    }

    public boolean deleteUserByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setString(1, login);
        preparedStatement.execute();
        preparedStatement.close();
        return  userIsExistByLogin(login);
    }

    public boolean userIsExistByLogin(String login) throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = connection.prepareStatement(USER_EXIST);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        int isExist = 0;
        while (resultSet.next()) {
            isExist = resultSet.getInt(1);
            System.out.println(isExist);
        }
        preparedStatement.close();
        result = isExist > 0 ? true : false;
        return result;
    }

    @PostConstruct
    private void initializeDB() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_TABLE_USERS);

        Statement preparedStatement1 = connection.createStatement();
        preparedStatement1.executeUpdate(CREATE_USERS_ID_SEQUENCE);
    }

}
