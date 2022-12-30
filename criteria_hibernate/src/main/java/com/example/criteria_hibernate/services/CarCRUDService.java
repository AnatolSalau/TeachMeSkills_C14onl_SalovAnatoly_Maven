package com.example.criteria_hibernate.services;

import com.example.criteria_hibernate.entity.Car;
import com.example.criteria_hibernate.entity.CarType;
import jakarta.annotation.PostConstruct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


@Service
public class CarCRUDService {

    @Autowired
    private  SessionFactory sessionFactory;

    //Load EntityManagerFactory or EntityManager
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public CarCRUDService() {
    }
    @PostConstruct
    private void init() {
        System.out.println("______________________________________________________________");
        System.out.println(sessionFactory.toString());
        //saveCar();
        criteriaHibernate("CarTwo", null,null);
        criteriaJPAFromHibernate("CarOne");
        System.out.println("______________________________________________________________");
    }

    private void saveCar() {
        Car car = new Car("CarThree", CarType.BMW, new Date(), false);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    private void criteriaHibernate(String carNameForSearch, CarType carType, Boolean hasCar ) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //car name for search
        //Create criteria
        Criteria criteria = session.createCriteria(Car.class);
        //Restrictions - class for comparing and other operations
        // eq = equals
        //chech null or not
        if (carNameForSearch != null) {
            criteria.add(Restrictions.eq("name",carNameForSearch));
        }
        if (carType != null) {
            criteria.add(Restrictions.eq("carType", carType));
        }
        if (hasCar != null) {
            criteria.add(Restrictions.eq("hasCar", hasCar));
        }

        List<Car> list = criteria.list();
        System.out.println(list.toString());
        //or if single result
        //Object o = criteria.uniqueResult();

        transaction.commit();
        session.close();
    }
    //Get JPA EntityManager from Hibernate
    private List<Car> criteriaJPAFromHibernate(String name) {
        //Get entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //Get criteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //Create query from criteria
        CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
        //Get Root class
        Root<Car> carRoot = query.from(Car.class);
        query.where(criteriaBuilder.equal(carRoot.get("name"), name ));
        TypedQuery<Car> typedQuery = entityManager.createQuery(query);
        List<Car> resultList = typedQuery.getResultList();
        System.out.println(resultList);
        return resultList;
    }
}
