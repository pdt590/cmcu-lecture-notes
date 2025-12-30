# 🧵 Ví dụ về funcational programming

## Lambda expressions

### 1️⃣ Hàm KHÔNG THAM SỐ

🎯 Bài toán: Trả về một số ngẫu nhiên

#### ❌ Không sử dụng Lambda (Anonymous Class)

```java
interface RandomNumber {
    int get();
}

public class NoLambdaNoParam {
    public static void main(String[] args) {

        RandomNumber random = new RandomNumber() {
            @Override
            public int get() {
                return 10;
            }
        };

        System.out.println(random.get());
    }
}
```

#### ✅ Sử dụng Lambda Expression

```java
public class LambdaNoParam {
    public static void main(String[] args) {

        RandomNumber random = () -> 10;

        System.out.println(random.get());
    }
}
```

📌 Cú pháp cần nhớ

> () -> expression

### 2️⃣ Hàm MỘT THAM SỐ

🎯 Bài toán: Kiểm tra số chẵn

#### ❌ Không sử dụng Lambda

```java
interface Checker {
    boolean check(int n);
}

public class NoLambdaOneParam {
    public static void main(String[] args) {

        Checker isEven = new Checker() {
            @Override
            public boolean check(int n) {
                return n % 2 == 0;
            }
        };

        System.out.println(isEven.check(4)); // true
    }
}
```

#### ✅ Sử dụng Lambda

```java
public class LambdaOneParam {
    public static void main(String[] args) {

        Checker isEven = n -> n % 2 == 0;

        System.out.println(isEven.check(4)); // true
    }
}
```

📌 Cú pháp cần nhớ

> n -> expression

### 3️⃣ Hàm NHIỀU THAM SỐ

🎯 Bài toán: Cộng hai số

#### ❌ Không sử dụng Lambda

```java
interface Calculator {
    int calculate(int a, int b);
}

public class NoLambdaMultiParam {
    public static void main(String[] args) {

        Calculator add = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        System.out.println(add.calculate(3, 5)); // 8
    }
}
```

#### ✅ Sử dụng Lambda

```java
public class LambdaMultiParam {
    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;

        System.out.println(add.calculate(3, 5)); // 8
    }
}

```

📌 Cú pháp cần nhớ

> (a, b) -> expression

### 4️⃣ Duyệt List

🎯 Bài toán: In ra danh sách tên

#### ❌ Không sử dụng Lambda (Java < 8)

```java
import java.util.*;

public class NoLambdaForEach {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Java", "Spring", "Docker");

        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }

        // hoặc
        for (String name : names) {
            System.out.println(name);
        }
    }
}
```

#### ✅ Sử dụng Lambda Expression

```java
import java.util.*;

public class LambdaForEach {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Java", "Spring", "Docker");

        names.forEach(name -> System.out.println(name));

        // Ngắn hơn nữa với Method Reference
        // names.forEach(System.out::println);
    }
}
```

### 5️⃣ Lọc phần tử trong List

🎯 Bài toán: Lấy số chẵn

#### ❌ Không sử dụng Lambda

```java
import java.util.*;

public class NoLambdaFilter {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evens = new ArrayList<>();

        for (Integer n : numbers) {
            if (n % 2 == 0) {
                evens.add(n);
            }
        }

        System.out.println(evens);
    }
}
```

#### ✅ Sử dụng Lambda + Stream

```java
import java.util.*;
import java.util.stream.Collectors;

public class LambdaFilter {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evens);
    }
}
```

## Functional interfaces

### 🎯 So sánh giữa không sử dụng và có sử dụng Functional Interface

**Bài toán**: Ta cần viết logic tính toán 2 số (cộng, trừ, nhân, chia) và có thể thay đổi hành vi dễ dàng

#### 1️⃣ KHÔNG sử dụng Functional Interface (cách truyền thống)

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

#### 2️⃣ CÓ sử dụng Functional Interface + Anonymous Class

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

#### 3️⃣ CÓ sử dụng Functional Interface + Lambda (Java 8+)

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

#### 4️⃣ So sánh tổng hợp

