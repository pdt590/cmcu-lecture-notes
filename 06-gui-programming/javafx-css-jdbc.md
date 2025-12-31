# Ví dụ: JavaFX + CSS + JDBC

## 🎯 Chức năng demo

- Người dùng nhập tên vào TextField
- Nhấn Button
- Ứng dụng lưu dữ liệu vào MySQL
- Hiển thị trạng thái thành công/thất bại trên giao diện

## 🛠 Công nghệ sử dụng

- Java 17+ (khuyến nghị)
- JavaFX
- JDBC
- MySQL

## 📁 Cấu trúc project

```java
JavaFXMySQLApp/
├── lib/
│   └── mysql-connector-j-8.0.xx.jar
└── src/
    └── main/
        └── java/
            └── com/example/
                ├── MainApp.java
                ├── DBUtil.java
                └── UserDAO.java
```

## 🗄️ Database MySQL

### 1️⃣ Tạo database & table

```sql
CREATE DATABASE javafx_demo;
USE javafx_demo;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);
```

### 2️⃣ Thêm MySQL Connector

- Tải mysql-connector-j
- Add vào classpath / lib
- Nếu dùng IDE (IntelliJ/Eclipse): Add as Library

## 🧩 Code chi tiết

### 1️⃣ DBUtil.java – Kết nối MySQL

```java
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/javafx_demo?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### 2️⃣ UserDAO.java – Xử lý DB

```java
package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public static void insertUser(String name) throws Exception {
        String sql = "INSERT INTO users(name) VALUES (?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
}
```

### 3️⃣ MainApp.java – JavaFX GUI

```java
package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        TextField textField = new TextField();
        textField.setPromptText("Enter name");

        Button button = new Button("Save to MySQL");

        Label label = new Label();

        button.setOnAction(e -> {
            String name = textField.getText();

            if (name.isEmpty()) {
                label.setText("Name cannot be empty!");
                return;
            }

            try {
                UserDAO.insertUser(name);
                label.setText("Saved successfully!");
                textField.clear();
            } catch (Exception ex) {
                label.setText("Database error!");
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(10, textField, button, label);
        root.setStyle("-fx-padding: 20");

        stage.setTitle("JavaFX + MySQL Demo");
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```

## ▶️ Chạy ứng dụng

1. Start MySQL
2. Kiểm tra user/password
3. Run MainApp
4. Nhập tên → Click Save to MySQL
5. Kiểm tra DB:

```sql
SELECT * FROM users;
```

## 🧠 Luồng hoạt động

```java
TextField → Button Click → UserDAO → JDB → MySQL
```
