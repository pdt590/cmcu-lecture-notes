# Ví dụ: JavaFX + FXML + JDBC

## 🎯 Mục tiêu project

- JavaFX UI dùng FXML (Được tạo từ **SceneBuilder**)
- Nhập dữ liệu từ TextField
- Click Button
- Lưu dữ liệu vào MySQL
- Hiển thị thông báo trạng thái

## 🛠 Công nghệ

- Java 17+
- JavaFX
- JDBC
- MySQL
- FXML + Controller

## 📁 Cấu trúc project (chuẩn)

```java
JavaFX-FXML-MySQL/
├── lib/
│   └── mysql-connector-j-8.0.xx.jar
└── src/
    └── main/
        ├── java/
        │   └── com/example/
        │       ├── MainApp.java
        │       ├── controller/
        │       │   └── UserController.java
        │       ├── dao/
        │       │   └── UserDAO.java
        │       └── util/
        │           └── DBUtil.java
        └── resources/
            └── view/
                └── user-view.fxml
```

## 🗄️ MySQL Database

```sql
CREATE DATABASE javafx_fxml_demo;
USE javafx_fxml_demo;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);
```

## 🔌 DBUtil – Kết nối MySQL

```java
package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/javafx_fxml_demo?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

## 🧩 UserDAO – Truy cập DB

```java
package com.example.dao;

import com.example.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public static void save(String name) throws Exception {
        String sql = "INSERT INTO users(name) VALUES (?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
}
```

## 🎮 Controller – UserController.java

```java
package com.example.controller;

import com.example.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserController {

    @FXML
    private TextField nameField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleSave() {
        String name = nameField.getText();

        if (name.isEmpty()) {
            statusLabel.setText("Name cannot be empty!");
            return;
        }

        try {
            UserDAO.save(name);
            statusLabel.setText("Saved successfully!");
            nameField.clear();
        } catch (Exception e) {
            statusLabel.setText("Database error!");
            e.printStackTrace();
        }
    }
}
```

## 🎨 FXML – user-view.fxml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.*?>
<?import javafx.scene.layout.VBox?>

<VBox spacing="10"
      xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.example.controller.UserController"
      alignment="CENTER"
      style="-fx-padding:20">

    <TextField fx:id="nameField" promptText="Enter name"/>

    <Button text="Save"
            onAction="#handleSave"/>

    <Label fx:id="statusLabel"/>

</VBox>
```

## 🚀 MainApp – Entry Point

```java
package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/user-view.fxml")
        );

        stage.setScene(new Scene(loader.load(), 300, 200));
        stage.setTitle("JavaFX + FXML + MySQL");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```

## ▶️ Chạy project

1. Chạy MySQL
2. Kiểm tra user/password
3. Add mysql-connector vào classpath
4. Run MainApp
5. Nhập tên → Save → kiểm tra DB

```sql
SELECT * FROM users;
```

## 🧠 Kiến trúc MVC

```java
FXML (View)
   ↓
Controller
   ↓
DAO
   ↓
MySQL
```
