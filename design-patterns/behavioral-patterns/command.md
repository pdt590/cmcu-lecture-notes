# 🎯 Mini Project: Smart Home Remote - Command Pattern

> **Command**: Biến các request (yêu cầu) thành các đối tượng độc lập, cho phép bạn thực thi, cho vào hàng đợi hoặc ghi log các request đó

Chủ đề: Smart Home Remote Control
→ Người dùng bấm nút điều khiển đèn, quạt,… mỗi hành động là một Command.

## 🧱 1. Cấu trúc Project

```java
command-demo/
│
├── App.java
│
├── command/
│     ├── Command.java
│     ├── LightOnCommand.java
│     ├── LightOffCommand.java
│     ├── FanOnCommand.java
│     ├── FanOffCommand.java
│
├── device/
│     ├── Light.java
│     └── Fan.java
│
└── remote/
      └── RemoteControl.java
```

## 🧩 2. Code chi tiết

### ⭐ A) Command interface — Command.java

```java
package command;

public interface Command {
    void execute();
}
```

### ⭐ B) Concrete Commands

**LightOnCommand.java**

```java
package command;

import device.Light;

public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
```

**LightOffCommand.java**

```java
package command;

import device.Light;

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
```

**FanOnCommand.java**

```java
package command;

import device.Fan;

public class FanOnCommand implements Command {

    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.start();
    }
}
```

**FanOffCommand.java**

```java
package command;

import device.Fan;

public class FanOffCommand implements Command {

    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.stop();
    }
}
```

### ⭐ C) Devices (Receivers)

**Light.java**

```java
package device;

public class Light {

    public void turnOn() {
        System.out.println("💡 Đèn bật");
    }

    public void turnOff() {
        System.out.println("💡 Đèn tắt");
    }
}
```

**Fan.java**

```java
package device;

public class Fan {

    public void start() {
        System.out.println("🌀 Quạt chạy");
    }

    public void stop() {
        System.out.println("🌀 Quạt dừng");
    }
}
```

### ⭐ D) Invoker — RemoteControl.java

```java
package remote;

import command.Command;

public class RemoteControl {

    private Command command;

    // Gán hành động tương ứng với nút bấm
    public void setCommand(Command command) {
        this.command = command;
    }

    // Nhấn nút
    public void pressButton() {
        if (command != null) {
            command.execute();
        }
    }
}
```

### ⭐ E) Main Application — App.java

```java
import command.*;
import device.*;
import remote.RemoteControl;

public class App {
    public static void main(String[] args) {

        RemoteControl remote = new RemoteControl();

        Light light = new Light();
        Fan fan = new Fan();

        // Commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command fanOn = new FanOnCommand(fan);
        Command fanOff = new FanOffCommand(fan);

        System.out.println("== Điều khiển đèn ==");
        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println("\n== Điều khiển quạt ==");
        remote.setCommand(fanOn);
        remote.pressButton();

        remote.setCommand(fanOff);
        remote.pressButton();
    }
}
```

## 🧪 3. Kết quả chạy

```bash
== Điều khiển đèn ==
💡 Đèn bật
💡 Đèn tắt

== Điều khiển quạt ==
🌀 Quạt chạy
🌀 Quạt dừng
```

## 🎉 4. Bạn học được gì?

- Hiểu rõ Command Pattern
- Hành động được đóng gói thành object → dễ undo/redo
- Invoker (Remote) không biết chi tiết thiết bị
- Dễ mở rộng thêm thiết bị hoặc Command mới
- Áp dụng cho UI Button, Menu, Queue task,…
