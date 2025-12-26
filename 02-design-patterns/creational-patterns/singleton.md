# 🎯 Mini Project: ApplicationConfig Manager – Singleton Pattern

> **Singleton**: Đảm bảo một lớp chỉ có một thể hiện (đối tượng) duy nhất và cung cấp phương thức truy cập toàn cục đến đối tượng này

Tạo một ứng dụng Java dùng Singleton để quản lý cấu hình (config) cho toàn hệ thống.

## 🧱 1. Cấu trúc Project

```java
singleton-demo/
│
├── App.java
├── config/
│     └── ApplicationConfig.java (Singleton)
├── service/
      └── UserService.java
```

## 🧩 2. Code chi tiết

**(A) Singleton: ApplicationConfig.java**

```java
package config;

public class ApplicationConfig {

    // Bước 1: Tạo static instance duy nhất
    private static ApplicationConfig instance;

    // Ví dụ các config
    private String appName = "My Demo App";
    private String version = "1.0.0";

    // Bước 2: Constructor private để chặn tạo đối tượng từ bên ngoài
    private ApplicationConfig() {
        System.out.println("ApplicationConfig initialized!");
    }

    // Bước 3: Hàm public để lấy instance duy nhất (Lazy Initialization)
    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    // Getter & Setter config
    public String getAppName() {
        return appName;
    }

    public void setAppName(String name) {
        this.appName = name;
    }

    public String getVersion() {
        return version;
    }
}
```

**(B) Class dùng Singleton: UserService.java**

```java
package service;

import config.ApplicationConfig;

public class UserService {

    public void printSystemInfo() {
        ApplicationConfig config = ApplicationConfig.getInstance();

        System.out.println("=== System Info ===");
        System.out.println("App Name : " + config.getAppName());
        System.out.println("Version  : " + config.getVersion());
        System.out.println("===================");
    }
}
```

**(C) Main file: App.java**

```java
import service.UserService;
import config.ApplicationConfig;

public class App {
    public static void main(String[] args) {

        // Lấy singleton và chỉnh sửa cấu hình
        ApplicationConfig config = ApplicationConfig.getInstance();
        config.setAppName("Singleton Demo Application");

        // Gọi service
        UserService userService = new UserService();
        userService.printSystemInfo();

        // Kiểm tra Singleton
        ApplicationConfig config2 = ApplicationConfig.getInstance();
        System.out.println("Có phải cùng instance? " + (config == config2)); // true
    }
}
```

## 🧪 3. Kết quả khi chạy

```bash
ApplicationConfig initialized!
=== System Info ===
App Name : Singleton Demo Application
Version  : 1.0.0
===================
Có phải cùng instance? true
```
