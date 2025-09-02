public class Counter {
        static int count = 0;

    // Constructor
    Counter() {
        count++; // Increment count each time an object is created
    }

    // Static method to return the count
    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        // Create several Counter objects
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        // Display number of objects created
        System.out.println("Number of Counter objects created: " + Counter.getCount());
    }
}
