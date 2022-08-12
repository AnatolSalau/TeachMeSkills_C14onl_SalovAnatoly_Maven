package by.salov.repository.impl;

import by.salov.models.User;
import by.salov.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

//Service for save and get User from DB by ORM Hibernate
@Service
public class UserRepositoryImpl implements UserRepository {
    private SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public User getById(int id) {
        //Open session to DB
        Session session = sessionFactory.openSession();
        //In Hibernate you must open and commit transaction
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        //Close session
        session.close();
        System.out.println(user);
        return user;
    }
    @Override
    public void save(User user) {
        //Open session to DB
        Session session = sessionFactory.openSession();
        //In Hibernate you must open and commit transaction
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        //Close session
        session.close();
    }
    @Override
    public void updateName(int id, String newName) {
        //Open session to DB
        Session session = sessionFactory.openSession();
        //In Hibernate you must open and commit transaction
        Transaction transaction = session.beginTransaction();
        //Update user
        User userFromDb = session.get(User.class, id);
        userFromDb.setLogin(newName);
        session.update(userFromDb);
        transaction.commit();
        //Close session
        session.close();
    }
}
