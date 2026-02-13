// HW PROBLEM 4: Food Preparation Template
// Topic: Template Method Pattern
// Problem Statement:
// Create Food class with template method prepare() that calls wash(), cook(), serve().
// Create Pizza and Soup with different implementations.
// Hints:
// ● Template method calls other methods in sequence
// ● Child classes override individual step methods
// ● Test template method on different food types


public class FoodPreparationTest {

    // Abstract base class defining the template method
    static abstract class Food {
        // Template method
        public final void prepare() {
            wash();
            cook();
            serve();
        }

        // Steps to be implemented by subclasses
        protected abstract void wash();
        protected abstract void cook();
        protected abstract void serve();
    }

    // Concrete subclass: Pizza
    static class Pizza extends Food {
        @Override
        protected void wash() {
            System.out.println("Washing vegetables and dough ingredients for Pizza.");
        }

        @Override
        protected void cook() {
            System.out.println("Baking the Pizza in the oven.");
        }

        @Override
        protected void serve() {
            System.out.println("Serving Pizza with extra cheese.");
        }
    }

    // Concrete subclass: Soup
    static class Soup extends Food {
        @Override
        protected void wash() {
            System.out.println("Washing vegetables and herbs for Soup.");
        }

        @Override
        protected void cook() {
            System.out.println("Boiling Soup ingredients in a pot.");
        }

        @Override
        protected void serve() {
            System.out.println("Serving hot Soup in a bowl.");
        }
    }

    // Main method to test template method
    public static void main(String[] args) {
        System.out.println("Preparing Pizza:");
        Food pizza = new Pizza();
        pizza.prepare();

        System.out.println("\nPreparing Soup:");
        Food soup = new Soup();
        soup.prepare();
    }
}
