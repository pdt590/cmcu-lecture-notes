# 🚀 Mini Project: Simple Product Management (Hibernate JPA)

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

## ⚙️ 3. persistence.xml

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

            <property name="hibernate.dialect"
                      value="org.hibernate.dialect.MySQL8Dialect"/>

            <property name="hibernate.hbm2ddl.auto"
                      value="update"/>

            <property name="hibernate.show_sql"
                      value="true"/>
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

# 🚀 Mini Project: Employee Management System (Hibernate JPA + HQL)

## 🎯 Mục tiêu

- Cấu hình Hibernate JPA
- Mapping Entity ↔ Table
- CRUD bằng EntityManager
- Truy vấn dữ liệu bằng HQL / JPQL
- Áp dụng DAO Pattern

## 🏗️ Kiến trúc Project

```java
employee-management-jpa/
│
├── src/main/java/
│   ├── entity/
│   │   └── Employee.java
│   │
│   ├── dao/
│   │   ├── EmployeeDAO.java
│   │   └── EmployeeDAOImpl.java
│   │
│   ├── util/
│   │   └── JPAUtil.java
│   │
│   └── Main.java
│
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml
│
└── pom.xml
```

## Code chi tiết

### 🧩 1. Database (MySQL)

```java
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE,
    department VARCHAR(100)
);
```

### 📦 2. Entity – Employee.java

```java
package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;
    private String department;

    public Employee() {}

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
```

### ⚙️ 3. JPA Util – JPAUtil.java

```java
package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("employeePU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
```

### 📑 4. DAO Interface – EmployeeDAO.java

```java
package dao;

import entity.Employee;
import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);
    Employee findById(Long id);
    List<Employee> findAll();
    List<Employee> findByDepartment(String department);
    List<Employee> findSalaryGreaterThan(double salary);
    void update(Employee employee);
    void delete(Long id);
}
```

### 🛠️ 5. DAO Implementation (HQL) – EmployeeDAOImpl.java

```java
package dao;

import entity.Employee;
import util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void save(Employee employee) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Employee findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Employee emp = em.find(Employee.class, id);
        em.close();
        return emp;
    }

    @Override
    public List<Employee> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Employee> query =
                em.createQuery("FROM Employee", Employee.class);
        List<Employee> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Employee> query =
                em.createQuery(
                        "FROM Employee e WHERE e.department = :dept",
                        Employee.class);
        query.setParameter("dept", department);
        List<Employee> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Employee> findSalaryGreaterThan(double salary) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Employee> query =
                em.createQuery(
                        "FROM Employee e WHERE e.salary > :salary",
                        Employee.class);
        query.setParameter("salary", salary);
        List<Employee> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public void update(Employee employee) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Employee emp = em.find(Employee.class, id);
        if (emp != null) {
            em.remove(emp);
        }
        em.getTransaction().commit();
        em.close();
    }
}
```

### ▶️ 6. Main Test – Main.java

```java
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAOImpl();

        // Create
        dao.save(new Employee("Nguyen Van A", 1200, "IT"));
        dao.save(new Employee("Tran Thi B", 900, "HR"));
        dao.save(new Employee("Le Van C", 1500, "IT"));

        // Read all
        dao.findAll().forEach(e ->
                System.out.println(e.getName() + " - " + e.getSalary())
        );

        // HQL: filter
        System.out.println("IT Department:");
        dao.findByDepartment("IT").forEach(e ->
                System.out.println(e.getName())
        );

        // HQL: salary condition
        System.out.println("Salary > 1000:");
        dao.findSalaryGreaterThan(1000).forEach(e ->
                System.out.println(e.getName())
        );
    }
}
```

### ⚙️ 7. persistence.xml

```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.0">
    <persistence-unit name="employeePU">
        <class>entity.Employee</class>

        <properties>
            <property name="jakarta.persistence.jdbc.driver"
                      value="com.mysql.cj.jdbc.Driver"/>

            <property name="jakarta.persistence.jdbc.url"
                      value="jdbc:mysql://localhost:3306/employee_db?useSSL=false"/>

            <property name="jakarta.persistence.jdbc.user"
                      value="root"/>

            <property name="jakarta.persistence.jdbc.password"
                      value="123456"/>

            <property name="hibernate.dialect"
                      value="org.hibernate.dialect.MySQL8Dialect"/>

            <property name="hibernate.hbm2ddl.auto"
                      value="update"/>

            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

### 📦 8. pom.xml (Maven)

```xml
<dependencies>
    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.4.Final</version>
    </dependency>

    <!-- JPA -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>
```

## 🧠 Các HQL đã sử dụng

```sql
FROM Employee
FROM Employee e WHERE e.department = :dept
FROM Employee e WHERE e.salary > :salary
```