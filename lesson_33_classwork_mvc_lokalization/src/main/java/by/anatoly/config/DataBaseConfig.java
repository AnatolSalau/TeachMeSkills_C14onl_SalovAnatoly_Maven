package by.anatoly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.DriverManager;

//Configuration for connect data base
@Configuration
@EnableWebMvc
public class DataBaseConfig {
    //Bean for connection to database
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //set driver class of library for connection to database
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/lesson_20_jdbc_homework");
        dataSource.setUsername("postgres");
        dataSource.setPassword("sA#259979148307");
        return dataSource;
    }
    //Bean for carry out actions on the database
    //If we have something databases -> we must create JdbcTemplate for every database
    @Bean
    JdbcTemplate jdbcTemplate() {
        //JdbcTemplate for current database
        return new JdbcTemplate(dataSource());
    }
}
