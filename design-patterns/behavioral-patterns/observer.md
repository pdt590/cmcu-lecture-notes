# 🎯 Mini Project: Stock Price Notifier - Observer Pattern

> **Observer**: Một đối tượng (quan sát) sẽ được thông báo khi trạng thái của một đối tượng khác (chủ thể) thay đổi

Chủ đề: Hệ thống thông báo giá cổ phiếu (Stock Price Notifier)
→ Khi giá thay đổi, tất cả observers (ứng dụng, người dùng, bot phân tích…) đều được thông báo tự động.

## 🧱 1. Cấu trúc project

```java
observer-demo/
│
├── App.java
├── observer/
│     ├── Observer.java
│     ├── Subject.java
│
├── stock/
│     ├── Stock.java          (Subject)
│     ├── StockObserver.java  (Observer)
│     ├── MobileAppObserver.java
│     └── WebAppObserver.java
```

## 🧩 2. Code chi tiết

### ⭐ A) Subject interface — Subject.java

```java
package observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

### ⭐ B) Observer interface — Observer.java

```java
package observer;

public interface Observer {
    void update(double price);
}

```

### ⭐ C) Concrete Subject — Stock.java

```java
package stock;

import observer.Observer;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private double price;

    public void setPrice(double newPrice) {
        System.out.println("Stock price updated: " + newPrice);
        this.price = newPrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(price);
        }
    }
}
```

### ⭐ D) Concrete Observer — StockObserver.java (generic)

```java
package stock;

import observer.Observer;

public class StockObserver implements Observer {

    private String name;

    public StockObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(double price) {
        System.out.println(name + " nhận được thông báo giá mới: " + price);
    }
}
```

### ⭐ E) Observer mô phỏng App Mobile — MobileAppObserver.java

```java
package stock;

import observer.Observer;

public class MobileAppObserver implements Observer {

    @Override
    public void update(double price) {
        System.out.println("[Mobile App] Giá mới: " + price);
    }
}
```

### ⭐ F) Observer mô phỏng App Web — WebAppObserver.java

```java
package stock;

import observer.Observer;

public class WebAppObserver implements Observer {

    @Override
    public void update(double price) {
        System.out.println("[Web Dashboard] Giá cổ phiếu vừa đổi: " + price);
    }
}
```

### ⭐ G) Main — App.java

```java
import stock.Stock;
import stock.StockObserver;
import stock.MobileAppObserver;
import stock.WebAppObserver;

public class App {
    public static void main(String[] args) {

        Stock stock = new Stock();

        // Đăng ký observers
        stock.registerObserver(new StockObserver("User A"));
        stock.registerObserver(new StockObserver("User B"));
        stock.registerObserver(new MobileAppObserver());
        stock.registerObserver(new WebAppObserver());

        // Thay đổi giá -> mọi observer đều nhận thông báo
        stock.setPrice(120.5);
        stock.setPrice(128.3);
    }
}
```

## 🧪 3. Kết quả khi chạy

```bash
Stock price updated: 120.5
User A nhận được thông báo giá mới: 120.5
User B nhận được thông báo giá mới: 120.5
[Mobile App] Giá mới: 120.5
[Web Dashboard] Giá cổ phiếu vừa đổi: 120.5

Stock price updated: 128.3
User A nhận được thông báo giá mới: 128.3
User B nhận được thông báo giá mới: 128.3
[Mobile App] Giá mới: 128.3
[Web Dashboard] Giá cổ phiếu vừa đổi: 128.3
```

## 🎉 4. Bạn học được gì?

- Hiểu rõ Observer Pattern
- Tách biệt Subject & Observer (loose coupling)
- Tự động cập nhật trạng thái — không cần polling
- Mô phỏng thực tế (stock price, weather, notifications…)