# 🎯 Mini Project: Document Creator – Factory Method Pattern

> Factory Method: Cung cấp giao diện để tạo đối tượng, nhưng cho phép các lớp con quyết định lớp nào sẽ được khởi tạo

Chủ đề: Document Creator
→ Mỗi loại tài liệu (Word, PDF, Excel) có factory method riêng để tạo ra đối tượng Document tương ứng

## 📁 1 Cấu trúc Project

```java
factory-method-demo/
│
├── App.java
│
├── document/
│     ├── Document.java
│     ├── WordDocument.java
│     ├── PdfDocument.java
│     └── ExcelDocument.java
│
└── creator/
      ├── DocumentCreator.java
      ├── WordCreator.java
      ├── PdfCreator.java
      └── ExcelCreator.java
```

 ## 🧩 2. Code chi tiết

### ⭐ A) Document interface — Document.java

```java
package document;

public interface Document {
    void open();
}
```

### ⭐ B) Concrete Documents

**WordDocument.java**

```java
package document;

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("📄 Opening Word Document...");
    }
}
```

**PdfDocument.java**

```java
package document;

public class PdfDocument implements Document {

    @Override
    public void open() {
        System.out.println("📕 Opening PDF Document...");
    }
}
```

**ExcelDocument.java**

```java
package document;

public class ExcelDocument implements Document {

    @Override
    public void open() {
        System.out.println("📊 Opening Excel Document...");
    }
}
```

### ⭐ C) Creator (Abstract Factory Method) — DocumentCreator.java

```java
package creator;

import document.Document;

public abstract class DocumentCreator {
    // Factory Method
    public abstract Document createDocument();

    // Common operation
    public void openDocument() {
        Document doc = createDocument();
        doc.open();
    }
}
```

### ⭐ D) Concrete Creators

**WordCreator.java**

```java
package creator;

import document.Document;
import document.WordDocument;

public class WordCreator extends DocumentCreator {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
```

**PdfCreator.java**

```java
package creator;

import document.Document;
import document.PdfDocument;

public class PdfCreator extends DocumentCreator {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
```

**ExcelCreator.java**

```java
package creator;

import document.Document;
import document.ExcelDocument;

public class ExcelCreator extends DocumentCreator {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
```

### ⭐ E) Main Application — App.java

```java
import creator.DocumentCreator;
import creator.WordCreator;
import creator.PdfCreator;
import creator.ExcelCreator;

public class App {
    public static void main(String[] args) {

        DocumentCreator word = new WordCreator();
        word.openDocument();

        DocumentCreator pdf = new PdfCreator();
        pdf.openDocument();

        DocumentCreator excel = new ExcelCreator();
        excel.openDocument();
    }
}
```

## 🧪 3. Output khi chạy

```bash
📄 Opening Word Document...
📕 Opening PDF Document...
📊 Opening Excel Document...
```

## 🎉 4. Bạn học được gì?

- Factory Method Pattern giúp tách logic khởi tạo object
- Người dùng chỉ gọi creator.openDocument()
- Mỗi subclass tự tạo đúng loại document
- Dễ mở rộng → thêm PowerPointCreator, ImageCreator…
