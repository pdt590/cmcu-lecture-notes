# 🚀 Mini Project: Simple Product Management (maven + Hibernate-JPA + JPQL)

## 🎯 Mục tiêu

- Cấu hình Hibernate JPA thuần
- Mapping @Entity
- CRUD bằng EntityManager
- Không DAO, không Service, không Framework

## 🏗️ Cấu trúc Project

```java
SimpleJPA/
│
├── src/main/java/
│   ├── Product.java
│   └── Main.java
│
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml
│
└── pom.xml
```

## 🗄️ 1. Database MySQL

```sql
CREATE DATABASE simple_jpa;
USE simple_jpa;

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DOUBLE
);
```

## 📦 2. Entity – Product.java

```java
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter & Setter
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
```

## ⚙️ 3. Config files

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cmc</groupId>
    <artifactId>SimpleJPA</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>25</maven.compiler.release>
        <exec.mainClass>com.cmc.SimpleJPA</exec.mainClass>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>7.2.0.Final</version>
        </dependency>
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.5.0</version> <!-- or latest version -->
        </dependency>
    </dependencies>
</project>
```

**persistence.xml**

```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.0">
    <persistence-unit name="simplePU">
        <class>Product</class>

        <properties>
            <property name="jakarta.persistence.jdbc.driver"
                      value="com.mysql.cj.jdbc.Driver"/>

            <property name="jakarta.persistence.jdbc.url"
                      value="jdbc:mysql://localhost:3306/simple_jpa?useSSL=false"/>

            <property name="jakarta.persistence.jdbc.user"
                      value="root"/>

            <property name="jakarta.persistence.jdbc.password"
                      value="123456"/>
        </properties>
    </persistence-unit>
</persistence>
```

## ▶️ 4. Main – Main.java (CRUD trực tiếp)

```java
import jakarta.persistence.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("simplePU");

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

```

## 🧠 Kiến thức JPA cốt lõi trong project

- `@Entity`, `@Id`, `@GeneratedValue`
- `EntityManagerFactory`
- `EntityManager`
- `persist()`, `find()`, `remove()`
- Transaction (`begin`, `commit`)
- JPQL: `FROM Product`

## 🧪 Output ví dụ

```bash
Hibernate: insert into products (name,price) values (?,?)
Hibernate: insert into products (name,price) values (?,?)
Product list:
1 - Laptop - 1500.0
2 - Mouse - 20.0
Hibernate: update products set price=? where id=?
Hibernate: delete from products where id=?
```

# 🚀 Mini Project: Simple Book Management (maven + Hibernate native + HQL)

## 🎯 Mục tiêu

- Hiểu Hibernate native API
- Dùng SessionFactory, Session
- Mapping Entity bằng annotation Hibernate/JPA
- CRUD + HQL không DAO

## 🏗️ Cấu trúc Project

```java
SimpleHibernateNative/
│
├── src/main/java/
│   ├── Book.java
│   └── Main.java
├── src/main/resources/
│   └── META-INF/
│       └── hibernate.cfg.xml
└── pom.xml
```

## 🗄️ 1. Database MySQL

```sql
CREATE DATABASE simple_hibernate;
USE simple_hibernate;

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    price DOUBLE
);
```

## 📦 2. Entity – Book.java

> Hibernate native vẫn dùng annotation JPA

```java
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;

    public Book() {}

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    // Getter & Setter
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
```

## ⚙️ 3. Config files

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cmc</groupId>
    <artifactId>SimpleJPAHibernateNative</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>25</maven.compiler.release>
        <exec.mainClass>com.cmc.simplehibernatenative.SimpleHibernateNative</exec.mainClass>
    </properties>
        <dependencies>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.4.7.Final</version>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.5.0</version> <!-- or latest version -->
        </dependency>
    </dependencies>
    <name>SimpleJPAHibernateNative</name>
</project>
```

**hibernate.cfg.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <!-- JDBC -->
        <property name="hibernate.connection.driver_class">
            com.mysql.cj.jdbc.Driver
        </property>

        <property name="hibernate.connection.url">
            jdbc:mysql://localhost:3306/simple_hibernate?useSSL=false
        </property>

        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">123456</property>

        <!-- Hibernate -->
        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQL8Dialect
        </property>

        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>

        <!-- Entity -->
        <mapping class="Book"/>

    </session-factory>
