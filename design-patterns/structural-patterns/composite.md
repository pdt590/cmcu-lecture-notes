# 🌳 Mini Project: File System - Composite Pattern

> **Composite**: Cho phép bạn tạo các cấu trúc phân cấp, trong đó các đối tượng có thể được tổ chức như cây. Nó giúp quản lý các đối tượng riêng lẻ và tổng hợp một cách nhất quán. Mục đích: Xử lý các đối tượng đơn lẻ và các tổ hợp đối tượng theo cùng một cách

🎯 Bài toán
Bạn cần biểu diễn cấu trúc cây gồm:

- File (đơn)
- Folder (chứa nhiều file/folder)

👉 Client thao tác thống nhất mà không cần phân biệt file hay folder
→ Dùng Composite Pattern

## 📁 1. Cấu trúc project

```java
composite-demo/
│
├── App.java
│
├── filesystem/
│     ├── FileSystemItem.java
│     ├── FileItem.java
│     └── Folder.java
```

## 🧠 2. Code chi tiết

## ⭐ A) Component — FileSystemItem.java

```java
package filesystem;

public interface FileSystemItem {
    void showDetails(String indent);
}
```

### ⭐ B) Leaf — FileItem.java

```java
package filesystem;

public class FileItem implements FileSystemItem {

    private String name;

    public FileItem(String name) {
        this.name = name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 File: " + name);
    }
}
```

### ⭐ C) Composite — Folder.java

```java
package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemItem {

    private String name;
    private List<FileSystemItem> items = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        items.add(item);
    }

    public void remove(FileSystemItem item) {
        items.remove(item);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 Folder: " + name);
        for (FileSystemItem item : items) {
            item.showDetails(indent + "   ");
        }
    }
}
```

### ⭐ D) Client — App.java

```java
import filesystem.FileItem;
import filesystem.Folder;
import filesystem.FileSystemItem;

public class App {
    public static void main(String[] args) {

        FileSystemItem file1 = new FileItem("readme.txt");
        FileSystemItem file2 = new FileItem("logo.png");

        Folder root = new Folder("root");
        Folder docs = new Folder("docs");

        docs.add(file1);
        root.add(docs);
        root.add(file2);

        root.showDetails("");
    }
}
```

## 🧪 3. Output

```bash
📁 Folder: root
   📁 Folder: docs
      📄 File: readme.txt
   📄 File: logo.png
```

## 🎯 Khi nào dùng Composite Pattern?

- Cấu trúc tree / hierarchy
- Muốn xử lý object đơn và object tập hợp giống nhau
- Menu, UI component, File system, XML/HTML DOM
