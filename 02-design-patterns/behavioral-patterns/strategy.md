# 🎯 Mini Project: Shipping Fee Calculator - Strategy Pattern

> **Strategy**: Cho phép thay đổi thuật toán hoặc hành vi của một đối tượng một cách linh hoạt trong thời gian chạy

Tạo hệ thống tính phí vận chuyển mà thuật toán tính phí có thể thay đổi linh hoạt bằng Strategy.
Ứng dụng thực tế:

- Thay đổi thuật toán xử lý mà không sửa code core
- Dùng Polymorphism thay cho nhiều câu if/else

## 🧱 1. Cấu trúc Project

```java
strategy-demo/
│
├── App.java
├── strategy/
│     ├── ShippingStrategy.java
│     ├── StandardShipping.java
│     ├── ExpressShipping.java
│     └── GrabShipping.java
│
└── order/
      └── ShippingContext.java
```

## 🧩 2. Code đầy đủ

### ⭐ A) Strategy Interface — ShippingStrategy.java

```java
package strategy;

public interface ShippingStrategy {
    double calculateFee(double distance);
}

⭐ B) Concrete Strategy — StandardShipping.java
package strategy;

public class StandardShipping implements ShippingStrategy {

    @Override
    public double calculateFee(double distance) {
        return distance * 5;  // 5k mỗi km
    }
}
```

### ⭐ C) Concrete Strategy — ExpressShipping.java

```java
package strategy;

public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculateFee(double distance) {
        return 20 + distance * 8;  // phí mở + 8k mỗi km
    }
}
```

### ⭐ D) Concrete Strategy — GrabShipping.java

```java
package strategy;

public class GrabShipping implements ShippingStrategy {

    @Override
    public double calculateFee(double distance) {
        return distance <= 3 ? 25 : 25 + (distance - 3) * 10;
    }
}
```

### ⭐ E) Context Class — ShippingContext.java

```java
package order;

import strategy.ShippingStrategy;

public class ShippingContext {

    private ShippingStrategy strategy;

    // Set strategy khi chạy
    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double distance) {
        return strategy.calculateFee(distance);
    }
}

```

### ⭐ F) Main Application — App.java

```java
import order.ShippingContext;
import strategy.ExpressShipping;
import strategy.GrabShipping;
import strategy.StandardShipping;

public class App {
    public static void main(String[] args) {

        ShippingContext context = new ShippingContext();
        double distance = 10; // 10 km

        // Standard Shipping
        context.setStrategy(new StandardShipping());
        System.out.println("Standard Fee: " + context.executeStrategy(distance));

        // Express Shipping
        context.setStrategy(new ExpressShipping());
        System.out.println("Express Fee: " + context.executeStrategy(distance));

        // Grab Shipping
        context.setStrategy(new GrabShipping());
        System.out.println("Grab Fee: " + context.executeStrategy(distance));
    }
}
```

## 🧪 3. Kết quả khi chạy (10 km)

```bash
Standard Fee: 50.0
Express Fee: 100.0
Grab Fee: 95.0
```

## 🎉 4. Bạn học được gì từ project này?

- Hiểu rõ Strategy Pattern: tách thuật toán ra khỏi logic chính.
- Thay đổi chiến lược runtime (lúc chạy).
- Mở rộng thuật toán dễ dàng mà không sửa code cũ (Open/Closed Principle — SOLID).
- Tổ chức code dạng plug-and-play.
