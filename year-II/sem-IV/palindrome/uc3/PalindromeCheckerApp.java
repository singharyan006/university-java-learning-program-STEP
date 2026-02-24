public class PalindromeCheckerApp {
    public static void main(String[] args) {
        String ip = "madam";
        String r_ip = "";
        for(int i=ip.length()-1; i>=0; i--) {
            r_ip += String.valueOf(ip.charAt(i));
        }

        if(ip.equals(r_ip)) {
            System.out.println("ip string is pallindrome");
        } else {
            System.out.println("ip string is not a pallindrome");
        }
    }
}