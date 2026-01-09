# 🎯 Mini Project: Data Sorting System - Strategy Pattern + Generics + Collection

🧩 Bài toán

Hệ thống cần sắp xếp dữ liệu cho nhiều kiểu khác nhau (Integer, String, Object …)
và có thể thay đổi thuật toán sắp xếp lúc runtime.

Yêu cầu:

- Strategy → thay đổi thuật toán
- Generics → dùng cho nhiều kiểu
- Collection → quản lý dữ liệu & strategy

## 📁 1. Cấu trúc project

```java
strategy-generic-collection/
│
├── App.java
│
├── strategy/
│     ├── SortStrategy.java
│     ├── AscendingSort.java
│     └── DescendingSort.java
│
└── context/
      └── SortContext.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Strategy Interface (Generic)

**SortStrategy.java**

```java
package strategy;

import java.util.List;

public interface SortStrategy<T extends Comparable<T>> {
    void sort(List<T> data);
}
```

- Generic
- Ràng buộc kiểu (Comparable)
- Dùng Collection (List\<T>)

### ⭐ B) Concrete Strategies

**AscendingSort.java**

```java
package strategy;

import java.util.Collections;
import java.util.List;

public class AscendingSort<T extends Comparable<T>>
        implements SortStrategy<T> {

    @Override
    public void sort(List<T> data) {
        Collections.sort(data);
    }
}
```

**DescendingSort.java**

```java
package strategy;

import java.util.Collections;
import java.util.List;

public class DescendingSort<T extends Comparable<T>>
        implements SortStrategy<T> {

    @Override
    public void sort(List<T> data) {
        Collections.sort(data, Collections.reverseOrder());
    }
}
```

### ⭐ C) Context (quản lý Strategy bằng Collection)

**SortContext.java**

```java
package context;

import strategy.SortStrategy;
import java.util.*;

public class SortContext<T extends Comparable<T>> {

    private Map<String, SortStrategy<T>> strategies = new HashMap<>();

    public void register(String name, SortStrategy<T> strategy) {
        strategies.put(name, strategy);
    }

    public void execute(String name, List<T> data) {
        SortStrategy<T> strategy = strategies.get(name);

        if (strategy == null) {
            throw new IllegalArgumentException("Strategy not found");
        }

        strategy.sort(data);
    }
}
```

- Strategy Pattern
- Generics
- Collection (Map, List)

### ⭐ D) Client — App.java

```java
import context.SortContext;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        SortContext<Integer> context = new SortContext<>();

        context.register("asc", new AscendingSort<>());
        context.register("desc", new DescendingSort<>());

        List<Integer> numbers = new ArrayList<>(List.of(5, 1, 4, 2));

        context.execute("asc", numbers);
        System.out.println("Ascending: " + numbers);

        context.execute("desc", numbers);
        System.out.println("Descending: " + numbers);
    }
}
```

## 🧪 3. Output

```bash
Ascending: [1, 2, 4, 5]
Descending: [5, 4, 2, 1]
```

## 🎯 4. Khi nào dùng thiết kế này?

- Có nhiều thuật toán cho cùng 1 bài toán
- Muốn thay đổi hành vi runtime
- Muốn code generic – tái sử dụng cao
- Rất giống cách Spring quản lý bean bằng Map
