// HW PROBLEM 2: Tool Access Levels
// Topic: Access Modifiers in Inheritance
// Problem Statement:
// Create Tool class with private, protected, and public fields. Create Hammer class and
// test field accessibility.
// Hints:
// ● Try accessing different access level fields from child
// ● Use getters for private fields
// ● Document which fields are directly accessible



public class ToolAccessTest {

    // Tool class with private, protected, and public fields
    static class Tool {
        private String privateMaterial = "Steel";
        protected int protectedWeight = 5;
        public String publicType = "Hand Tool";

        // Getter for private field
        public String getPrivateMaterial() {
            return privateMaterial;
        }
    }

    // Hammer class extending Tool
    static class Hammer extends Tool {

        public void testAccess() {
            // System.out.println(privateMaterial); // ❌ Not accessible directly
            System.out.println("Accessing private field via getter: " + getPrivateMaterial()); // ✅

            System.out.println("Accessing protected field directly: " + protectedWeight); // ✅
            System.out.println("Accessing public field directly: " + publicType); // ✅
        }
    }

    // Main method to test field accessibility
    public static void main(String[] args) {
        Hammer hammer = new Hammer();
        System.out.println("Testing field access from Hammer class:");
        hammer.testAccess();

        System.out.println("\nTesting field access from outside the class:");
        System.out.println("Public field: " + hammer.publicType); // ✅
        // System.out.println("Protected field: " + hammer.protectedWeight); // ⚠️ Accessible only within same package or subclass
        // System.out.println("Private field: " + hammer.privateMaterial); // ❌ Not accessible
        System.out.println("Private field via getter: " + hammer.getPrivateMaterial()); // ✅
    }
}
