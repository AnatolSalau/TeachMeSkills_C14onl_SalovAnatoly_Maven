package by.salov.lesson29_spring_aop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ProjectConfig {
    public final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public final String USER_NAME = "postgres";
    public final String USER_PASSWORD = "sA#259979148307";

    @Value("${new.value}")
    private String NEW_VALUE;

    private String JAVA_HOME = System.getenv("JAVA_HOME");

    @Bean
    public Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
        return connection;
    }
}
