// Parent class
class SmartDevice {
    String deviceName;

    SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    public void status() {
        System.out.println("Device: " + deviceName + " is connected to Smart Campus Network.");
    }
}

// Smart Classroom
class SmartClassroom extends SmartDevice {
    SmartClassroom(String name) {
        super(name);
    }

    public void controlLighting(boolean on) {
        System.out.println(deviceName + " - Classroom lights turned " + (on ? "ON" : "OFF"));
    }

    public void controlAC(int temp) {
        System.out.println(deviceName + " - AC set to " + temp + "°C");
    }

    public void controlProjector(boolean on) {
        System.out.println(deviceName + " - Projector turned " + (on ? "ON" : "OFF"));
    }
}

// Smart Lab
class SmartLab extends SmartDevice {
    SmartLab(String name) {
        super(name);
    }

    public void manageEquipment(String equipment, boolean on) {
        System.out.println(deviceName + " - Equipment " + equipment + " turned " + (on ? "ON" : "OFF"));
    }

    public void activateSafetySystem() {
        System.out.println(deviceName + " - Safety system activated!");
    }
}

// Smart Library
class SmartLibrary extends SmartDevice {
    SmartLibrary(String name) {
        super(name);
    }

    public void trackOccupancy(int people) {
        System.out.println(deviceName + " - Current occupancy: " + people + " people.");
    }

    public void checkBookAvailability(String book) {
        System.out.println(deviceName + " - Checking availability of \"" + book + "\"... Available ✅");
    }
}

// Main system
public class SmartCampusSystem {
    public static void main(String[] args) {
        // Upcasting: store all devices as SmartDevice
        SmartDevice[] devices = {
            new SmartClassroom("Classroom A101"),
            new SmartLab("Physics Lab"),
            new SmartLibrary("Central Library"),
            new SmartClassroom("Classroom B202")
        };

        // Safe downcasting with instanceof
        for (SmartDevice d : devices) {
            d.status(); // common method

            if (d instanceof SmartClassroom) {
                SmartClassroom sc = (SmartClassroom) d;
                sc.controlLighting(true);
                sc.controlAC(22);
                sc.controlProjector(true);

            } else if (d instanceof SmartLab) {
                SmartLab lab = (SmartLab) d;
                lab.manageEquipment("Microscope", true);
                lab.activateSafetySystem();

            } else if (d instanceof SmartLibrary) {
                SmartLibrary lib = (SmartLibrary) d;
                lib.trackOccupancy(120);
                lib.checkBookAvailability("Data Structures");
            }

            System.out.println("----------------------------");
        }
    }
}
