import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Administrator implements Staff {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(accessPermissions);
    }

    public String getAdminId() {
        return adminId;
    }

    public List<String> getAccessPermissions() {
        return Collections.unmodifiableList(accessPermissions);
    }

    @Override
    public String getStaffId() {
        return "Admin-" + adminId;
    }

    @Override
    public String getRole() {
        return "Administrator";
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminId='" + adminId + "'" +
                '}';
    }
}
