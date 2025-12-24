# 🎯 Mini Project: Sensor Adapter - Adapter Pattern

> **Adapter**: Cho phép các lớp có giao diện không tương thích hoạt động cùng nhau bằng cách chuyển đổi giao diện của một lớp thành giao diện mà lớp khác mong đợi

Chủ đề: Hệ thống hiện tại chỉ đọc dữ liệu từ TemperatureSensor (°C).
Bạn cần tích hợp sensor bên thứ 3 trả về °F và Kelvin (không sửa code của họ).
→ Dùng Adapter Pattern để chuẩn hoá về °C.

## 📁 1. Cấu trúc project

```java
sensor-adapter/
│
├── App.java
│
├── sensor/
│     └── TemperatureSensor.java
│
├── thirdparty/
│     ├── FahrenheitSensor.java
│     └── KelvinSensor.java
│
└── adapter/
      ├── FahrenheitAdapter.java
      └── KelvinAdapter.java
```

## 🧠 2. Code chi tiết

### ⭐ A) Target Interface — TemperatureSensor.java

```java
package sensor;

public interface TemperatureSensor {
    double getTemperatureCelsius();
}
```

### ⭐ B) Third-party sensors (KHÔNG sửa)

**FahrenheitSensor.java**

```java
package thirdparty;

public class FahrenheitSensor {
    public double getTempF() {
        return 98.6;
    }
}
```

**KelvinSensor.java**

```java
package thirdparty;

public class KelvinSensor {
    public double readKelvin() {
        return 310.15;
    }
}
```

### ⭐ C) Adapter implementations

**FahrenheitAdapter.java**

```java
package adapter;

import sensor.TemperatureSensor;
import thirdparty.FahrenheitSensor;

public class FahrenheitAdapter implements TemperatureSensor {

    private FahrenheitSensor sensor;

    public FahrenheitAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperatureCelsius() {
        return (sensor.getTempF() - 32) * 5 / 9;
    }
}
```

**KelvinAdapter.java**

```java
package adapter;

import sensor.TemperatureSensor;
import thirdparty.KelvinSensor;

public class KelvinAdapter implements TemperatureSensor {

    private KelvinSensor sensor;

    public KelvinAdapter(KelvinSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperatureCelsius() {
        return sensor.readKelvin() - 273.15;
    }
}
```

### ⭐ D) Client — App.java

```java
import adapter.FahrenheitAdapter;
import adapter.KelvinAdapter;
import sensor.TemperatureSensor;
import thirdparty.FahrenheitSensor;
import thirdparty.KelvinSensor;

public class App {
    public static void main(String[] args) {

        TemperatureSensor fSensor =
                new FahrenheitAdapter(new FahrenheitSensor());

        TemperatureSensor kSensor =
                new KelvinAdapter(new KelvinSensor());

        System.out.println("🌡 Fahrenheit → Celsius: "
                + fSensor.getTemperatureCelsius());

        System.out.println("🌡 Kelvin → Celsius: "
                + kSensor.getTemperatureCelsius());
    }
}
```

## 🧪 3. Output khi chạy

```bash
🌡 Fahrenheit → Celsius: 37.0
🌡 Kelvin → Celsius: 37.0
```

## 🎯 4. Khi nào dùng Adapter Pattern?

- Chuẩn hoá dữ liệu
- Tích hợp thư viện ngoài
- Legacy system
- Không sửa code cũ (OCP)
