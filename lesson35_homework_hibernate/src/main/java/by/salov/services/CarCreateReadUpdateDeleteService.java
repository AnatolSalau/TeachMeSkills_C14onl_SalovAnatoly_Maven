package by.salov.services;

import by.salov.entity.Car;
import by.salov.entity.CarType;
import by.salov.entity.PassengerCar;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(transaction);
        PassengerCar passengerCar =  session.get(PassengerCar.class, id);
        System.out.println(passengerCar);
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
        List<Object[]> cars = session.createNativeQuery("SELECT * FROM car")
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar( "cartype", StringType.INSTANCE )
                .addScalar( "creation_inside_database", TimestampType.INSTANCE)
                .addScalar("datecreationcar", DateType.INSTANCE)
                .addScalar("hascar", BooleanType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("updatind_inside_database", TimestampType.INSTANCE)
                .addScalar("version",IntegerType.INSTANCE )
                .addScalar("quantitypeople",IntegerType.INSTANCE )
                .list();
        System.out.println(cars);
        for (Object[] rawCar : cars) {
            Integer id = (Integer) rawCar[0];
            CarType carType =  CarType.valueOf((String) rawCar[1]);
            Date creationInsideDatabase = (Date) rawCar[2];
            Date dateCreationCar = (Date) rawCar[3];
            boolean hasCar = (boolean) rawCar[4];
            String name = (String) rawCar[5];
            Date updatingInsideDatabase = (Date) rawCar[6];
            int version = (int) rawCar[7];
            int quantityPeople = (int) rawCar[8];
            PassengerCar car = new PassengerCar(id,name,carType,dateCreationCar,creationInsideDatabase,updatingInsideDatabase,hasCar,version,quantityPeople);
            System.out.println(car.getName());
            System.out.println(car);
        }
        transaction.commit();
        session.close();
        return null;
    }
}
