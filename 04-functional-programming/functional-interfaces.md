# Functional interfaces

## 🎯 So sánh giữa không sử dụng và có sử dụng Functional Interface

**Bài toán**: Ta cần viết logic tính toán 2 số (cộng, trừ, nhân, chia) và có thể thay đổi hành vi dễ dàng

### 1️⃣ KHÔNG sử dụng Functional Interface (cách truyền thống)

**Cách 1: Mỗi hành vi là một class riêng**

```java
public class AddOperation {
    public int calculate(int a, int b) {
        return a + b;
    }
}

public class MultiplyOperation {
    public int calculate(int a, int b) {
        return a * b;
    }
}
```

**Sử dụng**

```java
public class Main {
    public static void main(String[] args) {
        AddOperation add = new AddOperation();
        MultiplyOperation mul = new MultiplyOperation();

        System.out.println(add.calculate(5, 3));
        System.out.println(mul.calculate(5, 3));
    }
}
```

**Nhược điểm**

- Tạo nhiều class dư thừa
- Không linh hoạt
- Khó truyền hành vi như tham số

Vi phạm tư duy Strategy pattern hiện đại

### 2️⃣ CÓ sử dụng Functional Interface + Anonymous Class

**Định nghĩa Functional Interface**

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}
```

**Sử dụng bằng Anonymous Class**

```java
public class Main {
    public static void main(String[] args) {

        Calculator add = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        Calculator multiply = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a * b;
            }
        };

        System.out.println(add.calculate(5, 3));
        System.out.println(multiply.calculate(5, 3));
    }
}
```

**Lợi ích**

- Không cần tạo class riêng
- Dễ đọc hơn
- Dùng chung interface
- Truyền được hành vi

### 3️⃣ CÓ sử dụng Functional Interface + Lambda (Java 8+)

```java
public class Main {
    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.calculate(5, 3));
        System.out.println(multiply.calculate(5, 3));
    }
}
```

**Ưu điểm vượt trội**

- Rất ngắn gọn
- Code rõ ý định
- Dễ mở rộng
- Chuẩn Java hiện đại

### 4️⃣ So sánh tổng hợp

| Tiêu chí       | Không dùng FI | FI + Anonymous | FI + Lambda |
| -------------- | ------------- | -------------- | ----------- |
| Số class       | Nhiều         | Ít             | Ít nhất     |
| Độ linh hoạt   | ❌ Thấp       | ✅ Trung bình  | ⭐ Cao      |
| Độ dài code    | ❌ Dài        | ⚠️ Trung bình  | ✅ Ngắn     |
| Truyền hành vi | ❌ Không      | ✅ Có          | ⭐ Rất tốt  |
| Java version   | Mọi           | 6+             | 8+          |

## Functional Interface có sẵn trong java.util.function

### 1️⃣ Predicate\<T>

👉 Nhận input có kiểu T, kiểm tra điều kiện và trả về output kiểu `boolean`

```java
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println(isEven.test(4)); // true
        System.out.println(isEven.test(5)); // false
    }
}
```

📌 Dùng nhiều trong filter()

### 2️⃣ Consumer\<T>

👉 Nhận input có kiểu T, không trả về gì

```java
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> printUpper = s ->
                System.out.println(s.toUpperCase());

        printUpper.accept("java");
    }
}
```

### 3️⃣ Function\<T, R>

👉 Chuyển đổi dữ liệu: Nhận input có kiểu T, trả về output có kiểu R.

```java
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> lengthFunc = s -> s.length();

        System.out.println(lengthFunc.apply("Functional")); // 10
    }
}
```

📌 Dùng trong map()

### 4️⃣ Supplier\<T>

👉 Không có input, trả về output kiểu T.

```java
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<Double> randomValue = () -> Math.random();

        System.out.println(randomValue.get());
    }
}
```

### 5️⃣ UnaryOperator\<T>

👉 Function nhận vào 1 input duy nhất và trả về output cùng kiểu dữ liệu T.

```java
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        UnaryOperator<Integer> square =
                n -> n * n;

        System.out.println(square.apply(5)); // 25
    }
}
```

### 6️⃣ BinaryOperator\<T>

👉 BiFunction nhận vào 2 input và trả về 1 output. Tât cả cùng kiểu dữ liệu T.

```java
import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {
        BinaryOperator<Integer> max =
                (a, b) -> a > b ? a : b;

        System.out.println(max.apply(3, 7)); // 7
    }
}
```

📌 Dùng trong reduce()
