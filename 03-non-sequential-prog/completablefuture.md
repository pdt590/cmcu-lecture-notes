# 🧵 Ví dụ: Async sử dụng CompletableFuture

## 🎯 Bài toán **đơn giản**

Giả sử:

- Có một tác vụ mất thời gian (giả lập bằng sleep)
- Ta không muốn chương trình bị block

Khi tác vụ xong thì xử lý kết quả

### ✅ Code hoàn chỉnh

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

### 🧪 Kết quả chạy

```bash
Start
Main thread continues...
Result: Hello Async
```

👉 Main thread không chờ tác vụ hoàn thành.

## 🎯 Bài toán **nâng cao**

- Chạy một tác vụ bất đồng bộ
- Biến đổi dữ liệu
- Tiêu thụ kết quả
- Chạy hành động cuối

Minh họa blocking vs non-blocking

### ✅ Code hoàn chỉnh

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

### 🧪 Kết quả chạy (tham khảo)

```java
Main thread starts
Main thread continues...
thenApply: hello
thenAccept: HELLO
thenRun: All tasks finished
After join()
After get()
```

### 🧠 Giải thích từng phương thức

#### 1️⃣ supplyAsync()

```java
CompletableFuture.supplyAsync(() -> "hello");
```

- Chạy bất đồng bộ
- Có giá trị trả về

#### 2️⃣ thenApply()

```java
.thenApply(result -> result.toUpperCase());
```

- Biến đổi dữ liệu
- Trả về CompletableFuture mới

#### 3️⃣ thenAccept()

```java
.thenAccept(result -> System.out.println(result));
```

- Nhận dữ liệu
- Không trả về kết quả

#### 4️⃣ thenRun()

```java
.thenRun(() -> System.out.println("Done"));
```

- Chạy sau khi hoàn thành
- Không nhận dữ liệu, không trả về

#### 5️⃣ join()

```java
future.join();
```

- Blocking
- Ném unchecked exception
- Dùng nhiều trong lambda / stream

#### 6️⃣ get()

```java
future.get();
```

- Blocking
- Ném checked exception
- Phổ biến trong code truyền thống
