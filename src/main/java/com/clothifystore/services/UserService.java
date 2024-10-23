



package com.clothifystore.services;

import com.clothifystore.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserService {

    private SessionFactory factory;

    public UserService() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }

    public User authenticate(String email, String password) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = (User) session.createQuery("from User where email = :email and password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}

