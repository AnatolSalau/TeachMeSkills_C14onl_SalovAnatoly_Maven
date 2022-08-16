package by.salov.services;

import by.salov.entity.Car;
import by.salov.entity.CarType;
import by.salov.entity.PassengerCar;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void update(int id,int quantityPeople, CarType carType, java.util.Date dateCreationCar, boolean hasCar) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PassengerCar passengerCar = session.get(PassengerCar.class, id);
        System.out.println("passengerCar from db : " + passengerCar);
        passengerCar.setName("name after update");
        passengerCar.setQuantityPeople(quantityPeople);
        passengerCar.setCarType(carType);
        passengerCar.setDateCreationCar(dateCreationCar);
        passengerCar.setHasCar(hasCar);
        session.update(passengerCar);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete() {

    }

    @Override
    public List<PassengerCar> getAllCars() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from cars");
        List<Object> rows = query.list();
        System.out.println(rows);
        List<Car> passengerCarList = rows
                .stream()
                .map(e -> (Car) e)
                .collect(Collectors.toList());
        System.out.println(passengerCarList);
        transaction.commit();
        session.close();
        return passengerCarList;
    }
}
