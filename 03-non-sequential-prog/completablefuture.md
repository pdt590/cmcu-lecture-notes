# 🎯 Bài toán **đơn giản**

Giả sử:

- Có một tác vụ mất thời gian (giả lập bằng sleep)
- Ta không muốn chương trình bị block

Khi tác vụ xong thì xử lý kết quả

## ✅ Code hoàn chỉnh

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void main(String[] args) {

        System.out.println("Start");

        CompletableFuture<String> future =
            CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000); // giả lập xử lý lâu
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello Async";
            });

        // Xử lý kết quả khi hoàn thành (không block)
        future.thenAccept(result ->
            System.out.println("Result: " + result)
        );

        System.out.println("Main thread continues...");

        // Giữ chương trình sống để thấy kết quả
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## 🧪 Kết quả chạy

```bash
Start
Main thread continues...
Result: Hello Async
```

👉 Main thread không chờ tác vụ hoàn thành.

# 🎯 Bài toán **nâng cao**

- Chạy một tác vụ bất đồng bộ
- Biến đổi dữ liệu
- Tiêu thụ kết quả
- Chạy hành động cuối

Minh họa blocking vs non-blocking

## ✅ Code hoàn chỉnh

```ruby
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Main thread starts");

        // 1️⃣ supplyAsync(): tạo tác vụ bất đồng bộ, có giá trị trả về
        CompletableFuture<String> future =
            CompletableFuture.supplyAsync(() -> {
                sleep(1000);
                return "hello";
            })

            // 2️⃣ thenApply(): biến đổi kết quả
            .thenApply(result -> {
                System.out.println("thenApply: " + result);
                return result.toUpperCase();
            })

            // 3️⃣ thenAccept(): tiêu thụ kết quả (không trả về)
            .thenAccept(result ->
                System.out.println("thenAccept: " + result)
            )

            // 4️⃣ thenRun(): chạy khi tất cả hoàn thành (không nhận dữ liệu)
            .thenRun(() ->
                System.out.println("thenRun: All tasks finished")
            );

        System.out.println("Main thread continues...");

        // 5️⃣ join(): chờ hoàn thành (unchecked exception)
        future.join();
        System.out.println("After join()");

        // 6️⃣ get(): chờ hoàn thành (checked exception)
        future.get();
        System.out.println("After get()");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## 🧪 Kết quả chạy (tham khảo)

```java
Main thread starts
Main thread continues...
thenApply: hello
thenAccept: HELLO
thenRun: All tasks finished
After join()
After get()
```

## 🧠 Giải thích từng phương thức

### 1️⃣ supplyAsync()

```java
CompletableFuture.supplyAsync(() -> "hello");
```

- Chạy bất đồng bộ
- Có giá trị trả về

### 2️⃣ thenApply()

```java
.thenApply(result -> result.toUpperCase());
```

- Biến đổi dữ liệu
- Trả về CompletableFuture mới

### 3️⃣ thenAccept()

```java
.thenAccept(result -> System.out.println(result));
```

- Nhận dữ liệu
- Không trả về kết quả

### 4️⃣ thenRun()

```java
.thenRun(() -> System.out.println("Done"));
```

- Chạy sau khi hoàn thành
- Không nhận dữ liệu, không trả về

### 5️⃣ join()

```java
future.join();
```

- Blocking
- Ném unchecked exception
- Dùng nhiều trong lambda / stream

### 6️⃣ get()

```java
future.get();
```

- Blocking
- Ném checked exception
- Phổ biến trong code truyền thống

# thenCombine() và allOf()

## Ví dụ thenCombine()

> Kết hợp kết quả của 2 CompletableFuture độc lập

🎯 Mục tiêu

- Chạy 2 tác vụ song song
- Khi cả hai hoàn thành, kết hợp kết quả

### Code ví dụ

```java
import java.util.concurrent.CompletableFuture;

public class ThenCombineDemo {

    public static void main(String[] args) {

        CompletableFuture<Integer> future1 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    return 10;
                });

        CompletableFuture<Integer> future2 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1500);
                    return 20;
                });

        // thenCombine: chỉ chạy khi future1 và future2 đều hoàn thành
        CompletableFuture<Integer> combined =
                future1.thenCombine(future2, (r1, r2) -> {
                    return r1 + r2;
                });

        // lấy kết quả
        Integer result = combined.join();
        System.out.println("Kết quả thenCombine = " + result);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### Giải thích

- future1 và future2 chạy song song
- `thenCombine()`:
  - Chờ cả hai xong
  - Nhận 2 kết quả
  - Trả về CompletableFuture mới

## Ví dụ allOf()

> Chờ nhiều CompletableFuture hoàn thành (không quan tâm kết quả trực tiếp)

🎯 Mục tiêu

- Chạy nhiều tác vụ song song
- Chờ tất cả hoàn thành
- Sau đó xử lý tiếp

### Code ví dụ

```java
import java.util.concurrent.CompletableFuture;
import java.util.List;

public class AllOfDemo {

    public static void main(String[] args) {

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    return "Task 1";
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1500);
                    return "Task 2";
                });

        CompletableFuture<String> f3 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(500);
                    return "Task 3";
                });

        // allOf: chờ tất cả future hoàn thành
        CompletableFuture<Void> all =
                CompletableFuture.allOf(f1, f2, f3);

        // xử lý sau khi tất cả hoàn thành
        all.thenRun(() -> {
            System.out.println("Tất cả task đã hoàn thành");

            System.out.println(f1.join());
            System.out.println(f2.join());
            System.out.println(f3.join());
        }).join();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### Giải thích

- `allOf()`:
  - Trả về `CompletableFuture<Void>`
  - Không chứa kết quả
- Muốn lấy kết quả → `join()` từng future

## [Tổng kết] So sánh nhanh

| Tiêu chí  | thenCombine          | allOf                   |
| --------- | -------------------- | ----------------------- |
| Số future | 2                    | Nhiều                   |
| Kết quả   | Có                   | Không                   |
| Dùng khi  | Kết hợp 2 kết quả    | Chờ tất cả              |
| Trả về    | CompletableFuture\<T> | CompletableFuture\<Void> |
