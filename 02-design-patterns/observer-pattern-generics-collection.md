# 🎯 Mini Project: Event Notification System - Observer Pattern + Generics + Collection

🧩 Bài toán

Hệ thống phát sinh event với nhiều kiểu dữ liệu khác nhau:

- String (log message)
- Integer (progress)
- Order, User, …

Yêu cầu:

- Nhiều observer lắng nghe event
- Thêm / xoá observer linh hoạt
- Generic để tái sử dụng cho nhiều kiểu event
- Collection để quản lý observer

## 📁 1. Cấu trúc project

```java
observer-generic-collection/
│
├── App.java
│
├── observer/
│     ├── Observer.java
│     └── ConsoleObserver.java
│
└── subject/
      ├── Subject.java
      └── EventPublisher.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Observer Interface (Generic)

**Observer.java**

```java
package observer;

public interface Observer<T> {
    void update(T event);
}
```

✔ Generic <T>

### ⭐ B) Subject Interface

**Subject.java**

```java
package subject;

import observer.Observer;

public interface Subject<T> {
    void register(Observer<T> observer);
    void unregister(Observer<T> observer);
    void notifyObservers(T event);
}
```

### ⭐ C) Concrete Subject (dùng Collection)

**EventPublisher.java**

```java
package subject;

import observer.Observer;
import java.util.*;

public class EventPublisher<T> implements Subject<T> {

    private List<Observer<T>> observers = new ArrayList<>();

    @Override
    public void register(Observer<T> observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(T event) {
        for (Observer<T> observer : observers) {
            observer.update(event);
        }
    }
}
```

✔ Collection: List
✔ Generic: <T>

### ⭐ D) Concrete Observer

**ConsoleObserver.java**

```java
package observer;

public class ConsoleObserver<T> implements Observer<T> {

    private String name;

    public ConsoleObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(T event) {
        System.out.println(name + " received event: " + event);
    }
}
```

### ⭐ E) Client — App.java

```java
import observer.ConsoleObserver;
import subject.EventPublisher;

public class App {
    public static void main(String[] args) {

        EventPublisher<String> stringPublisher = new EventPublisher<>();

        ConsoleObserver<String> logger =
                new ConsoleObserver<>("Logger");
        ConsoleObserver<String> audit =
                new ConsoleObserver<>("Audit");

        stringPublisher.register(logger);
        stringPublisher.register(audit);

        stringPublisher.notifyObservers("User logged in");

        System.out.println();

        EventPublisher<Integer> intPublisher = new EventPublisher<>();
        ConsoleObserver<Integer> progress =
                new ConsoleObserver<>("ProgressBar");

        intPublisher.register(progress);
        intPublisher.notifyObservers(75);
    }
}
```

## 🧪 3. Output

```java
Logger received event: User logged in
Audit received event: User logged in

ProgressBar received event: 75
```

## 🎯 4. Khi nào dùng mô hình này?

- Event-driven system
- Notification / Listener
- Logging, Audit
- GUI, Spring Event
