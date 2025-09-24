// File: HotelBooking.java
class HotelBooking {
    // Standard booking
    public void calculatePrice(String roomType, int nights) {
        int rate = getRate(roomType);
        int total = rate * nights;
        System.out.println("Standard Booking:");
        System.out.println("Room: " + roomType + ", Nights: " + nights);
        System.out.println("Total Cost: $" + total);
        System.out.println("----------------------");
    }

    // Seasonal booking
    public void calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        int rate = getRate(roomType);
        double total = rate * nights * seasonalMultiplier;
        System.out.println("Seasonal Booking:");
        System.out.println("Room: " + roomType + ", Nights: " + nights);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier);
        System.out.println("Total Cost: $" + total);
        System.out.println("----------------------");
    }

    // Corporate booking
    public void calculatePrice(String roomType, int nights, double discount, boolean mealPackage) {
        int rate = getRate(roomType);
        double base = rate * nights;
        double discounted = base - (base * discount);
        System.out.println("Corporate Booking:");
        System.out.println("Room: " + roomType + ", Nights: " + nights);
        System.out.println("Base Price: $" + base);
        System.out.println("Corporate Discount: " + (discount * 100) + "%");
        System.out.println("Meal Package: " + (mealPackage ? "Included" : "Not included"));
        System.out.println("Final Cost: $" + discounted);
        System.out.println("----------------------");
    }

    // Wedding package
    public void calculatePrice(String roomType, int nights, int guests, double decorationFee, double cateringPerGuest) {
        int rate = getRate(roomType);
        double base = rate * nights;
        double catering = guests * cateringPerGuest;
        double total = base + decorationFee + catering;
        System.out.println("Wedding Package:");
        System.out.println("Room: " + roomType + ", Nights: " + nights);
        System.out.println("Guests: " + guests);
        System.out.println("Base Cost: $" + base);
        System.out.println("Decoration Fee: $" + decorationFee);
        System.out.println("Catering: $" + catering);
        System.out.println("Total Cost: $" + total);
        System.out.println("----------------------");
    }

    // Helper: room rate lookup
    private int getRate(String roomType) {
        return switch (roomType.toLowerCase()) {
            case "suite" -> 300;
            case "deluxe" -> 200;
            default -> 100;
        };
    }

    public static void main(String[] args) {
        HotelBooking hb = new HotelBooking();
        hb.calculatePrice("Deluxe", 3);
        hb.calculatePrice("Suite", 5, 1.2);
        hb.calculatePrice("Deluxe", 4, 0.15, true);
        hb.calculatePrice("Suite", 2, 100, 500);
    }
}
