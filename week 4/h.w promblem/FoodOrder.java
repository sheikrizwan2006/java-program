public class FoodOrder {
    private String customerName;
    private String foodItem;
    private int quantity;
    private double price;

    private static final double FIXED_RATE = 150.0;  // Price per item

    // 1. Default constructor → assigns "Unknown" order
    public FoodOrder() {
        this("Unknown Customer", "Unknown Food", 0, 0.0);
    }

    // 2. Constructor with food item → quantity=1, price = fixed rate
    public FoodOrder(String foodItem) {
        this("Unknown Customer", foodItem, 1, FIXED_RATE);
    }

    // 3. Constructor with food item and quantity → price = quantity × fixedRate
    public FoodOrder(String foodItem, int quantity) {
        this("Unknown Customer", foodItem, quantity, quantity * FIXED_RATE);
    }

    // Full constructor (optional, to allow customer name)
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    // Method to print bill
    public void printBill() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price per item: ₹%.2f%n", FIXED_RATE);
        System.out.printf("Total Price: ₹%.2f%n", price);
        System.out.println("------------------------");
    }

    // Main method to test
    public static void main(String[] args) {
        FoodOrder order1 = new FoodOrder();
        FoodOrder order2 = new FoodOrder("Burger");
        FoodOrder order3 = new FoodOrder("Pizza", 3);
        FoodOrder order4 = new FoodOrder("Alice", "Pasta", 2, 2 * FIXED_RATE);

        order1.printBill();
        order2.printBill();
        order3.printBill();
        order4.printBill();
    }
}