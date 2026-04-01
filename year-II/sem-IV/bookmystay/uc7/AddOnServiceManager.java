import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServicesMap;

    public AddOnServiceManager() {
        reservationServicesMap = new HashMap<>();
    }

    public void addServiceToReservation(String reservationId, AddOnService service) {
        reservationServicesMap.computeIfAbsent(reservationId, key -> new ArrayList<>()).add(service);
    }

    public List<AddOnService> getServicesForReservation(String reservationId) {
        return reservationServicesMap.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateAdditionalCost(String reservationId) {
        double total = 0.0;
        List<AddOnService> services = reservationServicesMap.getOrDefault(reservationId, new ArrayList<>());
        for (AddOnService service : services) {
            total += service.getServiceCost();
        }
        return total;
    }

    public void displayServicesForReservation(String reservationId) {
        List<AddOnService> services = getServicesForReservation(reservationId);
        System.out.println("\nAdd-On Services for Reservation " + reservationId + ":");

        if (services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (int i = 0; i < services.size(); i++) {
            AddOnService service = services.get(i);
            System.out.println((i + 1) + ". " + service);
        }

        System.out.println("Total Additional Cost: Rs. " + calculateAdditionalCost(reservationId));
    }

    public void displayAllServiceMappings() {
        System.out.println("\n--- Reservation to Add-On Services Mapping ---");
        for (Map.Entry<String, List<AddOnService>> entry : reservationServicesMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("---------------------------------------------");
    }
}
