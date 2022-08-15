package by.salov.services;

import by.salov.entity.Car;
import by.salov.entity.PassengerCar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("default_crud_service")
public class CarCreateReadUpdateDeleteService implements CreateReadUpdateDeleteService{
    private SessionFactory sessionFactory;

    public CarCreateReadUpdateDeleteService(@Qualifier("default_sessionfactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(PassengerCar passengerCar) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passengerCar);
        transaction.commit();
        session.close();
    }

    @Override
    public Car read() {
        return null;
    }

    @Override
    public void update(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PassengerCar passengerCar = session.get(PassengerCar.class, 2);
        System.out.println("passengerCar from db : " + passengerCar);
        passengerCar.setName("name after update");
        passengerCar.setQuantityPeople(6);
        session.update(passengerCar);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete() {

    }
}
