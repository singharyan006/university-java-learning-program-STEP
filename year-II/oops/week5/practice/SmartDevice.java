import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmartDevice {
    // === Read-only properties ===
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    // === Write-only properties (stored as hashes) ===
    private int hashedEncryptionKey;
    private int hashedAdminPassword;

    // === Read-write properties ===
    private String deviceName;
    private boolean enabled;

    // === Internal state ===
    private final LocalDateTime startupTime;

    // === Constructor ===
    public SmartDevice(String deviceName) {
        this.deviceId = UUID.randomUUID().toString();
        this.manufacturingDate = LocalDateTime.now();
        this.serialNumber = "SN-" + UUID.randomUUID().toString().substring(0, 8);
        this.startupTime = LocalDateTime.now();
        this.deviceName = deviceName;
        this.enabled = true; // default enabled
    }

    // === Read-Only Property Methods ===
    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getManufacturingDate() {
        return manufacturingDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public long getUptime() {
        return Duration.between(startupTime, LocalDateTime.now()).toSeconds();
    }

    public int getDeviceAge() {
        return LocalDateTime.now().getYear() - manufacturingDate.getYear();
    }

    // === Write-Only Property Methods ===
    public void setEncryptionKey(String key) {
        if (key == null || key.length() < 8) {
            throw new IllegalArgumentException("Encryption key must be at least 8 characters long.");
        }
        this.hashedEncryptionKey = key.hashCode();
    }

    public void setAdminPassword(String password) {
        if (password == null || password.length() < 10 || !password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must be at least 10 chars and contain a digit.");
        }
        this.hashedAdminPassword = password.hashCode();
    }

    public boolean validateEncryptionKey(String key) {
        return key != null && key.hashCode() == this.hashedEncryptionKey;
    }

    public boolean validateAdminPassword(String password) {
        return password != null && password.hashCode() == this.hashedAdminPassword;
    }

    // === Read-Write Property Methods ===
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        this.deviceName = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // === Utility Methods ===
    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-only");
        info.put("manufacturingDate", "Read-only");
        info.put("serialNumber", "Read-only");
        info.put("uptime", "Computed Read-only");
        info.put("deviceAge", "Computed Read-only");
        info.put("encryptionKey", "Write-only");
        info.put("adminPassword", "Write-only");
        info.put("deviceName", "Read-Write");
        info.put("isEnabled", "Read-Write");
        return info;
    }

    public void resetDevice() {
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
        this.enabled = false; // simulate device reset
    }

    // === Demo Main Method ===
    public static void main(String[] args) {
        SmartDevice device1 = new SmartDevice("Router-01");

        // Read-only properties
        System.out.println("Device ID: " + device1.getDeviceId());
        System.out.println("Manufacturing Date: " + device1.getManufacturingDate());
        System.out.println("Serial Number: " + device1.getSerialNumber());
        System.out.println("Device Age: " + device1.getDeviceAge());

        // Wait a bit to show uptime
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println("Uptime: " + device1.getUptime() + " seconds");

        // Write-only properties
        device1.setEncryptionKey("SuperSecureKey");
        device1.setAdminPassword("AdminPass123");

        System.out.println("Encryption key validation: " + device1.validateEncryptionKey("SuperSecureKey"));
        System.out.println("Admin password validation: " + device1.validateAdminPassword("AdminPass123"));

        // Read-Write properties
        System.out.println("Device Name: " + device1.getDeviceName());
        device1.setDeviceName("Router-Home");
        System.out.println("Updated Device Name: " + device1.getDeviceName());

        System.out.println("Enabled: " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Enabled after change: " + device1.isEnabled());

        // Multiple devices
        SmartDevice device2 = new SmartDevice("Switch-01");
        System.out.println("\nDevice2 ID: " + device2.getDeviceId());
        System.out.println("Device1 ID still intact: " + device1.getDeviceId());

        // Property info
        System.out.println("\nProperty Info: " + device1.getPropertyInfo());

        // Reset device
        device1.resetDevice();
        System.out.println("After reset, encryption valid? " + device1.validateEncryptionKey("SuperSecureKey"));
    }
}
