// Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
// public static double convertFarhenheitToCelsius(double farhenheit) => 
// Method to convert Fahrenheit to Celsius and return the value. Use the following code  double farhenheit2celsius = (farhenheit - 32) * 5 / 9;
// Method to convert Celsius to Fahrenheit and return the value. Use the following code  double celsius2farhenheit = (celsius * 9 / 5) + 32;
// Method to convert pounds to kilograms and return the value. Use the following code  double pounds2kilograms = 0.453592;
// Method to convert kilograms to pounds and return the value. Use the following code  double kilograms2pounds = 2.20462; 
// Method to convert gallons to liters and return the value. Use following code to convert   double gallons2liters = 3.78541; 
// Method to convert liters to gallons and return the value. Use following code to convert  double liters2gallons = 0.264172;

import java.util.Scanner;

public class UnitConvertor3 {
    // Method to convert Fahrenheit to Celsius
    public static double convertFahrenheitToCelsius(double fahrenheit) {
        double fahrenheit2celsius = (fahrenheit - 32) * 5 / 9;
        return fahrenheit2celsius;
    }

    // Method to convert Celsius to Fahrenheit
    public static double convertCelsiusToFahrenheit(double celsius) {
        double celsius2fahrenheit = (celsius * 9 / 5) + 32;
        return celsius2fahrenheit;
    }

    // Method to convert pounds to kilograms
    public static double convertPoundsToKilograms(double pounds) {
        double pounds2kilograms = 0.453592;
        return pounds * pounds2kilograms;
    }

    // Method to convert kilograms to pounds
    public static double convertKilogramsToPounds(double kilograms) {
        double kilograms2pounds = 2.20462;
        return kilograms * kilograms2pounds;
    }

    // Method to convert gallons to liters
    public static double convertGallonsToLiters(double gallons) {
        double gallons2liters = 3.78541;
        return gallons * gallons2liters;
    }

    // Method to convert liters to gallons
    public static double convertLitersToGallons(double liters) {
        double liters2gallons = 0.264172;
        return liters * liters2gallons;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Unit Converter!");
        System.out.println("Choose a conversion type:");
        System.out.println("1. Fahrenheit to Celsius");
        System.out.println("2. Celsius to Fahrenheit");
        System.out.println("3. Pounds to Kilograms");
        System.out.println("4. Kilograms to Pounds");
        System.out.println("5. Gallons to Liters");
        System.out.println("6. Liters to Gallons");

        int choice = sc.nextInt();
        System.out.println("Enter the value to convert:");
        double value = sc.nextDouble();
        double result = 0;

        switch (choice) {
            case 1:
                result = convertFahrenheitToCelsius(value);
                System.out.println(value + " Fahrenheit is " + result + " Celsius.");
                break;
            case 2:
                result = convertCelsiusToFahrenheit(value);
                System.out.println(value + " Celsius is " + result + " Fahrenheit.");
                break;
            case 3:
                result = convertPoundsToKilograms(value);
                System.out.println(value + " Pounds is " + result + " Kilograms.");
                break;
            case 4:
                result = convertKilogramsToPounds(value);
                System.out.println(value + " Kilograms is " + result + " Pounds.");
                break;
            case 5:
                result = convertGallonsToLiters(value);
                System.out.println(value + " Gallons is " + result + " Liters.");
                break;
            case 6:
                result = convertLitersToGallons(value);
                System.out.println(value + " Liters is " + result + " Gallons.");
                break;
            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}