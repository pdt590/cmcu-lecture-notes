# 🧵 Ví dụ: Generics

## 🎯 Mục tiêu ví dụ

- Hiểu ý nghĩa của T, E, K, V
- Thấy cách dùng generics trong class + method + collection

## ✅ Ví dụ hoàn chỉnh

### 1️⃣ Generic Class dùng T (Type)

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

### 2️⃣ Generic Collection dùng E (Element)

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

### 3️⃣ Generic Map dùng K, V (Key – Value)

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
