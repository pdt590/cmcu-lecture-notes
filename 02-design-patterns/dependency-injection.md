# 🎯 Mini Project: NotificationApp - DI Pattern

🧩 Bài toán:
Xây dựng ứng dụng NotificationApp gửi thông báo qua các kênh khác nhau:

- Email
- SMS

Ta sẽ inject MessageService vào NotificationApp bằng 3 cách khác nhau.

## 📁 Cấu trúc project

```java
di-mini-project/
│
├── service/
│   ├── MessageService.java
│   ├── EmailService.java
│   └── SmsService.java
│
├── app/
│   ├── ConstructorInjectionApp.java
│   ├── SetterInjectionApp.java
│   └── FieldInjectionApp.java
│
└── Main.java
```

## 🧠 Code chi tiết

### 1. Interface & Implementations (Dependency)

**MessageService.java**

```java
public interface MessageService {
    void sendMessage(String message);
}
```

**EmailService.java**

```java
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}
```

**SmsService.java**

```java
public class SmsService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

### 2. Constructor Injection (Khuyến nghị dùng 👍)

**ConstructorInjectionApp.java**

```java
public class ConstructorInjectionApp {

    private final MessageService messageService;

    // Inject dependency qua constructor
    public ConstructorInjectionApp(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        messageService.sendMessage(message);
    }
}
```

**Đặc điểm**

- Dependency bắt buộc
- Class immutable
- Dễ test (mock)
- Được Spring khuyến nghị

### 3. Setter Injection (Tùy chọn)

**SetterInjectionApp.java**

```java
public class SetterInjectionApp {

    private MessageService messageService;

    // Inject dependency qua setter
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message) {
        if (messageService == null) {
            throw new IllegalStateException("MessageService is not set");
        }
        messageService.sendMessage(message);
    }
}
```

**Đặc điểm**

- Dependency có thể thay đổi
- Phù hợp dependency optional
- Dễ gây lỗi nếu quên set

### 4. Field Injection (Không khuyến nghị 🚫)

> Field Injection thường dùng trong Spring với @Autowired

**FieldInjectionApp.java**

```java
public class FieldInjectionApp {

    // Inject trực tiếp vào field
    MessageService messageService;

    public void notifyUser(String message) {
        messageService.sendMessage(message);
    }
}
```

**Đặc điểm**

- Code ngắn
- Khó test
- Vi phạm encapsulation
- Không thấy dependency từ constructor

### 5. Main class (Manual DI – không dùng Spring)

**Main.java**

```java
public class Main {
    public static void main(String[] args) {

        MessageService emailService = new EmailService();
        MessageService smsService = new SmsService();

        // Constructor Injection
        ConstructorInjectionApp app1 =
                new ConstructorInjectionApp(emailService);
        app1.notifyUser("Hello via Constructor Injection");

        // Setter Injection
        SetterInjectionApp app2 = new SetterInjectionApp();
        app2.setMessageService(smsService);
        app2.notifyUser("Hello via Setter Injection");

        // Field Injection
        FieldInjectionApp app3 = new FieldInjectionApp();
        app3.messageService = emailService; // Inject thủ công
        app3.notifyUser("Hello via Field Injection");
    }
}
```

## 🎯 So sánh nhanh 3 loại DI

| Tiêu chí            | Constructor | Setter | Field |
| ------------------- | ----------- | ------ | ----- |
| Bắt buộc dependency | ✅          | ❌     | ❌    |
| Dễ test             | ✅          | ⚠️     | ❌    |
| An toàn             | ✅          | ⚠️     | ❌    |
| Khuyến nghị         | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐    |
