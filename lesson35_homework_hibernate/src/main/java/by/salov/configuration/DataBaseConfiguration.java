package by.salov.configuration;

import by.salov.entity.Car;
import by.salov.entity.PassengerCar;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DataBaseConfiguration {
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
    @Qualifier("default_configuration")
    public Configuration dbConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.dialect", dialect);

        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        //configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(PassengerCar.class);

        return configuration;
    }

    @Bean
    @Qualifier("default_sessionfactory")
    SessionFactory sessionFactory(@Qualifier("default_configuration") Configuration configuration) throws HibernateException {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
