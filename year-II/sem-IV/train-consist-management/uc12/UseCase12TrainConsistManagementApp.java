import java.util.ArrayList;
import java.util.List;

public class UseCase12TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC12: Safety Compliance Check for Goods Bogies");

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isCompliant = isTrainSafetyCompliant(goodsBogies);
        System.out.println("Safety compliant train formation: " + isCompliant);

        List<GoodsBogie> unsafeGoodsBogies = new ArrayList<>();
        unsafeGoodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));
        unsafeGoodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isUnsafeCompliant = isTrainSafetyCompliant(unsafeGoodsBogies);
        System.out.println("Safety compliant train formation with invalid cargo: " + isUnsafeCompliant);
        System.out.println("Program continues.");
    }

    public static boolean isTrainSafetyCompliant(List<GoodsBogie> goodsBogies) {
        if (goodsBogies == null) {
            return true;
        }

        return goodsBogies.stream()
                .allMatch(bogie -> !"Cylindrical".equalsIgnoreCase(bogie.type)
                        || "Petroleum".equalsIgnoreCase(bogie.cargo));
    }
}
