// PROBLEM 3: Transportation Fleet Management
// Concept: Dynamic Method Dispatch
// A unified dispatch system where one call triggers different behavior
// for Bus, Taxi, Train, and Bike at runtime.

public class TransportationFleetDemo {
    public static void main(String[] args) {
        Transport[] fleet = new Transport[] {
            new Bus("Route 12A", 50),
            new Taxi("TX-204", 1.5),
            new Train("Red Line", 8),
            new Bike("Dock-7", 2)
        };

        Dispatcher dispatcher = new Dispatcher();

        for (Transport t : fleet) {
            dispatcher.dispatch(t, 10.0); // single dispatch call, different results
            System.out.println();
        }
    }
}

abstract class Transport {
    protected String id;

    public Transport(String id) {
        this.id = id;
    }

    // single unified action to be overridden
    public abstract void serve(double distanceKm);
}

class Bus extends Transport {
    private int capacity;
    private int currentPassengers;

    public Bus(String route, int capacity) {
        super(route);
        this.capacity = capacity;
        this.currentPassengers = Math.min(30, capacity); // example current load
    }

    @Override
    public void serve(double distanceKm) {
        System.out.println("Bus on " + id + " serving fixed route.");
        System.out.println("Capacity: " + capacity + ", Current passengers: " + currentPassengers);
        System.out.println("Estimated route distance: " + distanceKm + " km. Stopping at scheduled stops.");
    }
}

class Taxi extends Transport {
    private double baseFare = 3.0;
    private double perKm = 1.2;

    public Taxi(String plate, double perKm) {
        super(plate);
        this.perKm = perKm;
    }

    @Override
    public void serve(double distanceKm) {
        double fare = baseFare + (perKm * distanceKm);
        System.out.println("Taxi " + id + " providing door-to-door service.");
        System.out.printf("Distance: %.2f km | Fare: $%.2f\n", distanceKm, fare);
    }
}

class Train extends Transport {
    private int cars;
    private int seatsPerCar = 80;

    public Train(String line, int cars) {
        super(line);
        this.cars = cars;
    }

    @Override
    public void serve(double distanceKm) {
        int totalSeats = cars * seatsPerCar;
        System.out.println("Train on " + id + " running on schedule.");
        System.out.println("Cars: " + cars + ", Total seats: " + totalSeats);
        System.out.println("Planned travel distance: " + distanceKm + " km. Next arrivals on timetable.");
    }
}

class Bike extends Transport {
    private int availableUnits;

    public Bike(String dock, int availableUnits) {
        super(dock);
        this.availableUnits = availableUnits;
    }

    @Override
    public void serve(double distanceKm) {
        System.out.println("Bike from " + id + " is ideal for short eco trips.");
        System.out.println("Available units: " + availableUnits);
        System.out.println("Suggested trip distance: " + distanceKm + " km. Enjoy the ride!");
    }
}

class Dispatcher {
    // single method to dispatch any Transport subtype
    public void dispatch(Transport t, double distanceKm) {
        System.out.println("Dispatching vehicle: " + t.getClass().getSimpleName());
        t.serve(distanceKm);
    }
}
