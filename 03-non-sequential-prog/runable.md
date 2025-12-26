# 🧵 Ví dụ: Multithread sử dụng Runnable

## 🎯 Bài toán

Tạo 2 luồng:

- Luồng 1 in số 1 → 5
- Luồng 2 in chữ A → E

Hai luồng chạy song song

## 📌 Code hoàn chỉnh

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

## 🧪 Kết quả chạy (ví dụ)

```bash
Main thread finished
NumberRunnable: 1
LetterRunnable: A
NumberRunnable: 2
LetterRunnable: B
...
```

👉 Thứ tự không cố định, do scheduler quyết định.

# 🧵 Khi nào dùng Thread và khi nào dùng Runnable?

## 1️⃣ Dùng Thread khi nào?

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

## 2️⃣ Dùng Runnable khi nào?

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

## 3️⃣ So sánh trực tiếp

| Tiêu chí    | Thread           | Runnable              |
| ----------- | ---------------- | --------------------- |
| Kế thừa     | `extends Thread` | `implements Runnable` |
| Đa kế thừa  | ❌ Không         | ✔ Có                  |
| Tách logic  | ❌               | ✔                     |
| Thread pool | ❌               | ✔                     |
| Test        | Khó              | Dễ                    |
| Khuyến nghị | ❌ Ít dùng       | ✅ Nên dùng           |

📌 Best Practice: dùng Runnable
