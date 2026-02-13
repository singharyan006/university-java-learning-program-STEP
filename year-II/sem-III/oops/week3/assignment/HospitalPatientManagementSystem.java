// Hospital Patient Management System 
// Topic: Advanced Object Relationships and Data Management 
// Problem Statement: Develop a hospital patient management system with appointments, 
// treatments, and billing. 
// Requirements: 
// ● Create a Patient class with: patientId (String), patientName (String), age (int), 
// gender (String), contactInfo (String), medicalHistory (String array), 
// currentTreatments (String array) 
// ● Create a Doctor class with: doctorId (String), doctorName (String), 
// specialization (String), availableSlots (String array), patientsHandled (int), 
// consultationFee (double) 
// ● Create an Appointment class with: appointmentId (String), patient (Patient), 
// doctor (Doctor), appointmentDate (String), appointmentTime (String), status 
// (String) 
// ● Include static variables: totalPatients (int), totalAppointments (int), 
// hospitalName (String), totalRevenue (double) 
// ● Implement methods: scheduleAppointment(), cancelAppointment(), 
// generateBill(), updateTreatment(), dischargePatient() 
// ● Create different appointment types (Consultation, Follow-up, Emergency) with different 
// billing rates 
// ● Include static methods: generateHospitalReport(), getDoctorUtilization(), 
// getPatientStatistics() 
// ● Implement patient history tracking and treatment management 
// Deliverables: Complete hospital management system with patient records, appointment 
// scheduling, doctor management, and billing integration.


import java.util.*;

public class HospitalPatientManagementSystem {

    static class Patient {
        String patientId;
        String patientName;
        int age;
        String gender;
        String contactInfo;
        String[] medicalHistory;
        String[] currentTreatments;

        static int totalPatients = 0;

        public Patient(String patientName, int age, String gender, String contactInfo) {
            this.patientId = generatePatientId();
            this.patientName = patientName;
            this.age = age;
            this.gender = gender;
            this.contactInfo = contactInfo;
            this.medicalHistory = new String[10];
            this.currentTreatments = new String[5];
            totalPatients++;
        }

        public static String generatePatientId() {
            return "P" + (totalPatients + 1);
        }

        public void updateTreatment(String treatment) {
            for (int i = 0; i < currentTreatments.length; i++) {
                if (currentTreatments[i] == null) {
                    currentTreatments[i] = treatment;
                    return;
                }
            }
        }

        public void dischargePatient() {
            Arrays.fill(currentTreatments, null);
            System.out.println(patientName + " has been discharged.");
        }

        public void displayInfo() {
            System.out.println("Patient ID: " + patientId + " | Name: " + patientName + " | Age: " + age + " | Gender: " + gender);
        }
    }

    static class Doctor {
        String doctorId;
        String doctorName;
        String specialization;
        String[] availableSlots;
        int patientsHandled;
        double consultationFee;

        public Doctor(String doctorName, String specialization, String[] availableSlots, double consultationFee) {
            this.doctorId = generateDoctorId();
            this.doctorName = doctorName;
            this.specialization = specialization;
            this.availableSlots = availableSlots;
            this.consultationFee = consultationFee;
            this.patientsHandled = 0;
        }

        public static String generateDoctorId() {
            return "D" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        }

        public void incrementPatientsHandled() {
            patientsHandled++;
        }

        public void displayInfo() {
            System.out.println("Doctor ID: " + doctorId + " | Name: " + doctorName + " | Specialization: " + specialization);
        }
    }

    static class Appointment {
        String appointmentId;
        Patient patient;
        Doctor doctor;
        String appointmentDate;
        String appointmentTime;
        String status;
        String type;

        static int totalAppointments = 0;
        static String hospitalName = "CareWell Hospital";
        static double totalRevenue = 0.0;

        public Appointment(Patient patient, Doctor doctor, String appointmentDate, String appointmentTime, String type) {
            this.appointmentId = generateAppointmentId();
            this.patient = patient;
            this.doctor = doctor;
            this.appointmentDate = appointmentDate;
            this.appointmentTime = appointmentTime;
            this.status = "Scheduled";
            this.type = type;
            totalAppointments++;
            doctor.incrementPatientsHandled();
        }

        public static String generateAppointmentId() {
            return "A" + (totalAppointments + 1);
        }

        public void cancelAppointment() {
            status = "Cancelled";
            System.out.println("Appointment " + appointmentId + " has been cancelled.");
        }

        public double generateBill() {
            double rate = switch (type) {
                case "Consultation" -> doctor.consultationFee;
                case "Follow-up" -> doctor.consultationFee * 0.5;
                case "Emergency" -> doctor.consultationFee * 1.5;
                default -> doctor.consultationFee;
            };
            totalRevenue += rate;
            System.out.println("Bill for " + patient.patientName + ": ₹" + rate);
            return rate;
        }

        public void displayAppointment() {
            System.out.println("Appointment ID: " + appointmentId + " | Patient: " + patient.patientName +
                    " | Doctor: " + doctor.doctorName + " | Type: " + type + " | Status: " + status);
        }

        public static void generateHospitalReport(Appointment[] appointments) {
            System.out.println("=== " + hospitalName + " Report ===");
            System.out.println("Total Patients     : " + Patient.totalPatients);
            System.out.println("Total Appointments : " + totalAppointments);
            System.out.println("Total Revenue      : ₹" + totalRevenue);
            System.out.println("===============================");
        }

        public static void getDoctorUtilization(Doctor[] doctors) {
            System.out.println("=== Doctor Utilization ===");
            for (Doctor d : doctors) {
                System.out.println(d.doctorName + " - Patients Handled: " + d.patientsHandled);
            }
        }

        public static void getPatientStatistics(Patient[] patients) {
            System.out.println("=== Patient Statistics ===");
            for (Patient p : patients) {
                int activeTreatments = 0;
                for (String t : p.currentTreatments) {
                    if (t != null) activeTreatments++;
                }
                System.out.println(p.patientName + " - Active Treatments: " + activeTreatments);
            }
        }
    }

    public static void main(String[] args) {
        Patient p1 = new Patient("Alice", 30, "Female", "alice@example.com");
        Patient p2 = new Patient("Bob", 45, "Male", "bob@example.com");

        Doctor d1 = new Doctor("Dr. Smith", "Cardiology", new String[]{"10AM", "2PM"}, 1000);
        Doctor d2 = new Doctor("Dr. Mehta", "Neurology", new String[]{"11AM", "3PM"}, 1200);

        Appointment a1 = new Appointment(p1, d1, "2025-08-28", "10AM", "Consultation");
        Appointment a2 = new Appointment(p2, d2, "2025-08-28", "11AM", "Emergency");

        p1.updateTreatment("Blood Pressure Monitoring");
        p2.updateTreatment("MRI Scan");

        a1.generateBill();
        a2.generateBill();

        a1.displayAppointment();
        a2.displayAppointment();

        p1.dischargePatient();

        Appointment.generateHospitalReport(new Appointment[]{a1, a2});
        Appointment.getDoctorUtilization(new Doctor[]{d1, d2});
        Appointment.getPatientStatistics(new Patient[]{p1, p2});
    }
}
