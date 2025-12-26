# 🧵 Ví dụ: Multithread sử dụng Thread

## 🎯 Bài toán

Tạo 2 luồng:

- Luồng 1 in số 1 → 5
- Luồng 2 in chữ A → E

Hai luồng chạy song song

## 📌 Code hoàn chỉnh

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

## 🧪 Kết quả chạy (ví dụ)

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

## 🧠 Giải thích quan trọng

🔹 Vì sao dùng start() chứ không gọi run()?

| Gọi       | Ý nghĩa                                        |
| --------- | ---------------------------------------------- |
| `start()` | Tạo **luồng mới**, gọi `run()`                 |
| `run()`   | Chạy như **hàm bình thường**, không tạo thread |
