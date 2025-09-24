class FoodDelivery {

    // 1. Basic Delivery (only distance)
    double calculateDelivery(double distance) {
        double cost = distance * 10; // ₹10 per km
        System.out.println("Basic Delivery: Distance = " + distance + " km, Cost = ₹" + cost);
        return cost;
    }

    // 2. Premium Delivery (distance + priority fee)
    double calculateDelivery(double distance, double priorityFee) {
        double cost = (distance * 10) + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance + " km + Priority Fee = ₹" 
                           + priorityFee + ", Total = ₹" + cost);
        return cost;
    }

    // 3. Group Delivery (distance + discount based on number of orders)
    double calculateDelivery(double distance, int numberOfOrders) {
        double baseCost = distance * 10;
        double discount = numberOfOrders * 5; // ₹5 discount per order
        double finalCost = baseCost - discount;
        if (finalCost < 0) finalCost = 0;
        System.out.println("Group Delivery: Distance = " + distance + " km, Orders = " + numberOfOrders 
                           + ", Discount = ₹" + discount + ", Total = ₹" + finalCost);
        return finalCost;
    }

    // 4. Festival Special (distance + discount percentage + free delivery if above certain amount)
    double calculateDelivery(double distance, double discountPercent, double freeLimit) {
        double baseCost = distance * 10;
        double discount = (baseCost * discountPercent) / 100;
        double finalCost = baseCost - discount;
        if (finalCost >= freeLimit) {
            System.out.println("Festival Special: Free Delivery! Order exceeded ₹" + freeLimit);
            finalCost = 0;
        } else {
            System.out.println("Festival Special: Distance = " + distance + " km, Discount = " 
                               + discountPercent + "%, Total = ₹" + finalCost);
        }
        return finalCost;
    }
}

// Main Class
public class FoodDeliveryApp {
    public static void main(String[] args) {
        FoodDelivery fd = new FoodDelivery();

        fd.calculateDelivery(5);                   // Basic
        fd.calculateDelivery(8, 50);               // Premium
        fd.calculateDelivery(10, 3);               // Group
        fd.calculateDelivery(12, 20, 100);         // Festival Special
    }
}
