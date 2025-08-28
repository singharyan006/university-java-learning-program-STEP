// Write a program to create a Vehicle rental system demonstrating 
// static and instance members 
// Hint => 
// a. Create a Vehicle class with private instance variables: vehicleId (String), brand 
// (String), model (String), rentPerDay (double), isAvailable (boolean) 
// 4 
// b. Create static variables: totalVehicles (int), totalRevenue (double), 
// companyName (String), rentalDays (int to track total rental days) 
// c. Create a constructor and instance methods: rentVehicle(int days) which 
// calculates rent and updates availability, returnVehicle() to make vehicle available 
// again 
// d. Create static methods: setCompanyName(String name), getTotalRevenue(), 
// getAverageRentPerDay(), displayCompanyStats() 
// e. Create a method calculateRent(int days) that returns rental amount and 
// updates static revenue counter 
// f. Create a displayVehicleInfo() method showing all vehicle details including 
// rental history 
// g. In main, demonstrate the difference between static and instance members by creating 
// multiple vehicle objects, show how static variables are shared across all objects while 
// instance variables are unique to each object 


public class VehicleRentalSystem {

    static class Vehicle {
        private String vehicleId;
        private String brand;
        private String model;
        private double rentPerDay;
        private boolean isAvailable;
        private int totalRentalDays;

        private static int totalVehicles = 0;
        private static double totalRevenue = 0.0;
        private static int rentalDays = 0;
        private static String companyName = "Default Rentals";

        // Constructor
        public Vehicle(String brand, String model, double rentPerDay) {
            this.brand = brand;
            this.model = model;
            this.rentPerDay = rentPerDay;
            this.isAvailable = true;
            this.vehicleId = generateVehicleId();
            totalVehicles++;
        }

        // Static method to generate vehicle ID
        public static String generateVehicleId() {
            return String.format("V%03d", totalVehicles + 1);
        }

        // Instance method to rent vehicle
        public void rentVehicle(int days) {
            if (!isAvailable) {
                System.out.println("Vehicle " + vehicleId + " is not available.");
                return;
            }
            double rent = calculateRent(days);
            isAvailable = false;
            totalRentalDays += days;
            System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Rent: ₹" + rent);
        }

        // Instance method to return vehicle
        public void returnVehicle() {
            if (isAvailable) {
                System.out.println("Vehicle " + vehicleId + " is already available.");
                return;
            }
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " has been returned.");
        }

        // Instance method to calculate rent
        public double calculateRent(int days) {
            double rent = rentPerDay * days;
            totalRevenue += rent;
            rentalDays += days;
            return rent;
        }

        // Display vehicle info
        public void displayVehicleInfo() {
            System.out.println("Vehicle ID     : " + vehicleId);
            System.out.println("Brand          : " + brand);
            System.out.println("Model          : " + model);
            System.out.println("Rent/Day       : ₹" + rentPerDay);
            System.out.println("Available      : " + isAvailable);
            System.out.println("Total Rented   : " + totalRentalDays + " days");
            System.out.println("-----------------------------");
        }

        // Static methods
        public static void setCompanyName(String name) {
            companyName = name;
        }

        public static double getTotalRevenue() {
            return totalRevenue;
        }

        public static double getAverageRentPerDay() {
            return rentalDays == 0 ? 0 : totalRevenue / rentalDays;
        }

        public static void displayCompanyStats() {
            System.out.println("=== " + companyName + " Stats ===");
            System.out.println("Total Vehicles     : " + totalVehicles);
            System.out.println("Total Revenue      : ₹" + String.format("%.2f", totalRevenue));
            System.out.println("Total Rental Days  : " + rentalDays);
            System.out.println("Average Rent/Day   : ₹" + String.format("%.2f", getAverageRentPerDay()));
            System.out.println("===============================");
        }
    }

    public static void main(String[] args) {
        // Set company name
        Vehicle.setCompanyName("Speedy Wheels Rentals");

        // Create vehicle objects
        Vehicle v1 = new Vehicle("Toyota", "Innova", 1500);
        Vehicle v2 = new Vehicle("Honda", "City", 1200);
        Vehicle v3 = new Vehicle("Suzuki", "Swift", 1000);

        // Rent and return operations
        v1.rentVehicle(3);
        v2.rentVehicle(2);
        v1.returnVehicle();
        v3.rentVehicle(5);

        // Display vehicle info
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        // Display company stats
        Vehicle.displayCompanyStats();
    }
}
