# 🌉 Mini Project: Notification System - Bridge Pattern

> **Bridge**: Tách giao diện khỏi phần triển khai của nó. Nó cho phép hai thành phần phát triển độc lập với nhau. Mục đích là để tách các thành phần của một hệ thống để chúng có thể thay đổi độc lập mà không ảnh hưởng lẫn nhau

🎯 Bài toán
Hệ thống gửi thông báo có:

- Loại thông báo: Alert, Promotion
- Kênh gửi: Email, SMS

❌ Nếu dùng kế thừa → AlertEmail, AlertSMS, PromotionEmail, PromotionSMS (nổ class)
✔ Dùng Bridge Pattern để tách loại thông báo và kênh gửi

🧩 Ý tưởng Bridge Pattern

> Abstraction (Notification)
> Implementation (MessageSender)
> → Hai nhánh phát triển độc lập

## 📁 1. Cấu trúc project

```java
bridge-notification/
│
├── App.java
│
├── sender/
│     ├── MessageSender.java
│     ├── EmailSender.java
│     └── SmsSender.java
│
└── notification/
      ├── Notification.java
      ├── AlertNotification.java
      └── PromotionNotification.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Implementor — MessageSender.java

```java
package sender;

public interface MessageSender {
    void sendMessage(String message);
}
```

### ⭐ B) Concrete Implementors

**EmailSender.java**

```java
package sender;

public class EmailSender implements MessageSender {

    @Override
    public void sendMessage(String message) {
        System.out.println("📧 Email sent: " + message);
    }
}
```

**SmsSender.java**

```java
package sender;

public class SmsSender implements MessageSender {

    @Override
    public void sendMessage(String message) {
        System.out.println("📱 SMS sent: " + message);
    }
}
```

### ⭐ C) Abstraction — Notification.java

```java
package notification;

import sender.MessageSender;

public abstract class Notification {

    protected MessageSender sender;

    protected Notification(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void notifyUser();
}
```

### ⭐ D) Refined Abstractions

**AlertNotification.java**

```java
package notification;

import sender.MessageSender;

public class AlertNotification extends Notification {

    public AlertNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser() {
        sender.sendMessage("⚠ System alert!");
    }
}
```

**PromotionNotification.java**

```java
package notification;

import sender.MessageSender;

public class PromotionNotification extends Notification {

    public PromotionNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser() {
        sender.sendMessage("🎉 Big promotion today!");
    }
}
```

### ⭐ E) Client — App.java

```java
import notification.*;
import sender.*;

public class App {
    public static void main(String[] args) {

        Notification alertEmail =
            new AlertNotification(new EmailSender());

        Notification promoSms =
            new PromotionNotification(new SmsSender());

        alertEmail.notifyUser();
        promoSms.notifyUser();
    }
}
```

## 🧪 3. Output

```bash
📧 Email sent: ⚠ System alert!
📱 SMS sent: 🎉 Big promotion today!
```
