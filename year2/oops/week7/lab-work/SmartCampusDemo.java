// PROBLEM 6: Smart Campus IoT System
// Concept: Safe Downcasting with instanceof
// Create a campus management system with different smart devices:
// ● Smart classrooms control lighting, AC, and projectors
// ● Smart labs manage equipment and safety systems
// ● Smart libraries track occupancy and book availability
// Process mixed device collections safely, applying the right controls to each device type
// without crashing.
// Hint: Check first, cast second - safety matters in the real world!




public class SmartCampusDemo {
    public static void main(String[] args) {
        Device[] devices = new Device[3];
        devices[0] = new SmartClassroom("Room 101");
        devices[1] = new SmartLab("Lab A");
        devices[2] = new SmartLibrary("Central Library");

        for (Device d : devices) {
            d.status();
            if (d instanceof SmartClassroom) {
                SmartClassroom c = (SmartClassroom) d;
                c.setLights(true);
                c.setAC(22);
                c.lowerProjector();
            } else if (d instanceof SmartLab) {
                SmartLab l = (SmartLab) d;
                l.checkSafetySystems();
                l.activateEquipment("3D Printer");
            } else if (d instanceof SmartLibrary) {
                SmartLibrary lib = (SmartLibrary) d;
                lib.updateOccupancy(120);
                lib.checkBookAvailability("Data Structures");
            }
            System.out.println();
        }
    }
}

abstract class Device {
    protected String location;

    public Device(String location) {
        this.location = location;
    }

    public void status() {
        System.out.println("Device at " + location + " reporting status.");
    }
}

class SmartClassroom extends Device {
    public SmartClassroom(String location) { super(location); }

    public void setLights(boolean on) { System.out.println(location + " lights set to " + (on ? "ON" : "OFF")); }
    public void setAC(int temp) { System.out.println(location + " AC set to " + temp + "C"); }
    public void lowerProjector() { System.out.println(location + " projector lowered."); }
}

class SmartLab extends Device {
    public SmartLab(String location) { super(location); }

    public void checkSafetySystems() { System.out.println(location + " safety systems OK."); }
    public void activateEquipment(String eq) { System.out.println(location + " activated equipment: " + eq); }
}

class SmartLibrary extends Device {
    public SmartLibrary(String location) { super(location); }

    public void updateOccupancy(int count) { System.out.println(location + " occupancy updated: " + count); }
    public void checkBookAvailability(String title) { System.out.println(location + " checked availability for: " + title); }
}
