import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public final class MedicalRecord {

    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
        // HIPAA compliance validation
        Objects.requireNonNull(recordId, "Record ID cannot be null");
        Objects.requireNonNull(patientDNA, "Patient DNA cannot be null");
        Objects.requireNonNull(allergies, "Allergies array cannot be null");
        Objects.requireNonNull(medicalHistory, "Medical history array cannot be null");
        Objects.requireNonNull(birthDate, "Birth date cannot be null");
        Objects.requireNonNull(bloodType, "Blood type cannot be null");

        if (recordId.trim().isEmpty() || patientDNA.trim().isEmpty() || bloodType.trim().isEmpty()) {
            throw new IllegalArgumentException("IDs, DNA, and blood type cannot be empty.");
        }

        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = Arrays.copyOf(allergies, allergies.length); // Defensive copy
        this.medicalHistory = Arrays.copyOf(medicalHistory, medicalHistory.length); // Defensive copy
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    // Getters with defensive copying for mutable arrays
    public String getRecordId() {
        return recordId;
    }

    public String getPatientDNA() {
        return patientDNA;
    }

    public String[] getAllergies() {
        return Arrays.copyOf(allergies, allergies.length);
    }

    public String[] getMedicalHistory() {
        return Arrays.copyOf(medicalHistory, medicalHistory.length);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public final boolean isAllergicTo(String substance) {
        if (substance == null || substance.trim().isEmpty()) {
            return false;
        }
        for (String allergy : this.allergies) {
            if (allergy.equalsIgnoreCase(substance)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + "'" +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + "'" +
                ", allergies=" + Arrays.toString(allergies) +
                ", medicalHistory=" + Arrays.toString(medicalHistory) +
                '}';
    }
}
