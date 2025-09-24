// File: SmartHome.java
public class SmartDevice {
    protected String deviceName;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    public void connect() {
        System.out.println(deviceName + " connected to Smart Home system.");
    }
}

// Smart Light
class SmartLight extends SmartDevice {
    public SmartLight(String deviceName) {
        super(deviceName);
    }

    public void turnOn() {
        System.out.println(deviceName + " light turned ON 💡");
    }

    public void turnOff() {
        System.out.println(deviceName + " light turned OFF.");
    }
}

// Smart Thermostat
class SmartThermostat extends SmartDevice {
    public SmartThermostat(String deviceName) {
        super(deviceName);
    }

    public void setTemperature(int temp) {
        System.out.println(deviceName + " temperature set to " + temp + "°C 🌡️");
    }
}

// Smart Security Camera
class SmartCamera extends SmartDevice {
    public SmartCamera(String deviceName) {
        super(deviceName);
    }

    public void record() {
        System.out.println(deviceName + " is recording 🎥");
    }

    public void detectMotion() {
        System.out.println(deviceName + " motion detected! 🚨");
    }
}

// Main System
public class SmartHome {
    public static void main(String[] args) {
        // Create a mixed collection of devices (Upcasting)
        SmartDevice[] devices = {
            new SmartLight("Living Room Light"),
            new SmartThermostat("Bedroom Thermostat"),
            new SmartCamera("Front Door Camera")
        };

        // Process devices safely with instanceof
        for (SmartDevice d : devices) {
            d.connect(); // Common method

            if (d instanceof SmartLight) {
                SmartLight light = (SmartLight) d; // Safe downcast
                light.turnOn();
                light.turnOff();

            } else if (d instanceof SmartThermostat) {
                SmartThermostat thermo = (SmartThermostat) d;
                thermo.setTemperature(22);

            } else if (d instanceof SmartCamera) {
                SmartCamera cam = (SmartCamera) d;
                cam.record();
                cam.detectMotion();
            }

            System.out.println("----------------------");
        }
    }
}
