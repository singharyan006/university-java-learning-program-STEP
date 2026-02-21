public class PalindromeCheckerApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Pallindrome Checker App\n");

        String ip = "madaam";
        boolean isPallindrome = true;
        for(int i=0; i<ip.length()/2; i++) {
            if(ip.charAt(i) != ip.charAt(ip.length()-i-1)) {
                isPallindrome = false;
            }
        }

        if(isPallindrome) {
            System.out.println("The input is a pallindrome!");
        } else {
            System.out.println("The input is NOT pallindorme!");
        }
    }
}