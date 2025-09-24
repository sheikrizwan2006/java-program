public class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    public static int totalProducts = 0;
    public static String[] categories = {"Electronics", "Books", "Clothing", "Home"};

    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int qty) { this.stockQuantity = qty; }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        return null;
    }

    public static Product[] getProductsByCategory(Product[] products, String category) {
        int count = 0;
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                count++;
            }
        }
        Product[] result = new Product[count];
        int index = 0;
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                result[index++] = p;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - $%.2f - Category: %s - Stock: %d",
                productId, productName, price, category, stockQuantity);
    }
}