package com.akadatsky.DZ23;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.List;

public class HibernateStorage implements Storage {

    private SessionFactory sessionFactory;

    public HibernateStorage() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void removeAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", User.class.getSimpleName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void removeUserByAge(int age) {
        User user=new User();
        try (Session session = sessionFactory.openSession()) {
            user = session
                    .createQuery("FROM User WHERE age = :age ", User.class)
                    .setParameter("age", age)
                    .getSingleResult();
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserByName(String name) {
        User user=new User();
        try (Session session = sessionFactory.openSession()) {
            user = session
                    .createQuery("FROM User WHERE name = :name ", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public User getUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE name = :name ", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
    public void close() {
        sessionFactory.close();
    }

}
