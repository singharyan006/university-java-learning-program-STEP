// HW PROBLEM 5: Math Operations Inheritance
// Topic: Inheritance with Method Overloading
// Problem Statement:
// Create BasicMath with overloaded calculate() methods. Create AdvancedMath
// extending it and adding more overloaded methods.
// Hints:
// ● Create multiple calculate() methods with different parameters

// 2

// ● Child class inherits all overloaded methods
// ● Add new overloaded methods in child class



public class MathOperationsTest {

    // Base class with overloaded calculate() methods
    static class BasicMath {
        public int calculate(int a, int b) {
            System.out.println("BasicMath: Adding two integers");
            return a + b;
        }

        public double calculate(double a, double b) {
            System.out.println("BasicMath: Adding two doubles");
            return a + b;
        }

        public int calculate(int a) {
            System.out.println("BasicMath: Squaring an integer");
            return a * a;
        }
    }

    // Subclass with additional overloaded calculate() methods
    static class AdvancedMath extends BasicMath {
        public int calculate(int a, int b, int c) {
            System.out.println("AdvancedMath: Adding three integers");
            return a + b + c;
        }

        public double calculate(double a, double b, double c) {
            System.out.println("AdvancedMath: Multiplying three doubles");
            return a * b * c;
        }

        public String calculate(String expression) {
            System.out.println("AdvancedMath: Evaluating string expression (mock)");
            return "Result of '" + expression + "'";
        }
    }

    // Main method to test all overloaded methods
    public static void main(String[] args) {
        AdvancedMath math = new AdvancedMath();

        System.out.println("calculate(int, int): " + math.calculate(5, 3));
        System.out.println("calculate(double, double): " + math.calculate(2.5, 4.5));
        System.out.println("calculate(int): " + math.calculate(6));
        System.out.println("calculate(int, int, int): " + math.calculate(1, 2, 3));
        System.out.println("calculate(double, double, double): " + math.calculate(1.2, 3.4, 5.6));
        System.out.println("calculate(String): " + math.calculate("2 + 2 * 3"));
    }
}
