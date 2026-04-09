import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseCase11TrainConsistManagementApp {
    private static final Pattern TRAIN_ID_PATTERN = Pattern.compile("TRN-\\d{4}");
    private static final Pattern CARGO_CODE_PATTERN = Pattern.compile("PET-[A-Z]{2}");

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC11: Validate Train ID & Cargo Codes");

        String validTrainId = "TRN-1234";
        String invalidTrainId = "TRAIN12";
        String validCargoCode = "PET-AB";
        String invalidCargoCode = "PET-ab";

        System.out.println(validTrainId + " -> " + validateTrainId(validTrainId));
        System.out.println(invalidTrainId + " -> " + validateTrainId(invalidTrainId));
        System.out.println(validCargoCode + " -> " + validateCargoCode(validCargoCode));
        System.out.println(invalidCargoCode + " -> " + validateCargoCode(invalidCargoCode));
        System.out.println("Empty train ID -> " + validateTrainId(""));
        System.out.println("Empty cargo code -> " + validateCargoCode(""));
        System.out.println("Program continues.");
    }

    public static boolean validateTrainId(String trainId) {
        Matcher matcher = TRAIN_ID_PATTERN.matcher(trainId == null ? "" : trainId);
        return matcher.matches();
    }

    public static boolean validateCargoCode(String cargoCode) {
        Matcher matcher = CARGO_CODE_PATTERN.matcher(cargoCode == null ? "" : cargoCode);
        return matcher.matches();
    }
}
