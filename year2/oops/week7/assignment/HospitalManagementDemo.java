// PROBLEM 4: Hospital Management System
// Concept: Upcasting
// Demonstrates a general MedicalStaff system with specialized staff types
// and common institutional operations.

public class HospitalManagementDemo {
    public static void main(String[] args) {
        MedicalStaff[] staffList = new MedicalStaff[] {
            new Doctor("Dr. Smith", 101),
            new Nurse("Nurse Amy", 202),
            new Technician("Tech Bob", 303),
            new Administrator("Ms. Clark", 404)
        };

        // Common institutional operations via upcasting
        for (MedicalStaff staff : staffList) {
            staff.shiftSchedule();
            staff.accessIDCard();
            staff.processPayroll();
            System.out.println();
        }

        // Specialized operations via downcasting (example)
        for (MedicalStaff staff : staffList) {
            if (staff instanceof Doctor) {
                Doctor d = (Doctor) staff;
                d.diagnosePatient();
                d.prescribeMedicine();
                d.performSurgery();
            } else if (staff instanceof Nurse) {
                Nurse n = (Nurse) staff;
                n.administerMedicine();
                n.monitorPatient();
                n.assistProcedure();
            } else if (staff instanceof Technician) {
                Technician t = (Technician) staff;
                t.operateEquipment();
                t.runTest();
                t.maintainInstrument();
            } else if (staff instanceof Administrator) {
                Administrator a = (Administrator) staff;
                a.scheduleAppointment();
                a.manageRecords();
            }
            System.out.println();
        }
    }
}

class MedicalStaff {
    protected String name;
    protected int staffId;

    public MedicalStaff(String name, int staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    public void shiftSchedule() {
        System.out.println(name + " (ID: " + staffId + ") scheduled for shift.");
    }

    public void accessIDCard() {
        System.out.println(name + " (ID: " + staffId + ") accessed hospital ID card.");
    }

    public void processPayroll() {
        System.out.println(name + " (ID: " + staffId + ") payroll processed.");
    }
}

class Doctor extends MedicalStaff {
    public Doctor(String name, int staffId) {
        super(name, staffId);
    }
    public void diagnosePatient() {
        System.out.println(name + " is diagnosing a patient.");
    }
    public void prescribeMedicine() {
        System.out.println(name + " is prescribing medicine.");
    }
    public void performSurgery() {
        System.out.println(name + " is performing surgery.");
    }
}

class Nurse extends MedicalStaff {
    public Nurse(String name, int staffId) {
        super(name, staffId);
    }
    public void administerMedicine() {
        System.out.println(name + " is administering medicine.");
    }
    public void monitorPatient() {
        System.out.println(name + " is monitoring a patient.");
    }
    public void assistProcedure() {
        System.out.println(name + " is assisting in a procedure.");
    }
}

class Technician extends MedicalStaff {
    public Technician(String name, int staffId) {
        super(name, staffId);
    }
    public void operateEquipment() {
        System.out.println(name + " is operating medical equipment.");
    }
    public void runTest() {
        System.out.println(name + " is running a diagnostic test.");
    }
    public void maintainInstrument() {
        System.out.println(name + " is maintaining instruments.");
    }
}

class Administrator extends MedicalStaff {
    public Administrator(String name, int staffId) {
        super(name, staffId);
    }
    public void scheduleAppointment() {
        System.out.println(name + " is scheduling an appointment.");
    }
    public void manageRecords() {
        System.out.println(name + " is managing hospital records.");
    }
}