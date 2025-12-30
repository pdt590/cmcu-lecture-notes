# Lambda expressions

## 1️⃣ Hàm KHÔNG THAM SỐ

🎯 Bài toán: Trả về một số ngẫu nhiên

### ❌ Không sử dụng Lambda (Anonymous Class)

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

### ✅ Sử dụng Lambda Expression

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

## 2️⃣ Hàm MỘT THAM SỐ

🎯 Bài toán: Kiểm tra số chẵn

### ❌ Không sử dụng Lambda

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

### ✅ Sử dụng Lambda

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

## 3️⃣ Hàm NHIỀU THAM SỐ

🎯 Bài toán: Cộng hai số

### ❌ Không sử dụng Lambda

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

### ✅ Sử dụng Lambda

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

## 4️⃣ Duyệt List

🎯 Bài toán: In ra danh sách tên

### ❌ Không sử dụng Lambda (Java < 8)

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

### ✅ Sử dụng Lambda Expression

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

## 5️⃣ Lọc phần tử trong List

🎯 Bài toán: Lấy số chẵn

### ❌ Không sử dụng Lambda

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

### ✅ Sử dụng Lambda + Stream

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
