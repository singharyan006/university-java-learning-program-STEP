public class CarInfoDisplay {

    static class Car {
        private String brand;
        private String model;
        private double price;

        public Car(String brand, String model, double price) {
            this.brand = brand;
            this.model = model;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Car [Brand: " + brand + ", Model: " + model + ", Price: ₹" + price + "]";
        }
    }

    public static void main(String[] args) {
        Car car = new Car("Tata", "Harrier", 2150000.00);

        // Print object using toString()
        System.out.println("Car Details: " + car);

        // Print class name using getClass().getName()
        System.out.println("Class Name: " + car.getClass().getName());
    }
}
