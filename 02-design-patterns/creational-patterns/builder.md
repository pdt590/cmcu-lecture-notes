# 🎯 Mini Project: User Profile – Builder Pattern

> **Builder**: Giúp tạo ra các đối tượng phức tạp bằng cách xây dựng đối tượng qua nhiều bước

Bạn sẽ tạo object phức tạp (User) với nhiều thuộc tính tùy chọn:

- name (bắt buộc)
- age (tuỳ chọn)
- email (tuỳ chọn)
- phone (tuỳ chọn)
- address (tuỳ chọn)

Builder Pattern giúp khởi tạo object dễ đọc, tránh constructor dài ngoằng.

## 📁 1. Cấu trúc Project

```java
builder-demo/
│
├── App.java
└── user/
      ├── User.java
      └── UserBuilder.java
```

## 🧩 2. Code chi tiết

### ⭐ A) User class — User.java

```java
package user;

public class User {
    private final String name;   // required
    private final int age;       // optional
    private final String email;  // optional
    private final String phone;  // optional
    private final String address;// optional

    // Private constructor: chỉ Builder được gọi
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "User {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Static inner Builder class
    public static class UserBuilder {

        private final String name;     // required
        private int age = -1;          // optional default
        private String email = "";
        private String phone = "";
        private String address = "";

        public UserBuilder(String name) {
            this.name = name;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

## ⭐ B) Main Application — App.java

```java
import user.User;

public class App {
    public static void main(String[] args) {

        User user1 = new User.UserBuilder("Alice")
                .age(25)
                .email("alice@example.com")
                .address("Hanoi")
                .build();

        User user2 = new User.UserBuilder("Bob")
                .phone("0981234567")
                .build();

        User user3 = new User.UserBuilder("Charlie")
                .age(30)
                .email("charlie@mail.com")
                .phone("0123456789")
                .address("Saigon")
                .build();

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
```

## 🧪 3. Output khi chạy

```bash
User {name='Alice', age=25, email='alice@example.com', phone='', address='Hanoi'}
User {name='Bob', age=-1, email='', phone='0981234567', address=''}
User {name='Charlie', age=30, email='charlie@mail.com', phone='0123456789', address='Saigon'}
```

## 🎉 4. Bạn học được gì?

- Tránh constructor dài với nhiều tham số
- Code dễ đọc: .email(...).phone(...).age(...)
- Builder đảm bảo tính bất biến (immutable object)
- Tạo object phức tạp cực kỳ sạch sẽ
- Phù hợp cho config, object lớn, entity có nhiều field optional
