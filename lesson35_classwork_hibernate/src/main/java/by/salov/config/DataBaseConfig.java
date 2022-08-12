package by.salov.config;

import by.salov.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//Condiguration that create Hibernate SessionFactory
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
    @Qualifier(value = "default")
    Configuration configuration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.dialect", dialect);
        //indicate that hibernate must show sql request
        configuration.setProperty("hibernate.show_sql", "true");
        //indicate that hibernate must format sql request which we see in console
        configuration.setProperty("hibernate.format_sql", "true");
        //configuration for development
        //create and drop table  - for developing
        //if you want create new user you must set update
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        //You must mark our entity by addAnnotatedClass
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
    @Bean
    SessionFactory factory(@Qualifier(value = "default") Configuration configuration) throws HibernateException {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
