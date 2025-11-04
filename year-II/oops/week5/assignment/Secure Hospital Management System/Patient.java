import java.util.Objects;
import java.util.UUID;
import java.time.LocalDate;

public class Patient {

    // Protected health information (PHI)
    private final String patientId;
    private final MedicalRecord medicalRecord;

    // Modifiable personal data
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;

    // Current treatment info
    private int roomNumber;
    private String attendingPhysician;

    // Constructor for Standard Admission (full info)
    public Patient(String patientId, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician, MedicalRecord medicalRecord) {
        // Privacy and data integrity validation
        Objects.requireNonNull(patientId, "Patient ID cannot be null");
        Objects.requireNonNull(currentName, "Patient name cannot be null");
        Objects.requireNonNull(medicalRecord, "Medical record cannot be null");

        this.patientId = patientId;
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
        this.medicalRecord = medicalRecord;
    }

    // Constructor for Emergency Admission (minimal data)
    public Patient(String currentName) {
        this("TEMP-" + UUID.randomUUID().toString(), currentName, "N/A", "N/A", -1, "On-call",
             new MedicalRecord("TEMP-REC-" + UUID.randomUUID().toString(), "UNKNOWN", new String[]{}, new String[]{}, LocalDate.now(), "UNKNOWN"));
    }

    // Constructor for Transfer Admission (imports existing record)
    public Patient(String patientId, String currentName, String emergencyContact, String insuranceInfo, MedicalRecord medicalRecord) {
        this(patientId, currentName, emergencyContact, insuranceInfo, -1, "TBD", medicalRecord);
    }

    // --- Getters and Setters (JavaBean style) ---

    // Publicly accessible, non-sensitive info
    public String getPublicInfo() {
        return "Patient Name: " + currentName + ", Room Number: " + (roomNumber > 0 ? roomNumber : "N/A");
    }

    // Package-private for internal hospital staff
    String getBasicInfo() {
        return "Patient ID: " + patientId + ", Name: " + currentName + ", Attending Physician: " + attendingPhysician;
    }
    
    // Full access for authorized personnel (e.g., Doctor)
    public String getFullDetails(Staff staff) {
        if (staff instanceof Doctor) {
             return toString();
        }
        return "Access Denied: Insufficient privileges.";
    }


    public String getPatientId() {
        return patientId;
    }

    public MedicalRecord getMedicalRecord() {
        // The record itself is immutable, so no defensive copy needed.
        return medicalRecord;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        // Validation
        Objects.requireNonNull(currentName, "Patient name cannot be null");
        this.currentName = currentName;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = attendingPhysician;
    }

    // Audit trail via toString()
    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + "'" +
                ", currentName='" + currentName + "'" +
                ", emergencyContact='" + emergencyContact + "'" +
                ", insuranceInfo='" + insuranceInfo + "'" +
                ", roomNumber=" + roomNumber +
                ", attendingPhysician='" + attendingPhysician + "'" +
                ", medicalRecord=" + medicalRecord.toString() +
                '}';
    }
}
