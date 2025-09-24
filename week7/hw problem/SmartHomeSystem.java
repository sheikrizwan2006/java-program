// File: SmartHomeSystem.java
class SmartDevice {
    protected String name;
    public SmartDevice(String name) { this.name = name; }
    public void connect() { System.out.println(name + " connected."); }
}

class SmartTV extends SmartDevice {
    public SmartTV(String name) { super(name); }
    public void playChannel() { System.out.println(name + " playing TV channel."); }
}

class SmartThermostat extends SmartDevice {
    public SmartThermostat(String name) { super(name); }
    public void setTemp(int t) { System.out.println(name + " temperature set to " + t + "°C."); }
}

class SmartSecurity extends SmartDevice {
    public SmartSecurity(String name) { super(name); }
    public void activateAlarm() { System.out.println(name + " alarm activated!"); }
}

class SmartKitchen extends SmartDevice {
    public SmartKitchen(String name) { super(name); }
    public void cook() { System.out.println(name + " is cooking recipe."); }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartTV("Living Room TV"),
            new SmartThermostat("Bedroom Thermostat"),
            new SmartSecurity("Main Door Security"),
            new SmartKitchen("Smart Oven")
        };

        for (SmartDevice d : devices) {
            d.connect();

            if (d instanceof SmartTV tv) tv.playChannel();
            else if (d instanceof SmartThermostat t) t.setTemp(22);
            else if (d instanceof SmartSecurity s) s.activateAlarm();
            else if (d instanceof SmartKitchen k) k.cook();

            System.out.println("----------------------");
        }
    }
}
