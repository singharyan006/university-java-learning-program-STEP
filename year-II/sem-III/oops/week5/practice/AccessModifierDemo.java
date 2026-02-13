// PRACTICE PROBLEM 1: Access Modifiers - The Four 
// Levels of Security 
// Understanding private, default, protected, public modifiers 
// // File: AccessModifierDemo.java 
// package com.company.security; 
 
// public class AccessModifierDemo { 
//     // TODO: Create four different fields with different access modifiers: 
//     // - privateField (int) - only accessible within this class 
//     // - defaultField (String) - accessible within same package 
//     // - protectedField (double) - accessible in package + subclasses 
//     // - publicField (boolean) - accessible everywhere 
     
//     // TODO: Create four methods with matching access levels: 
//     // - privateMethod() - prints "Private method called" 
//     // - defaultMethod() - prints "Default method called"   
//     // - protectedMethod() - prints "Protected method called" 
//     // - publicMethod() - prints "Public method called" 
     
//     // TODO: Create a constructor that initializes all fields 
     
//     // TODO: Create a public method testInternalAccess() that: 
//     // - Accesses and prints all four fields 
//     // - Calls all four methods 
//     // - Demonstrates that private members are accessible within same class 
     
//     public static void main(String[] args) { 
//         // TODO: Create an AccessModifierDemo object 
//         // TODO: Try to access each field and method 
//         // TODO: Document in comments which ones work and which cause errors 
//         // TODO: Call testInternalAccess() to show internal accessibility 
//     } 
// } 
// 1 
 
 
// // TODO: Create a second class in the SAME package: 
// class SamePackageTest { 
//     public static void testAccess() { 
//         // TODO: Create AccessModifierDemo object 
//         // TODO: Try accessing each field and method 
//         // TODO: Document which access modifiers work within same package 
//     } 
// } 



// package com.company.security;

public class AccessModifierDemo {
    // Fields with different access modifiers
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;

    // Constructor to initialize all fields
    public AccessModifierDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    // Methods with different access modifiers
    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Public method to test internal access
    public void testInternalAccess() {
        System.out.println("privateField: " + privateField);
        System.out.println("defaultField: " + defaultField);
        System.out.println("protectedField: " + protectedField);
        System.out.println("publicField: " + publicField);

        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo(10, "default", 3.14, true);

        // Accessible: publicField
        System.out.println("Accessing publicField: " + demo.publicField);

        // Accessible: defaultField (same package)
        System.out.println("Accessing defaultField: " + demo.defaultField);

        // Accessible: protectedField (same package)
        System.out.println("Accessing protectedField: " + demo.protectedField);

        // Not accessible: privateField
        // System.out.println("Accessing privateField: " + demo.privateField); // ❌ Compile-time error

        // Accessible: publicMethod
        demo.publicMethod();

        // Accessible: defaultMethod (same package)
        demo.defaultMethod();

        // Accessible: protectedMethod (same package)
        demo.protectedMethod();

        // Not accessible: privateMethod
        // demo.privateMethod(); // ❌ Compile-time error

        // Internal access test
        demo.testInternalAccess();
    }
}
