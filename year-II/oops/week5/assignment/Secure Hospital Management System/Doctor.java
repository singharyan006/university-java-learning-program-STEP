import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Doctor implements Staff {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certifications);
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Set<String> getCertifications() {
        return Collections.unmodifiableSet(certifications);
    }

    @Override
    public String getStaffId() {
        return "Dr-" + licenseNumber;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "licenseNumber='" + licenseNumber + "'" +
                ", specialty='" + specialty + "'" +
                '}';
    }
}
