package by.salov.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class DataBaseConfig {
    @Value("${hibernate.connection.driver_class}")
    String driver;
    @Value("${hibernate.connection.url}")
    String url;
    @Value("${hibernate.connection.username}")
    String username;
    @Value("${hibernate.connection.password}")
    String password;
    @Value("${hibernate.dialect}")
    String dialect;

    @Bean
    //@Qualifier(value = "default")
    Configuration configuration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.dialect", dialect);
        return configuration;
    }
    @Bean
    SessionFactory factory(/*@Qualifier(value = "default")*/ Configuration configuration) throws HibernateException {
        return configuration.buildSessionFactory();
    }
}
