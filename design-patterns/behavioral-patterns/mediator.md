# 🎯 Mini Project: Chat Room - Mediator Pattern

> **Mediator**: Giảm độ phức tạp của giao tiếp giữa các đối tượng bằng cách giới thiệu một đối tượng trung gian để kiểm soát quá trình giao tiếp

Chủ đề: Chat Room
→ Các User không giao tiếp trực tiếp với nhau, mà thông qua Mediator (ChatRoom).

## 🧱 1. Cấu trúc Project

```java
mediator-demo/
│
├── App.java
│
├── mediator/
│     ├── ChatMediator.java
│     └── ChatMediatorImpl.java
│
└── user/
      ├── User.java
      └── UserImpl.java
```

## 🧩 2. Code chi tiết

### ⭐ A) Mediator interface — ChatMediator.java

```java
package mediator;

import user.User;

public interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}
```

### ⭐ B) Mediator implementation — ChatMediatorImpl.java

```java
package mediator;

import user.User;
import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {

    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String msg, User sender) {
        for (User user : users) {
            // Không gửi lại cho chính mình
            if (user != sender) {
                user.receive(msg);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
```

### ⭐ C) User abstract class — User.java

```java
package user;

import mediator.ChatMediator;

public abstract class User {

    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);

    public String getName() {
        return name;
    }
}
```

### ⭐ D) Concrete User — UserImpl.java

```java
package user;

import mediator.ChatMediator;

public class UserImpl extends User {

    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + " gửi: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + " nhận: " + msg);
    }
}
```

### ⭐ E) Main Application — App.java

```java
import mediator.ChatMediator;
import mediator.ChatMediatorImpl;
import user.User;
import user.UserImpl;

public class App {
    public static void main(String[] args) {

        ChatMediator mediator = new ChatMediatorImpl();

        User user1 = new UserImpl(mediator, "Alice");
        User user2 = new UserImpl(mediator, "Bob");
        User user3 = new UserImpl(mediator, "Charlie");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.send("Hello mọi người!");
        user3.send("Chào Alice!");
    }
}
```

## 🧪 3. Kết quả khi chạy

```bash
Alice gửi: Hello mọi người!
Bob nhận: Hello mọi người!
Charlie nhận: Hello mọi người!

Charlie gửi: Chào Alice!
Alice nhận: Chào Alice!
Bob nhận: Chào Alice!
```

## 🎉 4. Bạn học được gì?

- User không giao tiếp trực tiếp → giảm phụ thuộc (loose coupling)
- Mediator kiểm soát toàn bộ luồng tương tác
- Dễ mở rộng thêm logic: log chat, block user, private chat…
- Mô phỏng chính xác cách ứng dụng chat vận hành
