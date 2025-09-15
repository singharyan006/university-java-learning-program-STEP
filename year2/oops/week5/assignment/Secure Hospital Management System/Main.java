import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Setting up Hospital Management System ---");
        HospitalSystem hospital = new HospitalSystem();

        // Create Staff
        Doctor drHouse = new Doctor("MD123", "Nephrology", new HashSet<>(Arrays.asList("Board Certified", "PhD")));
        Nurse nurseJackie = new Nurse("RN456", "Day", Collections.singletonList("Certified Nurse Anesthetist"));
        Administrator adminCuddy = new Administrator("ADM789", Collections.singletonList("Full Access"));

        System.out.println("\n--- Scenario 1: Standard Admission ---");
        MedicalRecord recordJohn = new MedicalRecord(
            "MR001",
            "AGCT...",
            new String[]{"Peanuts", "Penicillin"},
            new String[]{"2010: Broken Arm", "2018: Pneumonia"},
            LocalDate.of(1985, 5, 20),
            "O+"
        );
        Patient patientJohn = new Patient(
            "P001",
            "John Doe",
            "Jane Doe (555-1234)",
            "BlueCross 12345",
            101,
            "Dr. House",
            recordJohn
        );
        hospital.admitPatient(patientJohn, drHouse); // Doctor admits

        System.out.println("\n--- Scenario 2: Emergency Admission ---");
        Patient patientJane = new Patient("Jane Smith"); // Minimal info
        hospital.admitPatient(patientJane, nurseJackie); // Nurse cannot admit without a room
        patientJane.setRoomNumber(102); // Assign a room
        hospital.admitPatient(patientJane, nurseJackie); // Now nurse can admit

        System.out.println("\n--- Scenario 3: Accessing Patient Data ---");
        System.out.println("Doctor accessing John's data: " + hospital.getPatientInfo("P001", drHouse));
        System.out.println("Nurse accessing John's data: " + hospital.getPatientInfo("P001", nurseJackie));
        System.out.println("Public Info for Jane: " + patientJane.getPublicInfo());

        System.out.println("\n--- Scenario 4: Data Immutability & Encapsulation ---");
        Patient retrievedJohn = hospital.findPatient("P001");
        if (retrievedJohn != null) {
            System.out.println("Is John allergic to Peanuts? " + retrievedJohn.getMedicalRecord().isAllergicTo("Peanuts"));
            
            // Try to modify immutable data (will fail to compile if setters existed)
            // retrievedJohn.getMedicalRecord().setBloodType("A-"); // This would be a compilation error
            
            // Try to modify the returned array
            String[] allergies = retrievedJohn.getMedicalRecord().getAllergies();
            allergies[0] = "Shellfish"; // Modify the copy
            
            // Verify original data is unchanged
            System.out.println("Original allergies after modification attempt: " + Arrays.toString(retrievedJohn.getMedicalRecord().getAllergies()));
            
            // Modify mutable data via setter
            System.out.println("John's old contact: " + retrievedJohn.getEmergencyContact());
            retrievedJohn.setEmergencyContact("John Smith Sr. (555-5678)");
            System.out.println("John's new contact: " + retrievedJohn.getEmergencyContact());
        }
        
        System.out.println("\n--- Scenario 5: Internal Operations ---");
        hospital.dischargePatient("P001");
        System.out.println("Searching for John after discharge: " + hospital.findPatient("P001"));
    }
}
