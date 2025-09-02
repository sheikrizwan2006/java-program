public class GameController {
    // Instance variables for controller configuration
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    // Default constructor - creates standard gaming setup
    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    // Parameterized constructor for custom configuration
    public GameController(String controllerBrand, String connectionType, boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        // Validate battery level (0-100)
        if (batteryLevel < 0) {
            this.batteryLevel = 0;
        } else if (batteryLevel > 100) {
            this.batteryLevel = 100;
        } else {
            this.batteryLevel = batteryLevel;
        }
        // Validate sensitivity (0.1 - 3.0)
        if (sensitivity < 0.1) {
            this.sensitivity = 0.1;
        } else if (sensitivity > 3.0) {
            this.sensitivity = 3.0;
        } else {
            this.sensitivity = sensitivity;
        }
    }

    // Two-parameter convenience constructor
    public GameController(String controllerBrand, String connectionType) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = true;  // default
        this.batteryLevel = 100;   // default
        this.sensitivity = 1.0;    // default
    }

    // Methods to test functionality
    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Controller Brand: " + controllerBrand);
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Vibration Enabled: " + hasVibration);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
        System.out.println();
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");

        // Create controller with default constructor
        GameController defaultController = new GameController();

        // Create controller with full parameterized constructor
        GameController customController = new GameController("ProGamer", "Bluetooth", false, 85, 2.5);

        // Create controller with convenience constructor
        GameController convenienceController = new GameController("SpeedyPad", "Wireless");

        // Test all methods on each controller

        System.out.println("--- Default Controller ---");
        defaultController.displayConfiguration();
        defaultController.calibrateController();
        defaultController.testVibration();

        System.out.println("--- Custom Controller ---");
        customController.displayConfiguration();
        customController.calibrateController();
        customController.testVibration();

        System.out.println("--- Convenience Controller ---");
        convenienceController.displayConfiguration();
        convenienceController.calibrateController();
        convenienceController.testVibration();

        // Compare different configurations (simple demonstration)
        System.out.println("--- Comparing Controllers ---");
        System.out.println("Default vs Custom: ");
        System.out.println("Default vibration: " + defaultController.hasVibration + ", Custom vibration: " + customController.hasVibration);
        System.out.println("Default sensitivity: " + defaultController.sensitivity + ", Custom sensitivity: " + customController.sensitivity);
    }
}
