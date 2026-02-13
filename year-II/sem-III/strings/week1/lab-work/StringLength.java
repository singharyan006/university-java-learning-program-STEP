import java.util.*;

public class StringLength {
    public static int countLength(String str) {
        int i = 0;
        try {
            while (true) {
                str.charAt(i);
                i++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // This exception is expected and used to know we've reached the end.
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the string you wanna feed: ");
        String str = sc.nextLine();

        int length = countLength(str);
        System.out.println("The number of letters in the string are: " + length);
        sc.close();
    }
}