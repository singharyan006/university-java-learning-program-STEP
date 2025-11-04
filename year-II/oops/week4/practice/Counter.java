public class Counter {
    // Static variable to track number of objects
    static int count = 0;

    // Constructor increments count
    public Counter() {
        count++;
    }

    // Static method to return current count
    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        System.out.println("=== OBJECT CREATION TRACKER ===");

        // Create several Counter objects
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        Counter c4 = new Counter();

        // Display number of objects created
        System.out.println("Total Counter objects created: " + Counter.getCount());
    }
}
