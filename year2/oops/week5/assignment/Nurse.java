import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Nurse implements Staff {
    private final String nurseId;
    private String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(qualifications);
    }

    public String getNurseId() {
        return nurseId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public List<String> getQualifications() {
        return Collections.unmodifiableList(qualifications);
    }
    
    @Override
    public String getStaffId() {
        return "Nurse-" + nurseId;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + "'" +
                ", shift='" + shift + "'" +
                '}';
    }
}
