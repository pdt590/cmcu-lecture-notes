# 🧵 Ví dụ: Multithread

## 🎯 Sử dụng Thread cơ bản

Tạo 2 luồng:

- Luồng 1 in số 1 → 5
- Luồng 2 in chữ A → E

Hai luồng chạy song song

### 📌 Code hoàn chỉnh

```java
class NumberThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("NumberThread: " + i);
            try {
                Thread.sleep(500); // nghỉ 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

```java
class LetterThread extends Thread {

    @Override
    public void run() {
        for (char c = 'A'; c <= 'E'; c++) {
            System.out.println("LetterThread: " + c);
            try {
                Thread.sleep(500); // nghỉ 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

```java
public class MultiThreadDemo {

    public static void main(String[] args) {

        NumberThread t1 = new NumberThread();
        LetterThread t2 = new LetterThread();

        t1.start(); // tạo luồng mới
        t2.start(); // tạo luồng mới

        System.out.println("Main thread finished");
    }
}
```

### 🧪 Kết quả chạy (ví dụ)

```bash
Main thread finished
NumberThread: 1
LetterThread: A
NumberThread: 2
LetterThread: B
NumberThread: 3
LetterThread: C
...
```

👉 Thứ tự không cố định, vì hệ điều hành quyết định lịch chạy thread.

### 🧠 Giải thích quan trọng

🔹 Vì sao dùng start() chứ không gọi run()?

| Gọi       | Ý nghĩa                                        |
| --------- | ---------------------------------------------- |
| `start()` | Tạo **luồng mới**, gọi `run()`                 |
| `run()`   | Chạy như **hàm bình thường**, không tạo thread |

## 🎯 Sử dụng Thread đầy đủ

- Tạo 2 thread
- Cho chúng chạy song song
- Main thread chờ 2 thread kết thúc

### 📌 Code hoàn chỉnh

```java
class MyThread extends Thread {

    // run(): chứa logic sẽ chạy trong thread
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " đang chạy, i = " + i);
            try {
                // sleep(): tạm dừng thread 1 giây
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " bị gián đoạn");
            }
        }
        System.out.println(getName() + " kết thúc");
    }
}

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        System.out.println("Trạng thái trước khi start:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        // start(): tạo luồng mới và gọi run()
        t1.start();
        t2.start();

        System.out.println("\nTrạng thái sau khi start:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        // join(): main thread chờ t1 và t2 kết thúc
        t1.join();
        t2.join();

        System.out.println("\nSau khi join:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        System.out.println("Main thread kết thúc");
    }
}
```

### 🎯 Tóm tắt

| Phương thức | Ý nghĩa             |
| ----------- | ------------------- |
| start()     | Tạo thread mới      |
| run()       | Logic thread        |
| sleep()     | Tạm dừng            |
| join()      | Chờ thread          |
| isAlive()   | Kiểm tra trạng thái |

## 🎯 Sử dụng Runnable cơ bản

Tạo 2 luồng:

- Luồng 1 in số 1 → 5
- Luồng 2 in chữ A → E

Hai luồng chạy song song

> Implement lại ví dụ của Thread thay thế bằng Runnable

### 📌 Code hoàn chỉnh

```java
class NumberRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("NumberRunnable: " + i);
            try {
                Thread.sleep(500); // nghỉ 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

```java
class LetterRunnable implements Runnable {

    @Override
    public void run() {
        for (char c = 'A'; c <= 'E'; c++) {
            System.out.println("LetterRunnable: " + c);
            try {
                Thread.sleep(500); // nghỉ 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

```java
public class RunnableDemo {

    public static void main(String[] args) {

        Runnable r1 = new NumberRunnable();
        Runnable r2 = new LetterRunnable();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start(); // tạo luồng mới
        t2.start(); // tạo luồng mới

        System.out.println("Main thread finished");
    }
}
```

### 🧪 Kết quả chạy (ví dụ)

```bash
Main thread finished
NumberRunnable: 1
LetterRunnable: A
NumberRunnable: 2
LetterRunnable: B
...
```

👉 Thứ tự không cố định, do scheduler quyết định.

## 🎯 Sử dụng Runnable đầy đủ

- Tạo 2 thread
- Cho chúng chạy song song
- Main thread chờ 2 thread kết thúc

> Implement lại ví dụ của Thread thay thế bằng Runnable

### 📌 Code hoàn chỉnh

```java
class MyRunnable implements Runnable {

    // run(): logic sẽ chạy trong thread
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " đang chạy, i = " + i);
            try {
                // sleep(): tạm dừng thread hiện tại 1 giây
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(threadName + " bị gián đoạn");
            }
        }

        System.out.println(threadName + " kết thúc");
    }
}

public class RunnableDemo {
    public static void main(String[] args) throws InterruptedException {

        // Tạo đối tượng Runnable
        MyRunnable task = new MyRunnable();

        // Gắn Runnable vào Thread
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        System.out.println("Trạng thái trước khi start:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        // start(): tạo luồng mới và gọi run()
        t1.start();
        t2.start();

        System.out.println("\nTrạng thái sau khi start:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        // join(): main thread chờ t1 và t2 kết thúc
        t1.join();
        t2.join();

        System.out.println("\nSau khi join:");
        System.out.println("t1 isAlive? " + t1.isAlive());
        System.out.println("t2 isAlive? " + t2.isAlive());

        System.out.println("Main thread kết thúc");
    }
}
```

## 🧵 So sánh Thread và Runnable?

### 1️⃣ Dùng Thread khi nào?

🔹 Khi nên dùng

- Ví dụ học tập, demo
- Cần ghi đè hành vi của Thread
- Cần tùy biến Thread (name, priority, interrupt handling)

🔹 Ví dụ

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in Thread");
    }
}

public class Main {
    public static void main(String[] args) {
        new MyThread().start();
    }
}
```

❌ Hạn chế

- Java không hỗ trợ đa kế thừa
- Gắn chặt logic với Thread
- Khó tái sử dụng

### 2️⃣ Dùng Runnable khi nào?

🔹 Khi nên dùng (KHUYẾN NGHỊ)

- Ứng dụng thực tế
- Tách logic và cơ chế chạy
- Dùng với Thread pool / Executor
- Dễ test, dễ mở rộng

🔹 Ví dụ

```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in Runnable");
    }
}

public class Main {
    public static void main(String[] args) {
        new Thread(new MyTask()).start();
    }
}
```

### 3️⃣ So sánh trực tiếp

| Tiêu chí    | Thread           | Runnable              |
| ----------- | ---------------- | --------------------- |
| Kế thừa     | `extends Thread` | `implements Runnable` |
| Đa kế thừa  | ❌ Không         | ✔ Có                  |
| Tách logic  | ❌               | ✔                     |
| Thread pool | ❌               | ✔                     |
| Test        | Khó              | Dễ                    |
| Khuyến nghị | ❌ Ít dùng       | ✅ Nên dùng           |

📌 Best Practice: dùng Runnable

## 🎯 Thread Pool

### 🧵 Thread Pool là gì?

Thread Pool trong Java là một tập hợp (pool) các thread được tạo sẵn và tái sử dụng để thực thi nhiều task khác nhau, thay vì tạo mới thread mỗi lần có công việc.

> 💡 Ý tưởng cốt lõi:
> Tạo thread một lần → dùng lại nhiều lần → quản lý tập trung

### 🧠 Thread Pool hoạt động thế nào?

1. Thread pool tạo sẵn N thread
2. Task được đưa vào queue
3. Thread rảnh sẽ lấy task ra xử lý
4. Xong task → thread quay lại pool

### 📊 So sánh nhanh các loại Thread Pool

| Thread Pool                 | Đặc điểm                  |
| --------------------------- | ------------------------- |
| `newFixedThreadPool(n)`     | Giới hạn thread           |
| `newCachedThreadPool()`     | Linh hoạt, không giới hạn |
| `newSingleThreadExecutor()` | 1 thread                  |
| `newScheduledThreadPool(n)` | Chạy theo lịch            |

### 🧪 Fixed Thread Pool (phổ biến nhất)

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println(
                    "Task " + taskId +
                    " chạy trên " +
                    Thread.currentThread().getName()
                );
            });
        }

        executor.shutdown();
    }
}
```
