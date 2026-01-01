# 🚀 Mini Project: Simple User Management (Maven + JDBC + MySQL)

## 🎯 Mục tiêu

- Kết nối Java với MySQL bằng JDBC
- Thực hiện CRUD trực tiếp trong main
- Hiểu rõ Connection, Statement, PreparedStatement, ResultSet
- Không dùng DAO, không framework

## 🏗️ Cấu trúc Project

```java
SimpleJDBC/
│
├── src/main/java/
│   └── Main.java
└── pom.xml
```

## 🗄️ 1. Database MySQL

```sql
CREATE DATABASE simple_jdbc;
USE simple_jdbc;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100)
);
```

## 🔌 2. pom.xml - thêm dependency MySQL connector

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cmc</groupId>
    <artifactId>SimpleJDBC</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>25</maven.compiler.release>
        <exec.mainClass>com.cmc.simplejdbc.SimpleJDBC</exec.mainClass>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.5.0</version> <!-- or latest version -->
        </dependency>
    </dependencies>
</project>
```

## ▶️ 3. Code Hoàn Chỉnh – Main.java

```java
import java.sql.*;

public class Main {

    private static final String URL =
        "jdbc:mysql://localhost:3306/simple_jdbc?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        // CREATE
        insertUser("Nguyen Van A", "a@gmail.com");

        // READ
        getAllUsers();

        // UPDATE
        updateUser(1, "Nguyen Van A Updated", "updated@gmail.com");

        // DELETE
        deleteUser(2);
    }

    // ================= CRUD METHODS =================

    static void insertUser(String name, String email) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Inserted user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getAllUsers() {
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("User list:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateUser(int id, String name, String email) {
        String sql = "UPDATE users SET name=?, email=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Updated user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

## 🧠 Kiến thức JDBC cốt lõi trong project

- `DriverManager.getConnection()`
- `Statement` vs `PreparedStatement`
- `executeQuery()` vs `executeUpdate()`
- `ResultSet`
- `try-with-resources`
- SQL Injection (đã phòng tránh)

## 🧪 Output ví dụ

```bash
Inserted user successfully!
User list:
1 | Nguyen Van A | a@gmail.com
Updated user successfully!
Deleted user successfully!
```

# 🚀 Mini Project: Student Management System (Maven + JDBC + MySQL + DAO)

## 🎯 Mục tiêu

- Kết nối Java với MySQL bằng JDBC
- Thực hiện CRUD (Create – Read – Update – Delete)
- Áp dụng mô hình DAO (Data Access Object)
- Code dễ hiểu, đúng chuẩn thực tế

## 🏗️ Kiến trúc Project

```java
student-management-jdbc/
│
├── src/
│   ├── model/
│   │   └── Student.java
│   │
│   ├── dao/
│   │   ├── StudentDAO.java
│   │   └── StudentDAOImpl.java
│   │
│   ├── util/
│   │   └── DBConnection.java
│   │
│   └── Main.java
│
└── pom.xml
```

## Code chi tiết

### 🗄️ 1. Database (MySQL)

```java
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    email VARCHAR(100)
);
```

### 🔌 2. Kết nối MySQL

**pom.xml - thêm dependency MySQL connector**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cmc</groupId>
    <artifactId>SimpleJDBC</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>25</maven.compiler.release>
        <exec.mainClass>com.cmc.simplejdbc.SimpleJDBC</exec.mainClass>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.5.0</version> <!-- or latest version -->
        </dependency>
    </dependencies>
</project>
```

**DBConnection.java**

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

### 📦 3. Model – Student.java

```java
package model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String email;

    public Student() {}

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Student(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

### 📑 4. DAO Interface – StudentDAO.java

```java
package dao;

import model.Student;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void updateStudent(Student student);
    void deleteStudent(int id);
}
```

### 🛠️ 5. DAO Implementation – StudentDAOImpl.java

```java
package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, age=?, email=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### ▶️ 6. Main Test – Main.java

```java
import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

public class Main {
    public static void main(String[] args) {

        StudentDAO dao = new StudentDAOImpl();

        // Create
        dao.addStudent(new Student("Nguyen Van A", 20, "a@gmail.com"));

        // Read
        dao.getAllStudents().forEach(s ->
                System.out.println(s.getId() + " - " + s.getName())
        );

        // Update
        Student s = dao.getStudentById(1);
        if (s != null) {
            s.setAge(22);
            dao.updateStudent(s);
        }

        // Delete
        dao.deleteStudent(2);
    }
}
```

## ✅ Kiến thức học được

- JDBC Connection, Statement, PreparedStatement, ResultSet
- CRUD với MySQL
- DAO Pattern
- Try-with-resources
- Clean code JDBC