| Tiêu chí       | Không dùng FI | FI + Anonymous | FI + Lambda |
| -------------- | ------------- | -------------- | ----------- |
| Số class       | Nhiều         | Ít             | Ít nhất     |
| Độ linh hoạt   | ❌ Thấp       | ✅ Trung bình  | ⭐ Cao      |
| Độ dài code    | ❌ Dài        | ⚠️ Trung bình  | ✅ Ngắn     |
| Truyền hành vi | ❌ Không      | ✅ Có          | ⭐ Rất tốt  |
| Java version   | Mọi           | 6+             | 8+          |

### Ví dụ cho những Functional Interface có sẵn trong java.util.function

#### 1️⃣ Predicate\<T>

👉 Kiểm tra điều kiện – trả về boolean

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

#### 2️⃣ Consumer\<T>

👉 Nhận dữ liệu – không trả về gì

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

#### 3️⃣ Function\<T, R>

👉 Chuyển đổi dữ liệu

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

#### 4️⃣ Supplier\<T>

👉 Cung cấp dữ liệu – không có input

```java
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<Double> randomValue = () -> Math.random();

        System.out.println(randomValue.get());
    }
}
```

#### 5️⃣ UnaryOperator\<T>

👉 Function với input = output

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

#### 6️⃣ BinaryOperator\<T>

👉 BiFunction với 2 input cùng kiểu

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

## Stream API

### 1️⃣ filter – LỌC PHẦN TỬ

🎯 Bài toán: Lấy số chẵn

```java
import java.util.*;
import java.util.stream.*;

public class FilterExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);
    }
}
```

📌 filter(Predicate<T>) → trả về Stream<T>

### 2️⃣ map – CHUYỂN ĐỔI DỮ LIỆU

🎯 Bài toán: Chuyển String → chữ hoa

```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("java", "spring", "docker");

        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);
    }
}
```

📌 map(Function<T, R>)

### 3️⃣ sorted – SẮP XẾP

🎯 Bài toán: Sắp xếp tăng dần

```java
import java.util.*;

public class SortedExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5, 1, 4, 2, 3);

        numbers.stream()
               .sorted()
               .forEach(System.out::println);
    }
}
```

🔁 Sắp xếp giảm dần

```java
numbers.stream()
       .sorted((a, b) -> b - a)
       .forEach(System.out::println);
```

### 4️⃣ forEach – XỬ LÝ TỪNG PHẦN TỬ

🎯 Bài toán: In kèm tiền tố

```java
import java.util.*;

public class ForEachExample {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Java", "Spring");

        names.stream()
             .forEach(name -> System.out.println("Hello " + name));
    }
}
```

📌 forEach(Consumer<T>)
⚠️ Thường dùng cho side-effect, không nên xử lý logic phức tạp

### 5️⃣ collect – THU THẬP KẾT QUẢ

🎯 Bài toán: Lấy danh sách số chẵn

```java
import java.util.*;
import java.util.stream.Collectors;

public class CollectExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evens);
    }
}
```

🔁 Collect sang Set

```java
Set<Integer> set = numbers.stream()
        .collect(Collectors.toSet());
```

### 6️⃣ reduce – TỔNG HỢP / GOM GIÁ TRỊ

🎯 Bài toán: Tính tổng

```java
import java.util.*;

public class ReduceExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(sum); // 15
    }
}
```

🔁 Không dùng identity (trả về Optional)

```java
Optional<Integer> sum = numbers.stream()
        .reduce((a, b) -> a + b);

sum.ifPresent(System.out::println);
```

### 7️⃣ Ví dụ TỔNG HỢP (filter + map + sorted + collect)

🎯 Bài toán: Lấy số chẵn, nhân đôi, sắp xếp giảm dần, lưu vào List

```java
import java.util.*;
import java.util.stream.Collectors;

public class FullStreamExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());

        System.out.println(result); // [12, 8, 4]
    }
}
```

### 8️⃣ Bảng tóm tắt nhanh

| Method  | Mục đích   | Functional Interface |
| ------- | ---------- | -------------------- |
| filter  | Lọc        | Predicate            |
| map     | Chuyển đổi | Function             |
| sorted  | Sắp xếp    | Comparator           |
| forEach | Xử lý      | Consumer             |
| collect | Thu thập   | Collector            |
| reduce  | Tổng hợp   | BinaryOperator       |
