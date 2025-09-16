class Tool {
    private String serialNumber;
    protected String material;
    public String brand;

    public Tool(String serialNumber, String material, String brand) {
        this.serialNumber = serialNumber;
        this.material = material;
        this.brand = brand;
    }

    // Getter for private field
    public String getSerialNumber() {
        return serialNumber;
    }
}

class Hammer extends Tool {
    public Hammer(String serialNumber, String material, String brand) {
        super(serialNumber, material, brand);
    }

    public void displayFields() {
        // System.out.println("Serial Number: " + serialNumber); // Not accessible - private in Tool
        System.out.println("Serial Number (via getter): " + getSerialNumber()); // Accessible via getter
        System.out.println("Material: " + material); // Accessible - protected
        System.out.println("Brand: " + brand); // Accessible - public
    }
}

public class HwProblem2 {
    public static void main(String[] args) {
        Hammer hammer = new Hammer("SN12345", "Steel", "BestTools");
        hammer.displayFields();
    }
}
