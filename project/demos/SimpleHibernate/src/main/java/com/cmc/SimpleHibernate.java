/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cmc;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

/* 
CREATE DATABASE simple_hibernate;
USE simple_hibernate;

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    price DOUBLE
);
*/

public class SimpleHibernate {

    public static void main(String[] args) {
        
        // Create SessionFactory
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Book.class)
                        .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // ================= CREATE =================
        session.beginTransaction();
        session.save(new Book("Java Basics", 15));
        session.save(new Book("Hibernate in Action", 25));
        session.getTransaction().commit();

        // ================= HQL: READ ALL =================
        List<Book> books =
                session.createQuery("FROM Book", Book.class)
                       .getResultList();

        System.out.println("All books:");
        books.forEach(b ->
                System.out.println(b.getId() + " - " +
                        b.getTitle() + " - " + b.getPrice())
        );

        // ================= UPDATE =================
        session.beginTransaction();
        Book book = session.get(Book.class, 1L);
        if (book != null) {
            book.setPrice(18);
        }
        session.getTransaction().commit();

        // ================= DELETE =================
        session.beginTransaction();
        Book del = session.get(Book.class, 2L);
        if (del != null) {
            session.delete(del);
        }
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}
