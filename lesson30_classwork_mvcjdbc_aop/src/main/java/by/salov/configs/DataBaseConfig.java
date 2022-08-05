package by.salov.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataBaseConfig {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    //Get system variables
    @Value("${db.login}")
    private  String USERNAME;
    @Value("${db.password}")
    private  String PASSWORD;

    @Bean
    @Qualifier(value = "connectionPostgres")
    public Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println(connection);
        //Get all system variables
        //System.out.println(System.getProperties());
        return connection;
    }
}
