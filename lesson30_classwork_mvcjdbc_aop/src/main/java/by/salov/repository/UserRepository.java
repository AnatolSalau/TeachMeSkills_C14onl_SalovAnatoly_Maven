package by.salov.repository;

import by.salov.annotations.BenchMark;
import by.salov.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRepository {

    public static final String SAVE_SQL = "insert into users (id, name, password) values (?, ?, ?)";
    public static final String GETALL_SQL = "select * from users";


    private Connection connection;

    public UserRepository(@Qualifier("connectionPostgres") Connection connection) {
        this.connection = connection;
    }

    @BenchMark
    public void save(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL);
        //convert UUID to string
        preparedStatement.setString(1,user.getId().toString());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.execute();
        preparedStatement.close();
    }
    //@Scheduled(cron = "*/1 * * * * *") // 1 раз в секунду
    public List<User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GETALL_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(getAll(resultSet,preparedStatement));
        return getAll(resultSet,preparedStatement);
    }
    private List<User> getAll(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            //get UUID from string
            User user = new User(UUID.fromString(id),name,password);
            users.add(user);
        }
        preparedStatement.close();
        return users;
    }
}
