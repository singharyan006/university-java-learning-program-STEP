import java.util.HashMap;
import java.util.Map;

public class HospitalSystem {

    // Static final constants for policies
    public static final String HOSPITAL_NAME = "General Hospital";
    public static final String PRIVACY_POLICY_URL = "http://example.com/privacy";

    private final Map<String, Patient> patientRegistry = new HashMap<>();

    public boolean admitPatient(Patient patient, Staff staff) {
        if (validateStaffAccess(staff, patient)) {
            patientRegistry.put(patient.getPatientId(), patient);
            System.out.println("Patient " + patient.getCurrentName() + " admitted successfully by " + staff.getRole() + " " + staff.getStaffId());
            return true;
        } else {
            System.out.println("Admission failed: Staff " + staff.getRole() + " " + staff.getStaffId() + " does not have permission.");
            return false;
        }
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        // Doctors and Administrators can admit any patient.
        // Nurses can only admit patients if they are assigned a room (simulating a workflow).
        if (staff instanceof Doctor || staff instanceof Administrator) {
            return true;
        }
        if (staff instanceof Nurse && patient instanceof Patient) {
            Patient p = (Patient) patient;
            // Simplified logic: A nurse can handle admission if a room is being assigned.
            if (p.getRoomNumber() > 0) {
                return true;
            }
        }
        return false;
    }
    
    // Package-private method for internal operations
    void dischargePatient(String patientId) {
        if (patientRegistry.containsKey(patientId)) {
            Patient p = patientRegistry.remove(patientId);
            System.out.println("Internal operation: Patient " + p.getCurrentName() + " discharged.");
        }
    }

    public Patient findPatient(String patientId) {
        return patientRegistry.get(patientId);
    }

    // Example of role-based data access
    public String getPatientInfo(String patientId, Staff staff) {
        Patient patient = patientRegistry.get(patientId);
        if (patient == null) {
            return "Patient not found.";
        }

        if (staff instanceof Doctor) {
            // Doctors get full details
            return patient.getFullDetails(staff);
        } else if (staff instanceof Nurse || staff instanceof Administrator) {
            // Nurses and Admins get basic info
            return patient.getBasicInfo();
        } else {
            // Default to public info for others
            return patient.getPublicInfo();
        }
    }
}
