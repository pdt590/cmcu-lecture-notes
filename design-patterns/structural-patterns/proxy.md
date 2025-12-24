# 🛡️ Mini Project: Secure Image Viewer - Proxy Pattern

> **Proxy**: Cung cấp một đối tượng thay thế cho một đối tượng khác để kiểm soát quyền truy cập vào đối tượng đó

🎯 Bài toán

- Hệ thống có Image (ảnh lớn, load tốn tài nguyên)
- Cần:
    - Lazy loading (chỉ load khi cần)
    - Phân quyền truy cập

→  Dùng Proxy Pattern

## 📁 1. Cấu trúc project

```java
proxy-demo/
│
├── App.java
│
├── image/
│     ├── Image.java
│     ├── RealImage.java
│     └── ProxyImage.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Subject — Image.java

```java
package image;

public interface Image {
    void display(String userRole);
}
```

### ⭐ B) Real Subject — RealImage.java

```java
package image;

public class RealImage implements Image {

    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("📥 Loading image: " + filename);
    }

    @Override
    public void display(String userRole) {
        System.out.println("🖼 Displaying image: " + filename);
    }
}
```

### ⭐ C) Proxy — ProxyImage.java

```java
package image;

public class ProxyImage implements Image {

    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display(String userRole) {

        if (!"ADMIN".equalsIgnoreCase(userRole)) {
            System.out.println("⛔ Access denied for role: " + userRole);
            return;
        }

        if (realImage == null) {
            realImage = new RealImage(filename);
        }

        realImage.display(userRole);
    }
}
```

### ⭐ D) Client — App.java

```java
import image.Image;
import image.ProxyImage;

public class App {
    public static void main(String[] args) {

        Image image = new ProxyImage("secret_photo.png");

        image.display("USER");   // bị chặn
        System.out.println();
        image.display("ADMIN");  // load & hiển thị
        System.out.println();
        image.display("ADMIN");  // không load lại
    }
}
```

## 🧪 3. Output

```java
⛔ Access denied for role: USER

📥 Loading image: secret_photo.png
🖼 Displaying image: secret_photo.png

🖼 Displaying image: secret_photo.png
```

## 🧠 4. Các loại Proxy thường gặp

| Loại             | Mục đích         |
| ---------------- | ---------------- |
| Virtual Proxy    | Lazy loading     |
| Protection Proxy | Phân quyền       |
| Remote Proxy     | Gọi object từ xa |
| Caching Proxy    | Cache dữ liệu    |
