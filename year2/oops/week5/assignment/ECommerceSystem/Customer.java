import java.time.LocalDateTime;

public class Customer {
    // Immutable account info
    private final String customerId;
    private final String email;
    private final LocalDateTime accountCreationDate;

    // Modifiable personal data
    private String name;
    private String phoneNumber;
    private String preferredLanguage;

    // Constructor for registered customers
    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = LocalDateTime.now();
    }

    // Constructor for guest checkout
    public Customer(String email, String phoneNumber) {
        this("GUEST_" + System.currentTimeMillis(), email, "Guest", phoneNumber, "en");
    }

    // Constructor for premium members
    public Customer(String customerId, String email, String name) {
        this(customerId, email, name, null, "en");
        // Special logic for premium members could be added here
    }

    // Constructor for corporate accounts
    public Customer(String customerId, String email, String name, String companyName) {
        this(customerId, email, name, null, "en");
        // Validation for corporate accounts
        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("Corporate accounts require a company name.");
        }
    }

    // Getters for all fields
    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    // Setters for modifiable data
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    // Access control methods
    String getCreditRating() { // Package-private for internal use
        // Dummy logic for credit rating
        return "Good";
    }

    public String getPublicProfile() { // Public for safe information
        return "Customer: " + name + ", ID: " + customerId.substring(0, 5) + "...";
    }
}