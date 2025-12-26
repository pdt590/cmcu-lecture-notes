# 🧵 Ví dụ: Generics với ký tự T, E, K, V

## 1️⃣ Generic Class dùng T (Type)

```java
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
```

📌 T = Type bất kỳ

## 2️⃣ Generic Class dùng E (Element) trong Collection

```java
class SimpleList<E> {
    private Object[] data = new Object[10];
    private int size = 0;

    public void add(E element) {
        data[size++] = element;
    }

    public E get(int index) {
        return (E) data[index];
    }
}
```

📌 E = phần tử trong collection

## 3️⃣ Generic Class dùng K, V (Key – Value) trong Map

```java
import java.util.HashMap;
import java.util.Map;

class SimpleCache<K, V> {
    private Map<K, V> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }
}
```

📌 K = Key
📌 V = Value

4️⃣ Sử dụng trong main

```java
public class GenericDemo {
    public static void main(String[] args) {

        // T
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println(stringBox.get());

        // E
        SimpleList<Integer> intList = new SimpleList<>();
        intList.add(10);
        intList.add(20);
        System.out.println(intList.get(1));

        // K, V
        SimpleCache<String, Integer> cache = new SimpleCache<>();
        cache.put("apple", 5);
        System.out.println(cache.get("apple"));
    }
}
```

# 🎯 Ví dụ Generics với Class, Interface và Method

## 1️⃣ Generic Interface

```java
// Interface generic
public interface Repository<T> {
    void save(T item);
    T findById(int id);
}
```

📌 T đại diện cho kiểu dữ liệu tổng quát

## 2️⃣ Generic Class implements Generic Interface

```java
import java.util.ArrayList;
import java.util.List;

// Class generic
public class MemoryRepository<T> implements Repository<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public void save(T item) {
        data.add(item);
    }

    @Override
    public T findById(int id) {
        if (id < data.size()) {
            return data.get(id);
        }
        return null;
    }
}
```

📌 Class này không quan tâm T là gì

## 3️⃣ Generic Method

```java
public class Printer {

    // Method generic độc lập với class
    public static <E> void print(E element) {
        System.out.println(element);
    }
}
```

📌 <E> chỉ tồn tại trong method

## 4️⃣ Sử dụng Generics

```java
public class Main {
    public static void main(String[] args) {

        // Generic Class + Interface
        Repository<String> stringRepo = new MemoryRepository<>();
        stringRepo.save("Java");
        stringRepo.save("Generics");

        String value = stringRepo.findById(0);
        System.out.println(value);

        // Generic Method
        Printer.print(100);
        Printer.print("Hello");
        Printer.print(3.14);
    }
}
```

## 🧠 Tóm tắt nhanh

| Thành phần | Generics              |
| ---------- | --------------------- |
| Interface  | `Repository<T>`       |
| Class      | `MemoryRepository<T>` |
| Method     | `<E> void print(E e)` |
