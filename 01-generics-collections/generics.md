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

# 🎯 Ví dụ Generic Interface, Class và Method

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

## 🧠 Phân tích nhanh

### 🔼 Upper Bounded <? extends Sensor>

- Đọc an toàn
- Không ghi
- Dùng cho tính toán, thống kê

### 🔽 Lower Bounded <? super TemperatureSensor>

- Ghi an toàn
- Lấy ra chỉ Object
- Dùng cho nạp dữ liệu

### ⚪ Unbounded <?>

- Duyệt, in, log
- Không ghi
- Dùng khi không quan tâm kiểu

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

# 🧵 Ví dụ: Wildcard (?) với Collections

## 🎯 Mục tiêu

- Hiểu `<? extends T>` (Upper Bounded)
- Hiểu `<? super T>` (Lower Bounded)
- Hiểu `<?>` (Unbounded)
- Biết khi nào đọc được, khi nào ghi được dữ liệu

## 📂 Cấu trúc project

```java
generic-wildcard-demo
│
├── Product.java
├── Electronics.java
├── Food.java
├── ProductService.java
└── Main.java
```

## 1️⃣ Lớp cơ sở Product

```java
public abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
```

## 2️⃣ Các lớp con

**Electronics.java**

```java
public class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price);
    }
}
```

**Food.java**

```java
public class Food extends Product {
    public Food(String name, double price) {
        super(name, price);
    }
}
```

## 3️⃣ Service sử dụng Wildcards

**ProductService.java**

```java
import java.util.List;

public class ProductService {

    // =========================
    // 1. Upper Bounded Wildcard
    // =========================
    // Chỉ đọc dữ liệu (READ ONLY)
    public static double calculateTotalPrice(List<? extends Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        // products.add(new Product(...)); ❌ KHÔNG cho phép
        return total;
    }

    // =========================
    // 2. Lower Bounded Wildcard
    // =========================
    // Ghi dữ liệu (WRITE)
    public static void addElectronics(List<? super Electronics> list) {
        list.add(new Electronics("Laptop", 1500));
        list.add(new Electronics("Phone", 800));

        // Electronics e = list.get(0); ❌ Không an toàn
    }

    // =========================
    // 3. Unbounded Wildcard
    // =========================
    // Không quan tâm kiểu dữ liệu
    public static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
```

## 4️⃣ Chương trình chạy chính

**Main.java**

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Electronics> electronics = new ArrayList<>();
        electronics.add(new Electronics("TV", 1200));
        electronics.add(new Electronics("Tablet", 600));

        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Pizza", 20));
        foods.add(new Food("Burger", 15));

        // Upper Bounded
        System.out.println("Total Electronics Price: "
                + ProductService.calculateTotalPrice(electronics));
        System.out.println("Total Food Price: "
                + ProductService.calculateTotalPrice(foods));

        // Lower Bounded
        List<Product> products = new ArrayList<>();
        ProductService.addElectronics(products);
        System.out.println("Products size after adding electronics: " + products.size());

        // Unbounded
        ProductService.printList(electronics);
        ProductService.printList(foods);
    }
}
```

## 🔍 Phân tích & Ghi nhớ quan trọng

### 🔼 Upper Bounded <? extends T>

`List<? extends Product>`

- Dùng khi chỉ đọc
- Không thể add()
- Áp dụng: tính toán, thống kê

### 🔽 Lower Bounded <? super T>

`List<? super Electronics>`

- Dùng khi chỉ ghi
- Lấy ra chỉ là Object
- Áp dụng: thêm dữ liệu vào collection

### ⚪ Unbounded <?>

`List<?>`

- Dùng khi không quan tâm kiểu
- Đọc dưới dạng Object
- Không add() (trừ null)

## 🧠 Quy tắc vàng – PECS

> Producer Extends – Consumer Super

| Mục đích            | Wildcard  |
| ------------------- | --------- |
| Đọc dữ liệu         | `extends` |
| Ghi dữ liệu         | `super`   |
| Không quan tâm kiểu | `?`       |
