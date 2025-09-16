import java.time.LocalDateTime;
import java.util.*;

// a. Immutable Product class
public final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    // Private constructor with full validation
    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Product ID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category required");
        if (manufacturer == null || manufacturer.isBlank()) throw new IllegalArgumentException("Manufacturer required");
        if (basePrice < 0) throw new IllegalArgumentException("Base price cannot be negative");
        if (weight < 0) throw new IllegalArgumentException("Weight cannot be negative");

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;

        this.features = features == null ? new String[0] : Arrays.copyOf(features, features.length);
        this.specifications = specifications == null ? new HashMap<>() : new HashMap<>(specifications);
    }

    // Factory methods
    public static Product createElectronics(String id, String name, String manufacturer,
                                            double basePrice, double weight,
                                            String[] features, Map<String, String> specs) {
        return new Product(id, name, "Electronics", manufacturer, basePrice, weight, features, specs);
    }

    public static Product createClothing(String id, String name, String manufacturer,
                                         double basePrice, double weight,
                                         String[] features, Map<String, String> specs) {
        return new Product(id, name, "Clothing", manufacturer, basePrice, weight, features, specs);
    }

    public static Product createBooks(String id, String name, String manufacturer,
                                      double basePrice, double weight,
                                      String[] features, Map<String, String> specs) {
        return new Product(id, name, "Books", manufacturer, basePrice, weight, features, specs);
    }

    // Getters with defensive copying for arrays and map
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return Arrays.copyOf(features, features.length); }
    public Map<String, String> getSpecifications() { return Collections.unmodifiableMap(specifications); }

    // Business consistent tax calculation - final, cannot be overridden
    public final double calculateTax(String region) {
        // Simplified tax rules based on region
        double taxRate = 0.1; // Default 10%
        if ("NY".equalsIgnoreCase(region)) taxRate = 0.08875;
        else if ("CA".equalsIgnoreCase(region)) taxRate = 0.0925;
        else if ("TX".equalsIgnoreCase(region)) taxRate = 0.0625;
        return basePrice * taxRate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", basePrice=" + basePrice +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return productId.equals(p.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}

// b. Customer class with privacy tiers
class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate;

    private int creditRating;  // package-private getter only

    // Constructor for full info customer
    public Customer(String customerId, String email, String name, String phoneNumber,
                    String preferredLanguage, String accountCreationDate, int creditRating) {
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email required");
        if (accountCreationDate == null || accountCreationDate.isBlank()) throw new IllegalArgumentException("Account creation date required");

        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = accountCreationDate;
        this.creditRating = creditRating;
    }

    // Getters and setters for modifiable personal data
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public String getAccountCreationDate() { return accountCreationDate; }

    // Package-private credit rating getter
    int getCreditRating() {
        return creditRating;
    }

    // Public safe profile info
    public String getPublicProfile() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", preferredLanguage='" + preferredLanguage + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", email='[PROTECTED]'" +  // do not expose email in toString
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", preferredLanguage='" + preferredLanguage + '\'' +
                ", accountCreationDate='" + accountCreationDate + '\'' +
                '}';
    }
}

// c. ShoppingCart class with access control
class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<CartItem> items = new ArrayList<>();
    private double totalAmount = 0.0;
    private int itemCount = 0;

    // Helper class to track products with quantity
    private static class CartItem {
        private final Product product;
        private int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        Product getProduct() { return product; }
        int getQuantity() { return quantity; }
        void setQuantity(int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
            this.quantity = quantity;
        }

        double getItemTotal() {
            return product.getBasePrice() * quantity;
        }
    }

    public ShoppingCart(String cartId, String customerId) {
        if (cartId == null || cartId.isBlank()) throw new IllegalArgumentException("Cart ID required");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID required");
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public String getCartId() { return cartId; }
    public String getCustomerId() { return customerId; }

    public synchronized boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) return false;
        if (quantity <= 0) return false;

        Product p = (Product) product;
        for (CartItem ci : items) {
            if (ci.getProduct().equals(p)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                recalcTotals();
                return true;
            }
        }
        items.add(new CartItem(p, quantity));
        recalcTotals();
        return true;
    }

    private void recalcTotals() {
        double sum = 0;
        int count = 0;
        for (CartItem ci : items) {
            sum += ci.getItemTotal();
            count += ci.getQuantity();
        }
        totalAmount = sum - calculateDiscount();
        itemCount = count;
    }

    // Private discount calculation logic
    private double calculateDiscount() {
        // Example: 5% discount if total > 500
        if (totalAmount > 500) return totalAmount * 0.05;
        return 0;
    }

    // Package-private cart summary for checkout
    String getCartSummary() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", itemCount=" + itemCount +
                ", items=" + items.size() +
                '}';
    }

    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }

    @Override
    public String toString() {
        return getCartSummary();
    }
}

// d. Constructor chaining for different order types (via Customer subclasses)
class GuestCustomer extends Customer {
    public GuestCustomer(String tempId, String email) {
        super(tempId, email, "Guest", null, "en", LocalDateTime.now().toLocalDate().toString(), 0);
    }
}

class RegisteredCustomer extends Customer {
    public RegisteredCustomer(String customerId, String email, String name, String phoneNumber,
                              String preferredLanguage, String accountCreationDate, int creditRating) {
        super(customerId, email, name, phoneNumber, preferredLanguage, accountCreationDate, creditRating);
    }
}

class PremiumMember extends RegisteredCustomer {
    private final double specialDiscountRate;

