// Vehicle Fleet Management System 
// Topic: Inheritance Simulation and Resource Management 
// Problem Statement: Build a vehicle fleet management system for a transportation company. 
// Requirements: 
// ● Create a base Vehicle class with: vehicleId (String), brand (String), model 
// (String), year (int), mileage (double), fuelType (String), currentStatus (String) 
// ● Create specific vehicle types: Car, Bus, Truck classes with unique attributes 
// (seatingCapacity for Bus, loadCapacity for Truck, etc.) 
// ● Include static variables: totalVehicles (int), fleetValue (double), companyName 
// (String), totalFuelConsumption (double) 
// ● Implement methods: assignDriver(), scheduleMaintenance(), 
// calculateRunningCost(), updateMileage(), checkServiceDue() 
// ● Create a Driver class with: driverId, driverName, licenseType, 
// assignedVehicle, totalTrips 
// ● Include static methods: getFleetUtilization(), 
// calculateTotalMaintenanceCost(), getVehiclesByType() 
// ● Implement trip management and fuel consumption tracking 
// Deliverables: Comprehensive fleet management system with vehicle tracking, driver 
// assignment, and operational cost analysis. 


import java.util.*;

public class VehicleFleetManagementSystem {

    static class Vehicle {
        protected String vehicleId;
        protected String brand;
        protected String model;
        protected int year;
        protected double mileage;
        protected String fuelType;
        protected String currentStatus;

        protected Driver assignedDriver;

        protected static int totalVehicles = 0;
        protected static double fleetValue = 0.0;
        protected static String companyName = "TransFleet Logistics";
        protected static double totalFuelConsumption = 0.0;

        public Vehicle(String brand, String model, int year, double mileage, String fuelType, String currentStatus) {
            this.vehicleId = generateVehicleId();
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.fuelType = fuelType;
            this.currentStatus = currentStatus;
            totalVehicles++;
        }

        public static String generateVehicleId() {
            return "V" + (totalVehicles + 1);
        }

        public void assignDriver(Driver driver) {
            this.assignedDriver = driver;
            driver.assignedVehicle = this;
            System.out.println(driver.driverName + " assigned to " + vehicleId);
        }

        public void scheduleMaintenance() {
            currentStatus = "Under Maintenance";
            System.out.println("Vehicle " + vehicleId + " scheduled for maintenance.");
        }

        public double calculateRunningCost(double fuelRatePerKm) {
            return mileage * fuelRatePerKm;
        }

        public void updateMileage(double additionalKm) {
            mileage += additionalKm;
            totalFuelConsumption += additionalKm * getFuelEfficiency();
        }

        public boolean checkServiceDue() {
            return mileage >= 10000;
        }

        public double getFuelEfficiency() {
            return fuelType.equalsIgnoreCase("Diesel") ? 0.12 : 0.10;
        }

        public void displayInfo() {
            System.out.println(vehicleId + " | " + brand + " " + model + " | " + year + " | Mileage: " + mileage + " km | Status: " + currentStatus);
        }
    }

    static class Car extends Vehicle {
        int passengerCapacity;

        public Car(String brand, String model, int year, double mileage, String fuelType, int passengerCapacity) {
            super(brand, model, year, mileage, fuelType, "Available");
            this.passengerCapacity = passengerCapacity;
        }
    }

    static class Bus extends Vehicle {
        int seatingCapacity;

        public Bus(String brand, String model, int year, double mileage, String fuelType, int seatingCapacity) {
            super(brand, model, year, mileage, fuelType, "Available");
            this.seatingCapacity = seatingCapacity;
        }
    }

    static class Truck extends Vehicle {
        double loadCapacity;

        public Truck(String brand, String model, int year, double mileage, String fuelType, double loadCapacity) {
            super(brand, model, year, mileage, fuelType, "Available");
            this.loadCapacity = loadCapacity;
        }
    }

    static class Driver {
        String driverId;
        String driverName;
        String licenseType;
        Vehicle assignedVehicle;
        int totalTrips;

        public Driver(String driverName, String licenseType) {
            this.driverId = generateDriverId();
            this.driverName = driverName;
            this.licenseType = licenseType;
            this.totalTrips = 0;
        }

        public static String generateDriverId() {
            return "D" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        }

        public void completeTrip(double distance) {
            totalTrips++;
            if (assignedVehicle != null) {
                assignedVehicle.updateMileage(distance);
                System.out.println(driverName + " completed trip of " + distance + " km with " + assignedVehicle.vehicleId);
            }
        }
    }

    // Static fleet operations
    public static double calculateTotalMaintenanceCost(Vehicle[] vehicles, double costPerVehicle) {
        double total = 0;
        for (Vehicle v : vehicles) {
            if (v.checkServiceDue()) total += costPerVehicle;
        }
        return total;
    }

    public static double getFleetUtilization(Vehicle[] vehicles) {
        int active = 0;
        for (Vehicle v : vehicles) {
            if (v.currentStatus.equalsIgnoreCase("Available")) active++;
        }
        return (double) active / vehicles.length * 100;
    }

    public static void getVehiclesByType(Vehicle[] vehicles, String type) {
        System.out.println("Vehicles of type: " + type);
        for (Vehicle v : vehicles) {
            if ((type.equalsIgnoreCase("Car") && v instanceof Car) ||
                (type.equalsIgnoreCase("Bus") && v instanceof Bus) ||
                (type.equalsIgnoreCase("Truck") && v instanceof Truck)) {
                v.displayInfo();
            }
        }
    }

    public static void main(String[] args) {
        Vehicle[] fleet = {
            new Car("Toyota", "Corolla", 2020, 9500, "Petrol", 5),
            new Bus("Volvo", "9400", 2019, 12000, "Diesel", 40),
            new Truck("Tata", "Prima", 2021, 8000, "Diesel", 20.5)
        };

        Driver d1 = new Driver("Alice", "Heavy");
        Driver d2 = new Driver("Bob", "Light");

        fleet[0].assignDriver(d2);
        fleet[1].assignDriver(d1);

        d1.completeTrip(300);
        d2.completeTrip(150);

        for (Vehicle v : fleet) {
            v.displayInfo();
        }

        System.out.println("Fleet Utilization: " + getFleetUtilization(fleet) + "%");
        System.out.println("Total Maintenance Cost: ₹" + calculateTotalMaintenanceCost(fleet, 5000));
        System.out.println("Total Fuel Consumption: " + Vehicle.totalFuelConsumption + " liters");

        getVehiclesByType(fleet, "Bus");
    }
}
