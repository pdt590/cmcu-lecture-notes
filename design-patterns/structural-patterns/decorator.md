# 🎯 Mini Project: Coffee Shop – Decorator Pattern

> Decorator: Cho phép bạn thêm chức năng mới vào một đối tượng mà không thay đổi cấu trúc của nó

Chủ đề: Coffee Shop với đồ uống (Beverage) có thể thêm topping
→ Ở đây: Cà phê có thể thêm sữa, thêm đường, thêm caramel…

## 📁 1. Cấu trúc Project

```java
decorator-demo/
│
├── App.java
│
├── beverage/
│     ├── Beverage.java
│     ├── Coffee.java
│     └── MilkTea.java
│
└── decorator/
      ├── ToppingDecorator.java
      ├── Milk.java
      └── Sugar.java
```

## 🧩 2. Code chi tiết

### ⭐ A) Component interface — Beverage.java

```java
package beverage;

public interface Beverage {
    String getDescription();
    double cost();
}
```

### ⭐ B) Concrete Components

**Coffee.java**

```java
package beverage;

public class Coffee implements Beverage {

    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}
```

**MilkTea.java**

```java
package beverage;

public class MilkTea implements Beverage {

    @Override
    public String getDescription() {
        return "Milk Tea";
    }

    @Override
    public double cost() {
        return 3.0;
    }
}
```

### ⭐ C) Decorator abstract class — ToppingDecorator.java

```java
package decorator;

import beverage.Beverage;

public abstract class ToppingDecorator implements Beverage {
    protected Beverage beverage;

    public ToppingDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
```

### ⭐ D) Concrete Decorators

**Milk.java**

```java
package decorator;

import beverage.Beverage;

public class Milk extends ToppingDecorator {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }
}
```

**Sugar.java**

```java
package decorator;

import beverage.Beverage;

public class Sugar extends ToppingDecorator {

    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }
}
```

### ⭐ E) Main Application — App.java

```java
import beverage.Beverage;
import beverage.Coffee;
import beverage.MilkTea;
import decorator.Milk;
import decorator.Sugar;

public class App {
    public static void main(String[] args) {

        // Cà phê + sữa + đường
        Beverage drink1 = new Coffee();
        drink1 = new Milk(drink1);
        drink1 = new Sugar(drink1);

        System.out.println(drink1.getDescription() + " => $" + drink1.cost());

        // Trà sữa + sữa
        Beverage drink2 = new MilkTea();
        drink2 = new Milk(drink2);

        System.out.println(drink2.getDescription() + " => $" + drink2.cost());
    }
}
```

🧪 3. Output khi chạy

```bash
Coffee, Milk, Sugar => $2.7
Milk Tea, Milk => $3.5
```

## 🎉 4. Bạn học được gì?

- Dễ dàng thêm topping mà không cần sửa lớp Coffee/MilkTea
- Các decorator có thể xếp chồng lên nhau
- Mô hình tuyệt vời cho app đồ uống, pizza, UI widgets, data streams…
