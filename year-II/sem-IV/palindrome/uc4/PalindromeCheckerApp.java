public class PalindromeCheckerApp {
    public static void main(String[] args) {
        String input = "madam";

        char[] arr = input.toCharArray();   // convert String → char[]

        int left = 0;
        int right = arr.length - 1;

        boolean isPalindrome = true;

        while (left < right) {

            if (arr[left] != arr[right]) {
                isPalindrome = false;
                break;
            }

            left++;
            right--;
        }

        if (isPalindrome) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }

}