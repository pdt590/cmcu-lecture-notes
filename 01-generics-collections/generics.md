# 🎯 Ví dụ Generic Interface, Class và Method

## 1️⃣ Generic Interface

```java
// Generic interface
public interface Repository<T> {
    void save(T item);
    T findById(int id);
}
```

## 2️⃣ Generic Class implements Generic Interface

```java
import java.util.ArrayList;
import java.util.List;

// Generic class
public class MemoryRepository<T> implements Repository<T> {

    private ArrayList<T> data = new ArrayList<>();

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

## 3️⃣ Generic Method

```java
public class Printer {

    // Generic method độc lập với class
    public static <E> void print(E element) {
        System.out.println(element);
    }
}
```

## 4️⃣ Sử dụng Generics

```java
public class Main {
    public static void main(String[] args) {

        // Generic Class + Interface
        MemoryRepository<String> stringRepo = new MemoryRepository<>();
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

## 4️⃣ Sử dụng trong main

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

# 🧵 Ví dụ: Wildcard (?)

## 🎯 Bối cảnh

- Hệ thống xử lý dữ liệu cảm biến (Sensor)
- Có nhiều loại cảm biến khác nhau
- Dữ liệu được lưu trong mảng (array)
- Áp dụng Wildcards để đọc / ghi / xử lý dữ liệu

## 📂 Cấu trúc project

```java
wildcard-no-collection
│
├── Sensor.java
├── SensorBox.java
├── TemperatureSensor.java
├── PressureSensor.java
├── SensorProcessor.java
└── Main.java
```

## 1️⃣ Lớp cơ sở Sensor

**Sensor.java**

```java
public abstract class Sensor {
    protected double value;

    public Sensor(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " = " + value;
    }
}
```

## 2️⃣ Các lớp con

**TemperatureSensor.java**

```java
public class TemperatureSensor extends Sensor {
    public TemperatureSensor(double value) {
        super(value);
    }
}
```

**PressureSensor.java**

```java
public class PressureSensor extends Sensor {
    public PressureSensor(double value) {
        super(value);
    }
}
```

## 3️⃣ Generic Box

**SensorBox.java**

```java
public class SensorBox<T> {
    private T[] data;
    private int index = 0;

    @SuppressWarnings("unchecked")
    public SensorBox(int size) {
        data = (T[]) new Object[size];
    }

    public void add(T item) {
        data[index++] = item;
    }

    public T get(int i) {
        return data[i];
    }

    public int size() {
        return index;
    }
}
```

## 4️⃣ Xử lý Wildcards

**SensorProcessor.java**

```java
public class SensorProcessor {

    // =========================
    // 1. Upper Bounded Wildcard
    // =========================
    // Chỉ đọc dữ liệu
    public static double average(SensorBox<? extends Sensor> box) {
        double sum = 0;
        for (int i = 0; i < box.size(); i++) {
            sum += box.get(i).getValue();
        }
        // box.add(...) ❌
        return sum / box.size();
    }

    // =========================
    // 2. Lower Bounded Wildcard
    // =========================
    // Ghi dữ liệu
    public static void fillTemperature(
            SensorBox<? super TemperatureSensor> box) {

        box.add(new TemperatureSensor(25));
        box.add(new TemperatureSensor(30));
        // TemperatureSensor t = box.get(0); ❌
    }

    // =========================
    // 3. Unbounded Wildcard
    // =========================
    // Không quan tâm kiểu
    public static void printBox(SensorBox<?> box) {
        for (int i = 0; i < box.size(); i++) {
            System.out.println(box.get(i));
        }
    }
}
```

## 5️⃣ Chạy chương trình

**Main.java**

```java
public class Main {
    public static void main(String[] args) {

        SensorBox<TemperatureSensor> tempBox = new SensorBox<>(5);
        tempBox.add(new TemperatureSensor(20));
        tempBox.add(new TemperatureSensor(22));

        SensorBox<PressureSensor> pressureBox = new SensorBox<>(5);
        pressureBox.add(new PressureSensor(100));
        pressureBox.add(new PressureSensor(110));

        // Upper Bounded
        System.out.println("Avg Temp: " +
                SensorProcessor.average(tempBox));
        System.out.println("Avg Pressure: " +
                SensorProcessor.average(pressureBox));

        // Lower Bounded
        SensorBox<Sensor> sensorBox = new SensorBox<>(5);
        SensorProcessor.fillTemperature(sensorBox);

        // Unbounded
        System.out.println("=== Print Temperature Box ===");
        SensorProcessor.printBox(tempBox);

        System.out.println("=== Print Sensor Box ===");
        SensorProcessor.printBox(sensorBox);
    }
}
```

## 📊 Bảng so sánh Wildcards trong Java

| Tiêu chí             | **Upper Bounded**              | **Lower Bounded**                   | **Unbounded**            |
| -------------------- | ------------------------------ | ----------------------------------- | ------------------------ |
| Cú pháp              | `<? extends T>`                | `<? super T>`                       | `<?>`                    |
| Ý nghĩa              | Kiểu **T hoặc subclass của T** | Kiểu **T hoặc superclass của T**    | Bất kỳ kiểu nào          |
| Đọc dữ liệu (`get`)  | ✔️ An toàn, kiểu trả về là `T` | ⚠️ Chỉ lấy được `Object`            | ⚠️ Chỉ lấy được `Object` |
| Ghi dữ liệu (`add`)  | ❌ Không cho phép               | ✔️ Cho phép `T` và subclass của `T` | ❌ (chỉ `null`)           |
| Mục đích chính       | **Read-only (Producer)**       | **Write-only (Consumer)**           | Không quan tâm kiểu      |
| Cho phép thêm `null` | ✔️                             | ✔️                                  | ✔️                       |
| Ví dụ điển hình      | Tính tổng, thống kê            | Thêm dữ liệu vào collection         | In, duyệt danh sách      |
| Nguy cơ runtime      | ❌ Không                        | ❌ Không                             | ❌ Không                  |
| Liên quan kế thừa    | Áp dụng cho **class con**      | Áp dụng cho **class cha**           | Không xét kế thừa        |
