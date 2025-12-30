# Stream API

## 1️⃣ filter – LỌC PHẦN TỬ

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

## 2️⃣ map – CHUYỂN ĐỔI DỮ LIỆU

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

## 3️⃣ sorted – SẮP XẾP

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

## 4️⃣ forEach – XỬ LÝ TỪNG PHẦN TỬ

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

## 5️⃣ collect – THU THẬP KẾT QUẢ

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

## 6️⃣ reduce – TỔNG HỢP / GOM GIÁ TRỊ

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

## 7️⃣ Ví dụ TỔNG HỢP (filter + map + sorted + collect)

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

## 8️⃣ Bảng tóm tắt nhanh

| Method  | Mục đích   | Functional Interface |
| ------- | ---------- | -------------------- |
| filter  | Lọc        | Predicate            |
| map     | Chuyển đổi | Function             |
| sorted  | Sắp xếp    | Comparator           |
| forEach | Xử lý      | Consumer             |
| collect | Thu thập   | Collector            |
| reduce  | Tổng hợp   | BinaryOperator       |
