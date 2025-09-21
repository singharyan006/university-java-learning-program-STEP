// HW PROBLEM 6: Weather System Hierarchy
// Topic: Complete Inheritance Implementation
// Problem Statement:
// Create Weather → Storm → Thunderstorm (multilevel) and Weather → Sunshine
// (hierarchical). Include constructor chaining, method overriding, and polymorphism.
// Hints:
// ● Implement both multilevel and hierarchical inheritance
// ● Use constructor chaining throughout
// ● Override methods at different levels
// ● Test with polymorphic array of Weather references



public class WeatherSystemTest {

    // Base class
    static class Weather {
        protected String condition;

        public Weather(String condition) {
            this.condition = condition;
            System.out.println("Weather constructor called");
        }

        public void display() {
            System.out.println("General Weather: " + condition);
        }
    }

    // Multilevel: Weather → Storm → Thunderstorm
    static class Storm extends Weather {
        protected int windSpeed;

        public Storm(String condition, int windSpeed) {
            super(condition);
            this.windSpeed = windSpeed;
            System.out.println("Storm constructor called");
        }

        @Override
        public void display() {
            System.out.println("Stormy Weather: " + condition + ", Wind Speed: " + windSpeed + " km/h");
        }
    }

    static class Thunderstorm extends Storm {
        private boolean hasLightning;

        public Thunderstorm(String condition, int windSpeed, boolean hasLightning) {
            super(condition, windSpeed);
            this.hasLightning = hasLightning;
            System.out.println("Thunderstorm constructor called");
        }

        @Override
        public void display() {
            System.out.println("Thunderstorm: " + condition + ", Wind Speed: " + windSpeed +
                               " km/h, Lightning: " + (hasLightning ? "Yes" : "No"));
        }
    }

    // Hierarchical: Weather → Sunshine
    static class Sunshine extends Weather {
        private int temperature;

        public Sunshine(String condition, int temperature) {
            super(condition);
            this.temperature = temperature;
            System.out.println("Sunshine constructor called");
        }

        @Override
        public void display() {
            System.out.println("Sunny Weather: " + condition + ", Temperature: " + temperature + "°C");
        }
    }

    // Main method to test polymorphism
    public static void main(String[] args) {
        Weather[] forecasts = new Weather[3];
        forecasts[0] = new Thunderstorm("Heavy Rain", 80, true);
        forecasts[1] = new Sunshine("Clear Sky", 32);
        forecasts[2] = new Storm("Windy", 60);

        System.out.println("\n--- Weather Forecasts ---");
        for (Weather w : forecasts) {
            w.display(); // Polymorphic call
        }
    }
}
