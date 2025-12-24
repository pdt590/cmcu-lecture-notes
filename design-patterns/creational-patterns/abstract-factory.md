# 🎯 Mini Project: UI Theme – Abstract Factory Pattern

> **Abstract Factory**: Cung cấp giao diện để tạo ra các nhóm đối tượng liên quan hoặc phụ thuộc mà không cần định nghĩa rõ ràng một lớp cụ thể

Ứng dụng tạo các thành phần UI (Button + Checkbox) theo từng theme:

- LightThemeFactory → LightButton + LightCheckbox
- DarkThemeFactory → DarkButton + DarkCheckbox

Client không cần biết class cụ thể, chỉ cần chọn Factory.

## 📁 1. Cấu trúc Project

```java
abstract-factory-demo/
│
├── App.java
│
├── ui/
│    ├── button/
│    │      ├── Button.java
│    │      ├── LightButton.java
│    │      └── DarkButton.java
│    │
│    └── checkbox/
│           ├── Checkbox.java
│           ├── LightCheckbox.java
│           └── DarkCheckbox.java
│
└── factory/
        ├── UIFactory.java
        ├── LightThemeFactory.java
        └── DarkThemeFactory.java
```

## 🧩 2. Code chi tiết

**Button.java**

```java
package ui.button;

public interface Button {
    void render();
}
```

**Checkbox.java**

```java
package ui.checkbox;

public interface Checkbox {
    void render();
}
```

### ⭐ B) Concrete Components

**LightButton.java**

```java
package ui.button;

public class LightButton implements Button {

    @Override
    public void render() {
        System.out.println("🔳 Light Button rendered");
    }
}
```

**DarkButton.java**

```java
package ui.button;

public class DarkButton implements Button {

    @Override
    public void render() {
        System.out.println("⬛ Dark Button rendered");
    }
}
```

**LightCheckbox.java**

```java
package ui.checkbox;

public class LightCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("☑️ Light Checkbox rendered");
    }
}
```

**DarkCheckbox.java**

```java
package ui.checkbox;

public class DarkCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("☒ Dark Checkbox rendered");
    }
}
```

### ⭐ C) Abstract Factory — UIFactory.java

```java
package factory;

import ui.button.Button;
import ui.checkbox.Checkbox;

public interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

### ⭐ D) Concrete Factories

**LightThemeFactory.java**

```java
package factory;

import ui.button.Button;
import ui.button.LightButton;
import ui.checkbox.Checkbox;
import ui.checkbox.LightCheckbox;

public class LightThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}
```

**DarkThemeFactory.java**

```java
package factory;

import ui.button.Button;
import ui.button.DarkButton;
import ui.checkbox.Checkbox;
import ui.checkbox.DarkCheckbox;

public class DarkThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}
```

### ⭐ E) Main Application — App.java

```java
import factory.DarkThemeFactory;
import factory.LightThemeFactory;
import factory.UIFactory;
import ui.button.Button;
import ui.checkbox.Checkbox;

public class App {
    public static void main(String[] args) {

        // Chọn theme Light
        UIFactory lightFactory = new LightThemeFactory();
        Button lightBtn = lightFactory.createButton();
        Checkbox lightCheckbox = lightFactory.createCheckbox();

        lightBtn.render();
        lightCheckbox.render();

        // Chọn theme Dark
        UIFactory darkFactory = new DarkThemeFactory();
        Button darkBtn = darkFactory.createButton();
        Checkbox darkCheckbox = darkFactory.createCheckbox();

        darkBtn.render();
        darkCheckbox.render();
    }
}
```

## 🧪 3. Output khi chạy

```bash
🔳 Light Button rendered
☑️ Light Checkbox rendered
⬛ Dark Button rendered
☒ Dark Checkbox rendered
```

## 🎉 4. Bạn học được gì?

- Abstract Factory cung cấp nhóm object theo “family”
- Dễ mở rộng các theme mới (BlueTheme, NeonTheme…)
- Client không biết class thật bên dưới
- Phù hợp cho UI toolkit, DB driver, Cross-platform system…