</hibernate-configuration>
```

## ▶️ 4. Main – Main.java (Hibernate Native CRUD + HQL)

```java
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

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
```

## 🧠 Hibernate Native API đã dùng

- `SessionFactory`
- `Session`
- `save()`
- `get()`
- `delete()`
- `beginTransaction()`
- `commit()`
- `HQL: FROM Book`

## 🧪 Output

```bash
Hibernate: insert into books (title,price) values (?,?)
Hibernate: insert into books (title,price) values (?,?)
All books:
1 - Java Basics - 15.0
2 - Hibernate in Action - 25.0
Hibernate: update books set price=? where id=?
Hibernate: delete from books where id=?
```

## 🔥 So sánh nhanh với Hibernate JPA

| Hibernate Native | Hibernate JPA        |
| ---------------- | -------------------- |
| Session          | EntityManager        |
| SessionFactory   | EntityManagerFactory |
| HQL              | JPQL                 |
| Mạnh             | Chuẩn                |


# 🚀 Mini Project: Simple Book Management (maven + Hibernate native + HQL + DAO)

## 🏗️ Kiến trúc Project

```java
employee-management-jpa/
│
├── src/main/java/
│   ├── entity/
│   │   └── Book.java
│   │
│   ├── dao/
│   │   ├── BookDAO.java        (Interface)
│   │   └── EBookDAOImpl.java   (Implementation)
│   │
│   ├── util/
│   │   └── HibernateUtil.java
│   │
│   └── app
        └── MainApp.java
│
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml
│
└── pom.xml

```

## 1️⃣ Entity – Book.java

**👉 Entity KHÔNG biết DAO, KHÔNG biết DB**

```java
package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private double price;

    public Book() {}

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // getter & setter
}
```

## 2️⃣ Hibernate Utility – HibernateUtil.java

```java
package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
```

## 3️⃣ DAO Interface – BookDAO.java

**👉 Chỉ định nghĩa hành vi, KHÔNG code DB**

```java
package dao;

import entity.Book;
import java.util.List;

public interface BookDAO {

    void save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    void update(Book book);

    void delete(Long id);
}
```

## 4️⃣ DAO Implementation – BookDAOImpl.java

**👉 Toàn bộ Hibernate code nằm ở đây**

```java
package dao;

import entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public void save(Book book) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Book", Book.class).list();
        }
    }

    @Override
    public void update(Book book) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.delete(book);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
```

## 5️⃣ Main Application – MainApp.java

**👉 App KHÔNG biết Hibernate, chỉ làm việc với DAO**

```java
package app;

import dao.BookDAO;
import dao.BookDAOImpl;
import entity.Book;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAOImpl();

        // CREATE
        Book book = new Book("Hibernate in Action", "Gavin King", 29.99);
        bookDAO.save(book);

        // READ
        Book found = bookDAO.findById(1L);
        System.out.println(found.getTitle());

        // UPDATE
        found.setPrice(35.0);
        bookDAO.update(found);

        // LIST
        List<Book> books = bookDAO.findAll();
        books.forEach(b -> System.out.println(b.getTitle()));

        // DELETE
        bookDAO.delete(1L);
    }
}
```

## 6️⃣ So sánh TRƯỚC và SAU khi dùng DAO

| Tiêu chí        | Không DAO | Có DAO |
| --------------- | --------- | ------ |
| Tách biệt logic | ❌        | ✅     |
| Dễ test         | ❌        | ✅     |
| Dễ mở rộng      | ❌        | ✅     |
| Thay ORM        | ❌        | ✅     |
| Chuẩn kiến trúc | ❌        | ✅     |

## 7️⃣ DAO Pattern giúp gì?

- Tách Business logic khỏi Persistence logic
- Giảm phụ thuộc Hibernate
- Dễ migrate sang JPA / Spring Data
- Chuẩn kiến trúc Enterprise Java
