# 🏠 Mini Project: Home Theater System - Facade Pattern

> Facade: Cung cấp một giao diện đơn giản để tương tác với một hệ thống phức tạp

🎯 Bài toán
Hệ thống xem phim tại nhà gồm nhiều subsystem:

- DVD Player
- Projector
- Sound System
- Lights

❌ Client phải gọi từng subsystem → phức tạp
✔ Dùng Facade Pattern để đơn giản hoá việc sử dụng

## 📁 1. Cấu trúc project

```java
facade-demo/
│
├── App.java
│
├── facade/
│     └── HomeTheaterFacade.java
│
└── subsystem/
      ├── DVDPlayer.java
      ├── Projector.java
      ├── SoundSystem.java
      └── Lights.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Subsystem classes

**DVDPlayer.java**

```java
package subsystem;

public class DVDPlayer {
    public void on() {
        System.out.println("📀 DVD Player ON");
    }

    public void play() {
        System.out.println("📀 DVD playing");
    }

    public void off() {
        System.out.println("📀 DVD Player OFF");
    }
}
```

**Projector.java**

```java
package subsystem;

public class Projector {
    public void on() {
        System.out.println("📽 Projector ON");
    }

    public void off() {
        System.out.println("📽 Projector OFF");
    }
}
```

**SoundSystem.java**

```java
package subsystem;

public class SoundSystem {
    public void on() {
        System.out.println("🔊 Sound System ON");
    }

    public void setVolume(int level) {
        System.out.println("🔊 Volume set to " + level);
    }

    public void off() {
        System.out.println("🔊 Sound System OFF");
    }
}
```

**Lights.java**

```java
package subsystem;

public class Lights {
    public void dim() {
        System.out.println("💡 Lights dimmed");
    }

    public void on() {
        System.out.println("💡 Lights ON");
    }
}
```

### ⭐ B) Facade — HomeTheaterFacade.java

```java
package facade;

import subsystem.*;

public class HomeTheaterFacade {

    private DVDPlayer dvd;
    private Projector projector;
    private SoundSystem sound;
    private Lights lights;

    public HomeTheaterFacade(
            DVDPlayer dvd,
            Projector projector,
            SoundSystem sound,
            Lights lights) {

        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
        this.lights = lights;
    }

    public void watchMovie() {
        System.out.println("\n🎬 Starting movie...");
        lights.dim();
        projector.on();
        sound.on();
        sound.setVolume(10);
        dvd.on();
        dvd.play();
    }

    public void endMovie() {
        System.out.println("\n🛑 Stopping movie...");
        dvd.off();
        sound.off();
        projector.off();
        lights.on();
    }
}
```

### ⭐ C) Client — App.java

```java
import facade.HomeTheaterFacade;
import subsystem.*;

public class App {
    public static void main(String[] args) {

        HomeTheaterFacade homeTheater =
            new HomeTheaterFacade(
                new DVDPlayer(),
                new Projector(),
                new SoundSystem(),
                new Lights()
            );

        homeTheater.watchMovie();
        homeTheater.endMovie();
    }
}
```

## 🧪 Output

```bash
🎬 Starting movie...
💡 Lights dimmed
📽 Projector ON
🔊 Sound System ON
🔊 Volume set to 10
📀 DVD Player ON
📀 DVD playing

🛑 Stopping movie...
📀 DVD Player OFF
🔊 Sound System OFF
📽 Projector OFF
💡 Lights ON
```
