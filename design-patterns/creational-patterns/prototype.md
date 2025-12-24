# 🎯 Mini Project: Document Management System - Prototype Pattern

> **Prototype**: Tạo ra các đối tượng mới bằng cách sao chép các đối tượng hiện có

Xây dựng một hệ thống quản lý tài liệu (Document Management System) trong đó mỗi loại tài liệu có thể được clone để tạo ra phiên bản mới nhanh chóng mà không cần tạo lại từ đầu.

## 📁 1. Cấu trúc Project

```java
/prototype
    Document.java
    WordDocument.java
    PdfDocument.java
    DocumentRegistry.java
    Main.java
```

## 🧩 2. Code chi tiết

### ✅ A. Interface Prototype — Document.java

```java
public interface Document extends Cloneable {
    Document clone();
    void printInfo();
}
```

### ✅ B. Concrete Prototype — WordDocument.java

```java
public class WordDocument implements Document {
    private String title;
    private String content;

    public WordDocument(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public Document clone() {
        return new WordDocument(title, content);
    }

    @Override
    public void printInfo() {
        System.out.println("[WORD] Title: " + title + " | Content: " + content);
    }
}
```

### ✅ C. Concrete Prototype — PdfDocument.java

```java
public class PdfDocument implements Document {
    private String title;
    private String text;

    public PdfDocument(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public Document clone() {
        return new PdfDocument(title, text);
    }

    @Override
    public void printInfo() {
        System.out.println("[PDF] Title: " + title + " | Text: " + text);
    }
}
```

### ✅ D. Prototype Registry — DocumentRegistry.java

```java
import java.util.HashMap;
import java.util.Map;

public class DocumentRegistry {
    private Map<String, Document> prototypes = new HashMap<>();

    public void addPrototype(String key, Document doc) {
        prototypes.put(key, doc);
    }

    public Document getClone(String key) {
        if (!prototypes.containsKey(key)) {
            System.out.println("Prototype not found: " + key);
            return null;
        }
        return prototypes.get(key).clone();
    }
}
```

### ✅ E. Demo — Main.java

```java
public class Main {
    public static void main(String[] args) {
        DocumentRegistry registry = new DocumentRegistry();

        registry.addPrototype("word_basic",
                new WordDocument("Basic Word Template", "Lorem ipsum..."));

        registry.addPrototype("pdf_basic",
                new PdfDocument("Basic PDF Template", "This is a PDF template"));

        // Clone
        Document doc1 = registry.getClone("word_basic");
        Document doc2 = registry.getClone("pdf_basic");

        doc1.printInfo();
        doc2.printInfo();

        // Create multiple copies
        Document doc3 = registry.getClone("word_basic");
        Document doc4 = registry.getClone("word_basic");

        System.out.println("Cloned multiple WORD documents:");
        doc3.printInfo();
        doc4.printInfo();
    }
}
```

## 🎉 Kết quả khi chạy

```bash
[WORD] Title: Basic Word Template | Content: Lorem ipsum...
[PDF] Title: Basic PDF Template | Text: This is a PDF template
Cloned multiple WORD documents:
[WORD] Title: Basic Word Template | Content: Lorem ipsum...
[WORD] Title: Basic Word Template | Content: Lorem ipsum...
```

## 📌 Điểm hay của Prototype Pattern trong project này

- Tạo object nhanh bằng cách clone thay vì khởi tạo lại.
- Dễ quản lý các template / mẫu tài liệu.
- Dùng Registry để quản lý nhiều prototype → mở rộng dễ dàng.
