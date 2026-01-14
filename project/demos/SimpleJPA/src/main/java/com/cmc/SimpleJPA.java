/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cmc;

import jakarta.persistence.*;
import java.util.List;

/*
CREATE DATABASE simple_jpa;
USE simple_jpa;

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DOUBLE
);
*/

public class SimpleJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("SimpleHibernateJPA");

        EntityManager em = emf.createEntityManager();

        // ================= CREATE =================
        em.getTransaction().begin();
        em.persist(new Product("Laptop", 1500));
        em.persist(new Product("Mouse", 20));
        em.getTransaction().commit();

        // ================= READ ALL =================
        List<Product> products =
                em.createQuery("FROM Product", Product.class)
                  .getResultList();

        System.out.println("Product list:");
        products.forEach(p ->
                System.out.println(p.getId() + " - " +
                        p.getName() + " - " + p.getPrice())
        );

        // ================= UPDATE =================
        em.getTransaction().begin();
        Product p = em.find(Product.class, 1L);
        if (p != null) {
            p.setPrice(1700);
        }
        em.getTransaction().commit();

        // ================= DELETE =================
        em.getTransaction().begin();
        Product deleteP = em.find(Product.class, 2L);
        if (deleteP != null) {
            em.remove(deleteP);
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}