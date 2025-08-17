public class OTPGenerator {

    public static int generateOTP() {
        return (int)(100000 + Math.random() * 900000); // generates a 6-digit number
    }

    public static boolean areOTPsUnique(int[] otps) {
        for (int i = 0; i < otps.length; i++) {
            for (int j = i + 1; j < otps.length; j++) {
                if (otps[i] == otps[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] otpArray = new int[10];

        System.out.println("Generated OTPs:");
        for (int i = 0; i < 10; i++) {
            otpArray[i] = generateOTP();
            System.out.println(otpArray[i]);
        }

        boolean unique = areOTPsUnique(otpArray);
        System.out.println("Are all OTPs unique? " + unique);
    }
}