    public PremiumMember(String customerId, String email, String name, String phoneNumber,
                         String preferredLanguage, String accountCreationDate, int creditRating,
                         double specialDiscountRate) {
        super(customerId, email, name, phoneNumber, preferredLanguage, accountCreationDate, creditRating);
        if (specialDiscountRate < 0 || specialDiscountRate > 1)
            throw new IllegalArgumentException("Invalid discount rate");
        this.specialDiscountRate = specialDiscountRate;
    }

    public double getSpecialDiscountRate() { return specialDiscountRate; }
}

class CorporateAccount extends RegisteredCustomer {
    private final String companyName;
    private final String companyTaxId;

    public CorporateAccount(String customerId, String email, String name, String phoneNumber,
                            String preferredLanguage, String accountCreationDate, int creditRating,
                            String companyName, String companyTaxId) {
        super(customerId, email, name, phoneNumber, preferredLanguage, accountCreationDate, creditRating);
        if (companyName == null || companyName.isBlank()) throw new IllegalArgumentException("Company name required");
        if (companyTaxId == null || companyTaxId.isBlank()) throw new IllegalArgumentException("Company tax ID required");
        this.companyName = companyName;
        this.companyTaxId = companyTaxId;
    }

    public String getCompanyName() { return companyName; }
    public String getCompanyTaxId() { return companyTaxId; }
}

// e. Separate order processing classes

final class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final String customerId;
    private final List<Product> products;

    public Order(String orderId, String customerId, List<Product> products) {
        if (orderId == null || orderId.isBlank()) throw new IllegalArgumentException("Order ID required");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID required");
        if (products == null || products.isEmpty()) throw new IllegalArgumentException("Order must contain products");

        this.orderId = orderId;
        this.customerId = customerId;
        this.products = List.copyOf(products);
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getCustomerId() { return customerId; }
    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderTime=" + orderTime +
                ", customerId='" + customerId + '\'' +
                ", products=" + products.size() +
                '}';
    }
}

final class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        if (processorId == null || processorId.isBlank()) throw new IllegalArgumentException("Processor ID required");
        if (securityKey == null || securityKey.isBlank()) throw new IllegalArgumentException("Security key required");
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public boolean processPayment(Order order, double amount) {
        // Simplified payment logic
        System.out.println("Processing payment of $" + amount + " for order " + order.getOrderId());
        // In real system, validate securityKey, communicate with payment gateway, etc.
        return true;  // Assume success
    }
}

final class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> shippingRates) {
        if (shippingRates == null) this.shippingRates = new HashMap<>();
        else this.shippingRates = new HashMap<>(shippingRates);
    }

    public double calculateShippingCost(String region, double weight) {
        double rate = shippingRates.getOrDefault(region.toUpperCase(), 10.0);
        return rate * weight;
    }
}

// f. ECommerceSystem final class

public final class ECommerceSystem {
    private static final Map<String, Product> productCatalog = new HashMap<>();

    private ECommerceSystem() {
        // Prevent instantiation
    }

    public static void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        productCatalog.put(product.getProductId(), product);
    }

    public static Product getProduct(String productId) {
        return productCatalog.get(productId);
    }

    public static boolean processOrder(Object orderObj, Object customerObj) {
        if (!(orderObj instanceof Order)) {
            System.err.println("Invalid order object");
            return false;
        }
        if (!(customerObj instanceof Customer)) {
            System.err.println("Invalid customer object");
            return false;
        }

        Order order = (Order) orderObj;
        Customer customer = (Customer) customerObj;

        // Business rules check (e.g., credit rating > 50)
        if (customer.getCreditRating() < 50) {
            System.err.println("Customer credit rating too low");
            return false;
        }

        // Process payment
        PaymentProcessor paymentProcessor = new PaymentProcessor("DefaultProcessor", "SecretKey123");
        double totalAmount = 0;
        for (Product p : order.getProducts()) {
            totalAmount += p.getBasePrice() + p.calculateTax("NY");  // example region tax calc
        }

        if (!paymentProcessor.processPayment(order, totalAmount)) {
            System.err.println("Payment failed");
            return false;
        }

        // Calculate shipping
        ShippingCalculator shippingCalculator = new ShippingCalculator(Map.of(
            "NY", 5.0,
            "CA", 7.0,
            "TX", 6.0
        ));
        double totalWeight = order.getProducts().stream().mapToDouble(Product::getWeight).sum();
        double shippingCost = shippingCalculator.calculateShippingCost("NY", totalWeight);

        System.out.println("Order processed successfully.");
        System.out.println("Total amount: $" + totalAmount);
        System.out.println("Shipping cost: $" + shippingCost);

        // Additional inventory updates and order fulfillment logic can be added here
        return true;
    }

    public static void main(String[] args) {
        // Sample usage

        Product laptop = Product.createElectronics("E1001", "Laptop Pro", "TechCorp", 1200.00, 5.5,
                new String[]{"16GB RAM", "512GB SSD"},
                Map.of("CPU", "Intel i7", "GPU", "NVIDIA RTX 3060"));

        Product tshirt = Product.createClothing("C2001", "Cool T-Shirt", "FashionInc", 20.00, 0.3,
                new String[]{"Cotton", "Unisex"},
                Map.of("Color", "Red", "Size", "M"));

        ECommerceSystem.addProduct(laptop);
        ECommerceSystem.addProduct(tshirt);

        RegisteredCustomer customer = new RegisteredCustomer("CUST123", "user@example.com",
                "John Doe", "555-1234", "en", "2022-01-15", 75);

        List<Product> orderedProducts = List.of(laptop, tshirt);
        Order order = new Order("ORD789", customer.getCustomerId(), orderedProducts);

        boolean success = ECommerceSystem.processOrder(order, customer);
        System.out.println("Order success: " + success);
    }
}