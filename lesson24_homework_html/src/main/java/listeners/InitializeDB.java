package listeners;

import dao.DBUsersConnect;
import entity.User;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitializeDB implements ServletContextListener {
    private DBUsersConnect dbUsersConnect;
    public InitializeDB() {
        dbUsersConnect = new DBUsersConnect();
        dbUsersConnect.addUser(
                new User("First","1111", "man","Default user", "admin")
        );
    }
}
